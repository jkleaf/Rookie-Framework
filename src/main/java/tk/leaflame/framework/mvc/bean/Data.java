package tk.leaflame.framework.mvc.bean;

/**
 * such as JSON
 *
 * @author leaflame
 * @date 2020/3/8 1:34
 */
public class Data {

    private Object data;

    public Data(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
