package tk.leaflame.framework.core.impl;

import tk.leaflame.framework.core.ClassScanner;
import tk.leaflame.framework.core.support.ClassTemplate;

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
                return pkgName.startsWith(packageName);
            }
        }.getClassList();
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
