package tk.leaflame.framework.ioc;

import tk.leaflame.framework.core.ClassHelper;
import tk.leaflame.framework.core.fault.InitializationException;
import tk.leaflame.framework.ioc.annotation.Impl;
import tk.leaflame.framework.ioc.annotation.Inject;
import tk.leaflame.framework.util.ArrayUtil;
import tk.leaflame.framework.util.CollectionUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author leaflame
 * @date 2020/3/3 10:58
 */
public class IocHelper {

    static {
        try {
            //Gets bean map
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            //iterate key-value pair of bean map
            for (Map.Entry<Class<?>, Object> beanEntity : beanMap.entrySet()) {
                //Gets bean class and instance
                Class<?> beanClass = beanEntity.getKey(); //key: bean class
                Object beanInstance = beanEntity.getValue(); //value: bean instance
                //Gets all fields from bean class (exclude fields in super class)
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    //iterate fields
                    for (Field beanField : beanFields) {
                        //Determines if the field has the Inject annotation
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            //Gets class(interface) from field
                            Class<?> interfaceClass = beanField.getType();
                            Class<?> implementClass = findImplementClass(interfaceClass);
                            //if impl class exists
                            if (implementClass != null) {
                                //Gets implement instance corresponding to the implement class from bean map
                                Object implementInstance = beanMap.get(implementClass);
                                if (implementInstance != null) {
                                    beanField.setAccessible(true); //allow access (set public)
                                    beanField.set(beanInstance, implementInstance); //set initial instance
                                } else {
                                    throw new InitializationException("DI error! class-name: "
                                            + beanClass.getSimpleName() + " field-name: "
                                            + interfaceClass.getSimpleName());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new InitializationException("initial error in IocHelper!", e);
        }
    }

    public static Class<?> findImplementClass(Class<?> interfaceClass) {
        Class<?> implementClass = interfaceClass;
        //Determines if the interface has the Impl annotation
        if (interfaceClass.isAnnotationPresent(Impl.class)) {
            //directly get the implement class of the interface
            implementClass = interfaceClass.getAnnotation(Impl.class).value();
        } else {
            //Gets all implement classes of the interface
            List<Class<?>> implementClassList = ClassHelper.getClassListBySuper(interfaceClass);
            if (CollectionUtil.isNotEmpty(implementClassList)) {
                //Gets the first implement class (only one)
                implementClass = implementClassList.get(0);
            }
        }
        return implementClass;
    }
}
