package tk.leaflame.framework.util;

import java.util.Collection;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author leaflame
 * @date 2020/3/3 12:50
 */
public class CollectionUtil {

    public static boolean isNotEmpty(Collection<?> collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }
}
