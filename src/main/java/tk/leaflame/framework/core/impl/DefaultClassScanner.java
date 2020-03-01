package tk.leaflame.framework.core.impl;

import tk.leaflame.framework.core.ClassScanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author leaflame
 * @date 2020/3/1 23:50
 */
public class DefaultClassScanner implements ClassScanner {

    @Override
    public List<Class<?>> getClassList(String packageName) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
