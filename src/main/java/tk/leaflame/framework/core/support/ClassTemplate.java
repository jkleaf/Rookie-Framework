package tk.leaflame.framework.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author leaflame
 * @date 2020/3/1 1:12
 */
public abstract class ClassTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    protected final String packageName;

    protected ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    public final List<Class<?>> getClassList() {
        return null;
    }

    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {

    }

    private void doAddClass(List<Class<?>> classList, String className) {

    }

    public abstract boolean checkAddClass(Class<?> cls);
}
