package tk.leaflame.framework.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author leaflame
 * @date 2020/2/29 17:55
 */
public class StringUtil {

    public static final String SEPARATOR = String.valueOf((char) 29);

    public static boolean isNotEmpty(String str){
        return StringUtils.isNotEmpty(str);
    }

    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    public static String defaultIfEmpty(String str, String defaultValue) {
        return StringUtils.defaultIfEmpty(str, defaultValue);
    }

}
