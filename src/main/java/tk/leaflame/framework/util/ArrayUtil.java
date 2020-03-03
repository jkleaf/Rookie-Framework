package tk.leaflame.framework.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author leaflame
 * @date 2020/3/3 13:08
 */
public class ArrayUtil {

    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
