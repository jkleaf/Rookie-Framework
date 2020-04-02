package tk.leaflame.framework.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.leaflame.framework.aop.proxy.Proxy;
import tk.leaflame.framework.aop.proxy.ProxyChain;
import tk.leaflame.framework.dao.DataBaseHelper;
import tk.leaflame.framework.tx.annotation.Transaction;

import java.lang.reflect.Method;

/**
 * @author leaflame
 * @date 2020/4/2 23:23
 */
public class TransactionProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(TransactionProxy.class);

    /**
     * 定义一个ThreadLocal，用于保存当前线程是否进行了事务处理(保证事务控制相关逻辑只执行一次)，默认为false
     */
    private static final ThreadLocal<Boolean> FLAG_HOLDER = ThreadLocal.withInitial(() -> false);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        //Gets transaction flag from current thread
        boolean flag = FLAG_HOLDER.get();
        //Gets target method
        Method method = proxyChain.getTargetMethod();
        //若当前线程未进行事务处理，且在目标方法上定义了 Transaction 注解，则说明该方法需要进行事务处理
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            //设置当前线程已进行事务处理
            FLAG_HOLDER.set(true);
            try {
                //开启事务
                DataBaseHelper.beginTransaction();
                logger.debug("[rookie] begin transaction");
                //执行目标方法
                result = proxyChain.doProxyChain();
                //提交事务
                DataBaseHelper.commitTransaction();
                logger.debug("[rookie] commit transaction");
            } catch (Exception e) {
                //回滚事务
                DataBaseHelper.rollbackTransaction();
                logger.debug("[rookie] rollback transaction"); //debug log
                throw e; //throw
            } finally {
                //移除线程局部变量
                FLAG_HOLDER.remove();
            }
        } else {
            //无事务可执行，直接执行目标方法
            result = proxyChain.doProxyChain(); //放行
        }
        return result;
    }
}
