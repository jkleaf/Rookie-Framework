package tk.leaflame.framework.mvc;

import java.lang.reflect.Method;

/**
 * encapsulate information about Controller method
 *
 * @author leaflame
 * @date 2020/3/4 23:26
 */
public class Handler {

    private Class<?> controllerClass;

    private Method requestMethod;

    public Handler(Class<?> controllerClass, Method requestMethod) {
        this.controllerClass = controllerClass;
        this.requestMethod = requestMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }
}
