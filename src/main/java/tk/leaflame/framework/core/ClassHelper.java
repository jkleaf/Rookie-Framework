package tk.leaflame.framework.core;

import tk.leaflame.framework.InstanceFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Gets the related classes based on the condition
 *
 * @author leaflame
 * @date 2020/3/1 23:10
 */
public class ClassHelper {

    /**
     * Gets base package from config file
     */
    private static final String basePackage = ConfigHelper.getString("rookie.framework.app.base_package");

    /**
     * Gets class scanner from instance factory
     */
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    /**
     * Gets all classes from base package
     *
     * @return
     */
    public static List<Class<?>> getClassList() {
        return classScanner.getClassList(basePackage);
    }

    /**
     * Gets the related class that specifies the parent class or interface from base package
     *
     * @param superClass
     * @return
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(basePackage, superClass);
    }

    /**
     * Gets the related class for the specified annotation from base package
     *
     * @param annotationClass
     * @return
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassListByAnnotation(basePackage, annotationClass);
    }

}
