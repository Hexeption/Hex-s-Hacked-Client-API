package uk.co.hexeption.api.render;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class Scissor {

    private static boolean clip = false;

    public static boolean begin(int x1, int y1, int x2, int y2) {

        int xstore1 = x1;
        int ystore1 = y1;

        x1 = Math.min(x1, x2);
        y1 = Math.min(y1, y2);
        x2 = Math.min(x2, xstore1);
        y2 = Math.min(y2, ystore1);

        if (x1 == x2 || y1 == y2) {
            return false;
        }

        if (!clip) {
            clip = true;
            glScissor(x1, Display.getHeight() - y2, x2 - x1, y2 - y1);
            glEnable(GL_SCISSOR_TEST);
            return true;
        }

        return false;
    }

    public static void end() {

        if (clip) {
            glDisable(GL_SCISSOR_TEST);
            clip = false;
        }
    }

}
