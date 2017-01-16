package uk.co.hexeption.api.mod;

/**
 * Created by Hexeption on 16/01/2017.
 */
public enum Category {

    MOVEMENT("Movement", 0xF8FF1F),
    PLAYER("Player", 0x00FFEC),
    RENDER("Render", 0x48FF1F),
    WORLD("World", 0xCF1FFF),
    COMBAT("Combat", 0x3ABDFF),
    MISC("Misc", 0xFFC100),
    GUI("Gui", 0xE800D5);

    public final String name;

    public final int color;

    Category(String name, int color) {

        this.name = name;
        this.color = color;
    }

    public String getName() {

        return name;
    }

    public int getColor() {

        return color;
    }
}
