package tk.leaflame.framework.aop.proxy;

/**
 * 代理接口
 *
 * @author leaflame
 * @date 2020/3/21 23:21
 */
public interface Proxy {

    /**
     * 执行链式代理
     *
     * @param proxyChain 代理链
     * @return 目标方法返回值
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
