package core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.leaflame.framework.aop.AspectProxy;
import tk.leaflame.framework.aop.annotation.Aspect;
import tk.leaflame.framework.mvc.annotation.Controller;

import java.lang.reflect.Method;

/**
 * 拦截Controller的所有方法
 *
 * @author leaflame
 * @date 2020/3/22 2:46
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.debug("==========begin==========");
        logger.debug(String.format("class: %s", cls.getName()));
        logger.debug(String.format("method: %s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        logger.debug(String.format("time: %dms", System.currentTimeMillis() - begin)); //计算耗时
        logger.debug("==========end==========");
    }
}
