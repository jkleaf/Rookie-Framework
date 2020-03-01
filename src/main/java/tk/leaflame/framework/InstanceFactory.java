package tk.leaflame.framework;

import sun.plugin2.message.ShowStatusMessage;
import tk.leaflame.framework.core.ClassScanner;
import tk.leaflame.framework.core.ConfigHelper;
import tk.leaflame.framework.core.impl.DefaultClassScanner;
import tk.leaflame.framework.util.ObjectUtil;
import tk.leaflame.framework.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Instance Factory
 *
 * @author leaflame
 * @date 2020/3/1 23:22
 */
public class InstanceFactory {

    /**
     * cache instance
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    //TODO
    //...

    public static final String CLASS_SCANNER = "rookie.framework.custom.class_scanner";

    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }


    /**
     * load instance from cache,config through reflection
     *
     * @param cacheKey
     * @param defaultImplClass
     * @param <T>
     * @return
     */
    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        //if cache exists
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        //get impl class configuration from config file
        String implClassName = ConfigHelper.getString(cacheKey);
        //if impl class configuration does not exist , use default class
        if (StringUtil.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }
        //create instance of this impl class through reflection
        T instance = ObjectUtil.newInstance(implClassName);
        //if instance is not null then put into cache
        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        //return instance
        return instance;
    }

}
