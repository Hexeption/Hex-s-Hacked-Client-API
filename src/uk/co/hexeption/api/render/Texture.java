package uk.co.hexeption.api.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class Texture {

    private final ResourceLocation texture;

    public Texture(String textureLocation) {

        texture = new ResourceLocation(textureLocation);
        bindTexture(texture);
    }

    public void render(float x, float y, float width, float height) {

        render(x, y, width, height, 0F, 0F, 1F, 1F);
    }

    public void render(float x, float y, float width, float height, float u, float v, float t, float s) {

        bindTexture(texture);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer renderer = tessellator.getBuffer();
        renderer.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX);
        renderer.pos(x + width, y, 0F).tex(t, v).endVertex();
        renderer.pos(x, y, 0F).tex(u, v).endVertex();
        renderer.pos(x, y + height, 0F).tex(u, s).endVertex();
        renderer.pos(x, y + height, 0F).tex(u, s).endVertex();
        renderer.pos(x + width, y + height, 0F).tex(t, s).endVertex();
        renderer.pos(x + width, y, 0F).tex(t, v).endVertex();
        tessellator.draw();
    }

    private void bindTexture(ResourceLocation textures) {

        Minecraft.getMinecraft().getTextureManager().bindTexture(textures);
        GlStateManager.enableTexture2D();
    }

    public String toString() {

        return texture.getResourcePath();
    }
}
