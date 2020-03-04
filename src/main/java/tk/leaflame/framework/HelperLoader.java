package tk.leaflame.framework;

import tk.leaflame.framework.ioc.BeanHelper;
import tk.leaflame.framework.ioc.IocHelper;
import tk.leaflame.framework.mvc.ControllerHelper;
import tk.leaflame.framework.util.ClassUtil;

import java.util.Arrays;

/**
 * load Helper classes
 *
 * @author leaflame
 * @date 2020/3/5 2:16
 */
public final class HelperLoader {

    public static void init() {
        //defines Helper classes that need to be loaded
        Class<?>[] classList = {
                //TODO
                ControllerHelper.class,
                BeanHelper.class,
                IocHelper.class,
                //TODO
        };
        Arrays.asList(classList).forEach(cls -> {
            ClassUtil.loadClass(cls.getName());
        });
    }
}
