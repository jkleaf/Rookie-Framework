package tk.leaflame.framework.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author leaflame
 * @date 2020/3/5 10:11
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Object to JSON
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2JSON(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("object to JSON error!", e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * JSON to Object
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T JSON2obj(String json, Class<?> type) {
        T obj;
        try {
            obj = (T) objectMapper.readValue(json, type);
        } catch (IOException e) {
            logger.error("JSON to object error!", e);
            throw new RuntimeException(e);
        }
        return obj;
    }
}
