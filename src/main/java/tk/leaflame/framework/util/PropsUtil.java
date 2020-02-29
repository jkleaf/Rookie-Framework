package tk.leaflame.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author leaflame
 * @date 2020/2/29 17:47
 */
public class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String propsPath) {
        Properties props = new Properties();
        InputStream is = null; //todo auto release
        try {
            if (StringUtil.isEmpty(propsPath)) {
                throw new IllegalArgumentException();
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath += suffix;
            }
            is = ClassUtil.getClassLoader().getResourceAsStream(propsPath);
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            logger.error("load props files error!", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("release resource error!", e);
            }
        }
        return props;
    }

    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    public static int getNumber(Properties props, String key) {
        int value = 0;
        if (props.containsKey(key)) {
//            value = CastUtil.castInt(props.getProperty(key)); todo
        }
        return value;
    }

    public static boolean getBoolean(Properties props, String key, boolean defalutValue) {
        boolean value = defalutValue;
        if (props.containsKey(key)) {
//            value = CastUtil.castBoolean(props.getProperty(key)); todo
        }
        return value;
    }
}
