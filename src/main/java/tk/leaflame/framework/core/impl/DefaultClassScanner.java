package tk.leaflame.framework.core.impl;

import tk.leaflame.framework.core.ClassScanner;
import tk.leaflame.framework.core.support.AnnotationClassTemplate;
import tk.leaflame.framework.core.support.ClassTemplate;
import tk.leaflame.framework.core.support.SuperClassTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author leaflame
 * @date 2020/3/1 23:50
 */
public class DefaultClassScanner implements ClassScanner {

    @Override
    public List<Class<?>> getClassList(String packageName) {
        return new ClassTemplate(packageName) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                String clsName = cls.getName();
                String pkgName = clsName.substring(0, clsName.lastIndexOf("."));
                return pkgName.startsWith(packageName); //all classes
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new AnnotationClassTemplate(packageName, annotationClass) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return cls.isAnnotationPresent(annotationClass); //with annotation on the type element
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return new SuperClassTemplate(packageName, superClass) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return superClass.isAssignableFrom(cls) && !superClass.equals(cls); //super class or interface
            }
        }.getClassList();
    }
}
