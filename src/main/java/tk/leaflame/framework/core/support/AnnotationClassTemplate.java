package tk.leaflame.framework.core.support;

import java.lang.annotation.Annotation;

/**
 * template class used to get annotation class
 *
 * @author leaflame
 * @date 2020/3/1 1:12
 */
public abstract class AnnotationClassTemplate extends ClassTemplate {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
}
