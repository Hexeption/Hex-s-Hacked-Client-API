package uk.co.hexeption.api.mod;

import net.minecraft.client.Minecraft;
import uk.co.hexeption.api.io.LoggerHelper;
import uk.co.hexeption.api.managers.EventManager;

/**
 * Created by Hexeption on 16/01/2017.
 */
public abstract class Mod {


    public Minecraft mc = Minecraft.getMinecraft();

    private String name = getClass().getAnnotation(ModInfo.class).name();

    private String description = getClass().getAnnotation(ModInfo.class).description();

    private Category category = getClass().getAnnotation(ModInfo.class).category();

    private boolean visible = getClass().getAnnotation(ModInfo.class).visible();

    private boolean enbled = false;

    private int keyBind = getClass().getAnnotation(ModInfo.class).bind();

    public void setEnbled(boolean enbled) {

        if (enbled != this.enbled) {
            this.enbled = enbled;
            if (enbled) {
                onEnable();
                LoggerHelper.info("Enabled " + this.name);
                EventManager.register(this);
            } else {
                onDisable();
                LoggerHelper.info("Disabled " + this.name);
                EventManager.unregister(this);
            }
        }
    }

    public void toggle() {

        setEnbled(!isEnbled());
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

    public boolean isEnbled() {

        return enbled;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public boolean isVisible() {

        return visible;
    }

    public void setVisible(boolean visible) {

        this.visible = visible;
    }

    public int getKeyBind() {

        return keyBind;
    }

    public void setKeyBind(int keyBind) {

        this.keyBind = keyBind;
    }
}
