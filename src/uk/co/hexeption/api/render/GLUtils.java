package uk.co.hexeption.api.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 17/01/2017.
 */
public final class GLUtils {

    private static final Random random = new Random();

    public static List<Integer> vbos = new ArrayList<>(), textures = new ArrayList<>();

    public static void glScissor(int[] rect) {

        glScissor(rect[0], rect[1], rect[0] + rect[2], rect[1] + rect[3]);
    }

    public static void glScissor(float x, float y, float x1, float y1) {

        int scaleFactor = getScaleFactor();
        GL11.glScissor((int) (x * scaleFactor), (int) (Minecraft.getMinecraft().displayHeight - (y1 * scaleFactor)), (int) ((x1 - x) * scaleFactor), (int) ((y1 - y) * scaleFactor));
    }

    /**
     * @return The scale factor used by the play's screen gui scale
     */
    public static int getScaleFactor() {

        int scaleFactor = 1;
        boolean isUnicode = Minecraft.getMinecraft().isUnicode();
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;

        if (guiScale == 0) {
            guiScale = 1000;
        }

        while (scaleFactor < guiScale && Minecraft.getMinecraft().displayWidth / (scaleFactor + 1) >= 320 && Minecraft.getMinecraft().displayHeight / (scaleFactor + 1) >= 240) {
            scaleFactor++;
        }

        if (isUnicode && scaleFactor % 2 != 0 && scaleFactor != 1) {
            scaleFactor--;
        }

        return scaleFactor;

    }

    /**
     * @return Mouse X cord.
     */
    public static int getMouseX() {

        return (Mouse.getX() * getScreenWidth() / Minecraft.getMinecraft().displayWidth);
    }

    /**
     * @return Mouse Y cord.
     */
    public static int getMouseY() {

        return (getScreenHeight() - Mouse.getY() * getScreenHeight() / Minecraft.getMinecraft().displayWidth - 1);
    }

    /**
     * @return Screen width with gui scale.
     */
    public static int getScreenWidth() {

        return Minecraft.getMinecraft().displayWidth / getScaleFactor();
    }

    /**
     * @return Screen height with gui scale.
     */
    public static int getScreenHeight() {

        return Minecraft.getMinecraft().displayHeight / getScaleFactor();
    }

    public static int genVBO() {

        int id = GL15.glGenBuffers();
        vbos.add(id);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
        return id;
    }

    public static int getTexture() {

        int textureID = glGenTextures();
        textures.add(textureID);
        return textureID;
    }

    public static void cleanup() {

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        glBindTexture(GL_TEXTURE_2D, 0);

        for (int vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }

        for (int texture : textures) {
            glDeleteTextures(texture);
        }

    }

    /**
     * Colors
     */

    public static void glColor(float red, float green, float blue, float alpha) {

        GlStateManager.color(red, green, blue, alpha);
    }

    public static void glColor(Color color) {

        GlStateManager.color((float) color.getRed() / 255F, (float) color.getGreen() / 255F, (float) color.getBlue() / 255F, (float) color.getAlpha() / 255F);
    }

    public static void glColor(int color) {

        GlStateManager.color((float) (color >> 16 & 255) / 255F, (float) (color >> 8 & 255) / 255F, (float) (color & 255) / 255F, (float) (color >> 24 & 255) / 255F);
    }

    public static Color getHSBColor(float hue, float sturation, float luminance) {

        return Color.getHSBColor(hue, sturation, luminance);
    }

    public static Color getRandomColor(int saturationRandom, float luminance) {

        final float hue = random.nextFloat();
        final float saturation = (random.nextInt(saturationRandom) + (float) saturationRandom) / (float) saturationRandom + (float) saturationRandom;
        return getHSBColor(hue, saturation, luminance);
    }

    public static Color getRandomColor() {

        return getRandomColor(1000, 0.6f);
    }


}
