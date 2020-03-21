package tk.leaflame.framework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.leaflame.framework.aop.proxy.Proxy;
import tk.leaflame.framework.aop.proxy.ProxyChain;

import java.lang.reflect.Method;

/**
 * 切面代理
 * 抽象类提供Template Method
 *
 * @author leaflame
 * @date 2020/3/22 1:41
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        Class<?> cls = proxyChain.getTargetClass(); //target class
        Method method = proxyChain.getTargetMethod(); //target method
        Object[] params = proxyChain.getMethodParams(); //method params
        //begin
        begin();
        try { //TODO
            if (intercept(cls, method, params)) { //拦截
                //before
                before(cls, method, params); //前置增强
                result = proxyChain.doProxyChain();
                //after
                after(cls, method, params, result); //后置增强
            } else {
                result = proxyChain.doProxyChain(); //放行
            }
        } catch (Exception e) {
            logger.error("AOP ERROR", e);
            error(cls, method, params, e);
            throw e;
        } finally {
            //end
            end();
        }
        return result;
    }

    /**
     * begin
     */
    public void begin() {
    }

    /**
     * intercept
     *
     * @param cls
     * @param method
     * @param params
     * @return
     * @throws Throwable
     */
    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    /**
     * before
     *
     * @param cls
     * @param method
     * @param params
     * @throws Throwable
     */
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    }

    /**
     * after
     *
     * @param cls
     * @param method
     * @param params
     * @param result
     * @throws Throwable
     */
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
    }

    /**
     * error
     *
     * @param cls
     * @param method
     * @param params
     * @param e
     */
    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
    }

    /**
     * end
     */
    public void end() {
    }
}
