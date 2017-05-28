package uk.co.hexeption.api.render;

import net.minecraft.client.renderer.GlStateManager;

import static org.lwjgl.opengl.GL11.*;

public class Stencil {

    public static void write() {

        glClearStencil(0);
        glClear(GL_STENCIL_BUFFER_BIT);
        glEnable(GL_STENCIL_TEST);
        glStencilFunc(GL_ALWAYS, 1, 0xffff);
        glStencilOp(GL_KEEP, GL_KEEP, GL_REPLACE);
        GlStateManager.colorMask(false, false, false, false);
    }

    public static void erase() {

        glStencilFunc(GL_NOTEQUAL, 1, 0xffff);
        glStencilFunc(GL_KEEP, GL_KEEP, GL_REPLACE);
        GlStateManager.colorMask(true, true, true, true);
    }

    public static void clear() {

        glDisable(GL_STENCIL_TEST);
    }


}
