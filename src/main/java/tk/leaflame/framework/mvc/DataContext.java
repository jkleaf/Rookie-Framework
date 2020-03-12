package tk.leaflame.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author leaflame
 * @date 2020/3/12 20:21
 */
public class DataContext {

    private static final ThreadLocal<DataContext> dataContextContainer = new ThreadLocal<>();

    private HttpServletRequest request;

    private HttpServletResponse response;

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        DataContext dataContext = new DataContext();
        dataContext.request = request;
        dataContext.response = response;
        dataContextContainer.set(dataContext);
    }

    public static void destroy() {
        dataContextContainer.remove();
    }

    public static DataContext getInstance() {
        return dataContextContainer.get();
    }

    public HttpServletRequest getRequest() {
        return getInstance().request;
    }

    public HttpServletResponse getResponse() {
        return getInstance().response;
    }
}
