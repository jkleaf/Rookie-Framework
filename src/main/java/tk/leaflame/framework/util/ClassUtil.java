package tk.leaflame.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * @author leaflame
 * @date 2020/2/29 18:02
 */
public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    //todo
    public static String getCLassPath() {
        String classPath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classPath = resource.getPath();
        }
        return classPath;
    }

    /**
     * 加载类（自动初始化）
     *
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    public static Class<?> loadClass(String classname, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(classname, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("load class error!", e);
            throw new RuntimeException();
        }
        return cls;
    }




}
