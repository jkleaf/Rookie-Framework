package tk.leaflame.framework.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器
 *
 * @author leaflame
 * @date 2020/3/21 23:23
 */
public class ProxyManager {

    /**
     * 创建代理对象
     * 将{@link MethodInterceptor#intercept}的参数传入proxyChain的构造器
     *
     * @see MethodInterceptor#intercept
     * TODO
     *
     * @param targetClass
     * @param proxyList
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
        return (T) Enhancer.create(targetClass, (MethodInterceptor) (targetObject, targetMethod, methodParams, methodProxy)
                -> new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList)
                .doProxyChain());
    }
}
