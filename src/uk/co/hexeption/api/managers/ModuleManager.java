package uk.co.hexeption.api.managers;

import uk.co.hexeption.api.io.LoggerHelper;
import uk.co.hexeption.api.mod.Module;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hexeption on 16/01/2017.
 */
public class ModuleManager {

    private static ModuleManager instance;

    private final List<Module> modules;

    public ModuleManager() {

        modules = new LinkedList<Module>();

    }

    /**
     * Adds a module/s to the list.
     *
     * @param modules
     */
    public void addModules(final Module... modules) {

        synchronized (this.modules) {
            for (final Module module : modules) {
                this.modules.add(module);
            }
        }
    }

    /**
     * @param clazz
     * @param <T>
     * @return A Module by a class file
     */
    public <T extends Module> T getModuleByClass(final Class<T> clazz) {

        synchronized (this.modules) {
            for (final Module module : modules) {
                if (module.getClass().equals(clazz)) {
                    return (T) module;
                }
            }
        }

        LoggerHelper.warn(String.format("Module %s not found by class, returning null!", clazz.getCanonicalName()));
        return null;
    }

    /**
     * @param name
     * @param <T>
     * @return A Module by name
     */
    public <T extends Module> T getModuleByName(final String name) {

        synchronized (this.modules) {
            for (final Module module : modules) {
                if (module.getName().replaceAll(" ", "").toLowerCase().equals(name.toLowerCase())) {
                    return (T) module;
                }
            }
        }

        LoggerHelper.warn(String.format("Module %s not found by name, returning null!", name));
        return null;
    }

    public List<Module> getModules() {

        synchronized (this.modules) {
            return modules;
        }
    }


}
