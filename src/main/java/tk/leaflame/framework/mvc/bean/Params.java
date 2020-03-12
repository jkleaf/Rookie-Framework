package tk.leaflame.framework.mvc.bean;

import tk.leaflame.framework.core.bean.BaseBean;

import java.util.Map;

/**
 * encapsulate request parameters
 *
 * @author leaflame
 * @date 2020/3/5 10:58
 */
public class Params extends BaseBean {

    private final Map<String, Object> fieldMap;

    public Params(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    private Object get(String name) {
        return fieldMap.get(name);
    }
}
