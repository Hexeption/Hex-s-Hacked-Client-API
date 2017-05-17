package uk.co.hexeption.api.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 17/01/2017.
 */
public class RenderUtils {

    /**
     * Draws a rectangle at the given cords
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public static void drawReact(float x, float y, float width, float height, int color) {

        glDisable(GL_TEXTURE_2D);
        glEnable(GL_LINE_SMOOTH);
        glPushMatrix();
        GLUtils.glColor(color);
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x, y + height);
        glVertex2f(x + width, y + height);
        glVertex2f(x + width, y);
        glEnd();
        glPopMatrix();
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_LINE_SMOOTH);
    }

    /**
     * Draw a Gradient Rect at the given cords
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param startColor
     * @param endColor
     */
    //TODO:Fix
    public static void drawGradientRect(float x, float y, float width, float height, int startColor, int endColor) {

        float startColor_Alpha = (float) (startColor >> 24 & 255) / 255F;
        float startColor_Red = (float) (startColor >> 16 & 255) / 255F;
        float startColor_Green = (float) (startColor >> 8 & 255) / 255F;
        float startColor_Blue = (float) (startColor & 255) / 255F;
        float endColor_Alpha = (float) (endColor >> 24 & 255) / 255F;
        float endColor_Red = (float) (endColor >> 16 & 255) / 255F;
        float endColor_Green = (float) (endColor >> 8 & 255) / 255F;
        float endColor_Blue = (float) (endColor & 255) / 255F;

        glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL_FLAT);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexBuffer = tessellator.getBuffer();
        vertexBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexBuffer.pos((double) x + width, (double) y, (double) 0).color(startColor_Red, startColor_Green, startColor_Blue, startColor_Alpha);
        vertexBuffer.pos((double) x, (double) y, (double) 0).color(startColor_Red, startColor_Green, startColor_Blue, startColor_Alpha);
        vertexBuffer.pos((double) x, (double) y + height, (double) 0).color(endColor_Red, endColor_Green, endColor_Blue, endColor_Alpha);
        vertexBuffer.pos((double) x + width, (double) y + height, (double) 0).color(endColor_Red, endColor_Green, endColor_Blue, endColor_Alpha);
        tessellator.draw();
        GlStateManager.shadeModel(GL_FLAT);
        glDisable(GL_BLEND);
        glEnable(GL_ALPHA);
        glEnable(GL_TEXTURE_2D);
    }

    /**
     * Draws a rect with a border.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param lineWidth
     * @param insideColor
     * @param borderColor
     */
    public static void drawBorderedRect(float x, float y, float width, float height, float lineWidth, int insideColor, int borderColor) {

        drawReact(x, y, width, height, insideColor);

        glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glLineWidth(lineWidth);
        glBegin(GL_LINE_LOOP);
        GLUtils.glColor(borderColor);
        glVertex3f(x, y, 1);
        glVertex3f(x + width, y, 1);
        glVertex3f(x + width, y + height, 1);
        glVertex3f(x, y + height, 1);
        glEnd();
        glDisable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);
    }

    /**
     * Draws a Circle with a radius
     *
     * @param x
     * @param y
     * @param radius
     * @param color
     */
    //TODO: Fix
    public static void drawCircle(float x, float y, float radius, int color) {

        glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glEnable(GL_POLYGON_SMOOTH);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glBegin(GL_POLYGON);
        for (int i = 0; i <= 360; i++) {
            double x2 = Math.sin(((i * Math.PI) / 180)) * radius;
            double y2 = Math.cos(((i * Math.PI) / 180)) * radius;
            glVertex3d(x + x2, y + y2, 0);
        }
        glEnd();
        glDisable(GL_POLYGON_SMOOTH);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }


}

