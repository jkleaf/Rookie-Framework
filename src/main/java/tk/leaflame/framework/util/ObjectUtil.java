package tk.leaflame.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leaflame
 * @date 2020/3/1 23:45
 */
public class ObjectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

    /**
     * newInstance
     *
     * @param className
     * @param <T>
     * @return
     */
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtil.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("create instance error", e);
            throw new RuntimeException(e);
        }
        return instance;
    }
}
