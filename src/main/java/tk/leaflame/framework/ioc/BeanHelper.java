package tk.leaflame.framework.ioc;

import tk.leaflame.framework.aop.annotation.Aspect;
import tk.leaflame.framework.core.ClassHelper;
import tk.leaflame.framework.core.fault.InitializationException;
import tk.leaflame.framework.ioc.annotation.Bean;
import tk.leaflame.framework.mvc.annotation.Action;
import tk.leaflame.framework.tx.annotation.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leaflame
 * @date 2020/3/3 8:56
 */
public final class BeanHelper {

    /**
     * Bean Map (mapping bean class and bean instance)
     * ensure singleton instance
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        //Gets all classes from base package
        List<Class<?>> classList = ClassHelper.getClassList();
        classList.forEach(cls -> {
            // specific annotations on the elements
            if (cls.isAnnotationPresent(Bean.class) ||
                    cls.isAnnotationPresent(Service.class) ||
                    cls.isAnnotationPresent(Action.class) ||
                    cls.isAnnotationPresent(Aspect.class)) {
                try {
                    //create bean instance
                    Object beanInstance = cls.newInstance();
                    //put into bean map
                    BEAN_MAP.put(cls, beanInstance);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new InitializationException("initial bean instance error!", e);
                }
            }
        });
    }

    /**
     * get bean map
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * set bean instance (put into bean map)
     *
     * @param cls
     * @param obj
     */
    public static void setBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }

    /**
     * get bean instance
     *
     * @param cls
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("unable to get instance by class " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }
}
