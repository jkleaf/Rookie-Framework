package tk.leaflame.framework.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.leaflame.framework.util.ClassUtil;
import tk.leaflame.framework.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author leaflame
 * @date 2020/3/1 1:12
 */
public abstract class ClassTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    /**
     * package name (used to load class)
     */
    protected final String packageName;

    protected ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    /**
     * get class list
     *
     * @return {@code List<Class<?>>}
     */
    public final List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            //Gets urls by parsing package name
            Enumeration<URL> urls = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));
            //iterate urls
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    //Gets protocol (file or jar)
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) { //todo
                        //if int the class packages,do add class
                        String packagePath = url.getPath().replaceAll("%20", " ");//replace space
                        addClass(classList, packagePath, packageName);
                    } else if (protocol.equals("jar")) { //todo
                        //if int the jar packages,parse entry from jar (unzip)
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            // the entry is class
                            if (jarEntryName.endsWith(".class")) {
                                //Gets class name (may be under some packages ("/" should be handled))
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                //directly do add class due to the loop
                                doAddClass(classList, className);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("get class error!", e);
            e.printStackTrace();
        }
        return classList;
    }

    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        try {
            //Gets the class files or folders under the package name path
            File[] files = new File(packagePath).listFiles(f -> (f.isFile() && f.getName().endsWith(".class")) || f.isDirectory());
            assert files != null;
            //iterate files
            for (File file : files) {
                String fileName = file.getName();
                if (file.isFile()) { //is file
                    //Gets class file name
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    if (StringUtil.isNotEmpty(packageName)) {
                        //real package path
                        className = packageName + "." + className;
                    }
                    doAddClass(classList, className);
                } else { //is dir
                    //Gets sub package path
                    String subPackagePath = fileName;
                    if (StringUtil.isNotEmpty(packagePath)) {
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    //Gets sub package name
                    String subPackageName = fileName;
                    if (StringUtil.isNotEmpty(subPackageName)) {
                        subPackageName = packageName + "." + subPackageName;
                    }
                    //recursively add class
                    addClass(classList, subPackagePath, subPackageName);
                }
            }
        } catch (Exception e) {
            logger.error("add class error!", e);
        }
    }

    /**
     * load class by className then put into classList
     *
     * @param classList {@code List<Class<?>>}
     * @param className {@code packageName.className}
     */
    private void doAddClass(List<Class<?>> classList, String className) {
        //load class
        Class<?> clz = ClassUtil.loadClass(className, false);
        // class can be added
        if (checkAddClass(clz)) {
            //add class
            classList.add(clz);
        }
    }

    /**
     * Determines if class is able to be added (as a filter)
     * differentiate super class and annotation class (TO BE {@Override})
     *
     * @param cls
     * @return
     */
    public abstract boolean checkAddClass(Class<?> cls);
}
