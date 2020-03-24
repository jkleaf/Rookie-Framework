package tk.leaflame.framework.aop;

import tk.leaflame.framework.FrameworkConstant;
import tk.leaflame.framework.InstanceFactory;
import tk.leaflame.framework.aop.annotation.Aspect;
import tk.leaflame.framework.aop.proxy.Proxy;
import tk.leaflame.framework.aop.proxy.ProxyManager;
import tk.leaflame.framework.core.ClassHelper;
import tk.leaflame.framework.core.ClassScanner;
import tk.leaflame.framework.core.fault.InitializationException;
import tk.leaflame.framework.ioc.BeanHelper;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 初始化Aop框架
 *
 * @author leaflame
 * @date 2020/3/22 3:05
 */
public final class AopHelper {

    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    static {
        try {
            // 创建 Proxy Map（用于 存放代理类 与 目标类列表 的映射关系）（e.x. ControllerAspect.class <=> {xxxController.class}）
            Map<Class<?>, List<Class<?>>> proxyMap = createProxyMap();
            // 创建 Target Map（用于 存放目标类 与 代理类对象列表 的映射关系）(由上述关系得出，多对多关系)（e.x. xxxController.class <=> {xxxAspect.class}）
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap); //主要 important (代理列表最终传入相应代理链)
            //TODO
            //iterate targetMap
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                // 创建代理实例，同时也新建一条代理链（返回该目标对象经过代理链，最后动态代理得到的实例）
                Object proxyInstance = ProxyManager.createProxy(targetClass, proxyList);
                // 用代理实例覆盖目标实例，并放入 Bean 容器中
                BeanHelper.setBean(targetClass, proxyInstance);
            }
        } catch (Exception e) {
            throw new InitializationException("Error initialize AopHelper", e);
        }
    }

    private static Map<Class<?>, List<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, List<Class<?>>> proxyMap = new LinkedHashMap<>();
        // 添加相关代理

        addAspectProxy(proxyMap);      // 切面代理

        return proxyMap;
    }

    private static void addAspectProxy(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        // 获取切面类（所有继承于 AspectProxy 的类）
        List<Class<?>> proxyClassList = ClassHelper.getClassListBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxyClassList) {
            //with annotation(Aspect) on the type element
            if (proxyClass.isAnnotationPresent(Aspect.class)) {
                //Gets the aspect annotation which is defined on the aspect class
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                // 创建目标类列表（根据获得到的Aspect注解）
                List<Class<?>> targetClassList = createTargetClassList(aspect);
                // 将代理类和目标类列表加入proxyMap (e.x. key=ControllerAspect.class,value=xxxController.class)
                proxyMap.put(proxyClass, targetClassList);
            }
        }
    }

    private static List<Class<?>> createTargetClassList(Aspect aspect) throws Exception {
        List<Class<?>> targetClassList = new ArrayList<>();
        Class<? extends Annotation> annotation = aspect.value();
        // 若注解不为空且不是 Aspect 注解，则添加应用包名下带有该注解的所有类
        if (!annotation.equals(Aspect.class)) {
            targetClassList.addAll(ClassHelper.getClassListByAnnotation(annotation));
        }
        return targetClassList;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, List<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey(); //代理类
            List<Class<?>> targetClassList = proxyEntry.getValue(); //目标类列表
            for (Class<?> targetClass : targetClassList) {
                // 创建代理类（切面类）实例 TODO
                Proxy baseAspect = (Proxy) proxyClass.newInstance();
                //if contains
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(baseAspect); //根据targetMap的目标类获取代理对象列表并添加代理类对象
                } else {
                    //if not contains
                    List<Proxy> baseAspectList = new ArrayList<>(); //新建代理对象列表
                    baseAspectList.add(baseAspect); //添加代理类对象
                    targetMap.put(targetClass, baseAspectList); //Sets key and value
                }
            }
        }
        return targetMap;
    }
}
