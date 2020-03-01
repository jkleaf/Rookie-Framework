package tk.leaflame.framework.core.support;

/**
 * template class used to get sub class
 *
 * @author leaflame
 * @date 2020/3/1 1:12
 */
public abstract class SuperClassTemplate extends ClassTemplate {

    protected final Class<?> superClass;

    protected SuperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }
}
