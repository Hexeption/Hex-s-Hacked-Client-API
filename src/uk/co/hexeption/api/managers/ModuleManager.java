package uk.co.hexeption.api.managers;

import uk.co.hexeption.api.io.LoggerHelper;
import uk.co.hexeption.api.mod.Mod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hexeption on 16/01/2017.
 */
public class ModuleManager {

    private static ModuleManager instance;

    private final List<Mod> mods;

    public ModuleManager() {

        mods = new LinkedList<Mod>();

    }

    /**
     * Adds a module/s to the list.
     *
     * @param mods
     */
    public void addModules(final Mod... mods) {

        synchronized (this.mods) {
            for (final Mod mod : mods) {
                this.mods.add(mod);
            }
        }
    }

    /**
     * @param clazz
     * @param <T>
     * @return A Mod by a class file
     */
    public <T extends Mod> T getModuleByClass(final Class<T> clazz) {

        synchronized (this.mods) {
            for (final Mod mod : mods) {
                if (mod.getClass().equals(clazz)) {
                    return (T) mod;
                }
            }
        }

        LoggerHelper.warn(String.format("Mod %s not found by class, returning null!", clazz.getCanonicalName()));
        return null;
    }

    /**
     * @param name
     * @param <T>
     * @return A Mod by name
     */
    public <T extends Mod> T getModuleByName(final String name) {

        synchronized (this.mods) {
            for (final Mod mod : mods) {
                if (mod.getName().replaceAll(" ", "").toLowerCase().equals(name.toLowerCase())) {
                    return (T) mod;
                }
            }
        }

        LoggerHelper.warn(String.format("Mod %s not found by name, returning null!", name));
        return null;
    }

    public List<Mod> getMods() {

        synchronized (this.mods) {
            return mods;
        }
    }


}
