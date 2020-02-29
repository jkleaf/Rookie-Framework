package tk.leaflame.framework.core;

import tk.leaflame.framework.FrameworkConstant;
import tk.leaflame.framework.util.PropsUtil;

import java.util.Properties;

/**
 * @author leaflame
 * @date 2020/2/29 17:45
 */
public class ConfigHelper {

    private static final Properties configProps = PropsUtil.loadProps(FrameworkConstant.CONFIG_PROPS);

    public static String getString(String key) {
        return PropsUtil.getString(configProps, key);
    }

    public static int getInt(String key) {
        return PropsUtil.getNumber(configProps, key);
    }
}

