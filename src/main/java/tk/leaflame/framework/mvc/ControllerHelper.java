package tk.leaflame.framework.mvc;

import tk.leaflame.framework.core.ClassHelper;
import tk.leaflame.framework.mvc.annotation.Controller;
import tk.leaflame.framework.mvc.annotation.Request;
import tk.leaflame.framework.util.ArrayUtil;
import tk.leaflame.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leaflame
 * @date 2020/3/4 20:15
 */
public class ControllerHelper {

    private static final Map<Requester, Handler> controllerMap = new LinkedHashMap<>();

    static {
        //Gets all controller class
        List<Class<?>> controllerClassList = ClassHelper.getClassListByAnnotation(Controller.class);
        if (CollectionUtil.isNotEmpty(controllerClassList)) {
            //Defines two controller map
            Map<Requester, Handler> commonControllerMap = new HashMap<>();
            Map<Requester, Handler> regexControllerMap = new HashMap<>();
            //iterate controller classes
            controllerClassList.forEach(controllerClass -> {
                //Gets all methods in the controller class (excluding inherited methods)
                Method[] controllerMethods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(controllerMethods)) {
                    for (Method method : controllerMethods) {
                        //handle method
                    }
                }
            });
        }
    }

    /**
     *
     */
    private static void handleControllerMthod(Class<?> controllerClass, Method controllerMethod,
                                              Map<Requester, Handler> commonControllerMap,
                                              Map<Requester, Handler> regexControllerMap) {
        //Determines whether the controller methods have Request annotations
        if (controllerMethod.isAnnotationPresent(Request.Get.class)) {
            String reqPath = controllerMethod.getAnnotation(Request.Get.class).value();
            putControllerMap("GET", reqPath, controllerClass, controllerMethod, commonControllerMap, regexControllerMap);
        } else if (controllerMethod.isAnnotationPresent(Request.Post.class)) {
            String reqPath = controllerMethod.getAnnotation(Request.Post.class).value();
            putControllerMap("POST", reqPath, controllerClass, controllerMethod, commonControllerMap, regexControllerMap);
        } else if (controllerMethod.isAnnotationPresent(Request.Put.class)) {
            String reqPath = controllerMethod.getAnnotation(Request.Put.class).value();
            putControllerMap("PUT", reqPath, controllerClass, controllerMethod, commonControllerMap, regexControllerMap);
        } else if (controllerMethod.isAnnotationPresent(Request.Delete.class)) {
            String reqPath = controllerMethod.getAnnotation(Request.Delete.class).value();
            putControllerMap("DELETE", reqPath, controllerClass, controllerMethod, commonControllerMap, regexControllerMap);
        }
    }

    private static void putControllerMap(String reqMethod, String reqPath, Class<?> controllerClass, Method controllerMethod,
                                         Map<Requester, Handler> commonControllerMap,
                                         Map<Requester, Handler> regexControllerMap) {
        //Checks placeholder
        if (reqPath.matches("")) {
            //TODO

            //put into regex controller map
            regexControllerMap.put(new Requester(reqMethod, reqPath), new Handler(controllerClass, controllerMethod));
        } else {
            //put into common controller map
            commonControllerMap.put(new Requester(reqMethod, reqPath), new Handler(controllerClass, controllerMethod));
        }
    }

    /**
     * Gets controller map
     *
     * @return
     */
    public static Map<Requester, Handler> getControllerMap() {
        return controllerMap;
    }
}
