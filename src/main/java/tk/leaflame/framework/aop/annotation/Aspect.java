package tk.leaflame.framework.aop.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 *
 * @author leaflame
 * @date 2020/3/3 9:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value(); //annotation

    //TODO other attributes (pkg,cls...)
}
