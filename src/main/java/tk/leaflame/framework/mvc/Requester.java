package tk.leaflame.framework.mvc;

/**
 * encapsulate Request obj
 *
 * @author leaflame
 * @date 2020/3/4 23:26
 */
public class Requester {

    private String reqMethod;

    private String reqPath;

    public Requester(String reqMethod, String reqPath) {
        this.reqMethod = reqMethod;
        this.reqPath = reqPath;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public String getReqPath() {
        return reqPath;
    }
}
