package tk.leaflame.framework.mvc.bean;

import tk.leaflame.framework.core.bean.BaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * if return a view
 *
 * @author leaflame
 * @date 2020/3/5 11:13
 */
public class View extends BaseBean {

    private String path; //view path

    private Map<String, Object> data; //data

    public View(String path) {
        this.path = path;
        data = new HashMap<>();
    }

    public View data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    /**
     * redirect
     * @return
     */
    public boolean isRedirect() {
        return path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
