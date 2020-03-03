package tk.leaflame.framework.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Class Scanner
 *
 * @author leaflame
 * @date 2020/3/1 23:15
 */
public interface ClassScanner {


    /**
     * get classes by package name
     *
     * @param packageName
     * @return
     */
    List<Class<?>> getClassList(String packageName);


    /**
     * get classes by package name and annotation
     *
     * @param packageName
     * @param annotationClass
     * @return
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);


    /**
     * get classes by super package name and super class or interface
     *
     * @param packageName
     * @param superClass
     * @return
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);
}
