package uk.co.hexeption.api.utils;

import net.minecraft.client.renderer.texture.TextureUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by Hexeption on 13/05/2017.
 */
public class TextureUtils {

    public static ByteBuffer[] getIcon(String clientName) {

        InputStream[] icons = new InputStream[]{
                TextureUtil.class.getResourceAsStream(String.format("/assets/%s/textures/icon/16.png", clientName.toLowerCase())),
                TextureUtil.class.getResourceAsStream(String.format("/assets/%s/textures/icon/32.png", clientName.toLowerCase())),
                TextureUtil.class.getResourceAsStream(String.format("/assets/%s/textures/icon/48.png", clientName.toLowerCase())),
                TextureUtil.class.getResourceAsStream(String.format("/assets/%s/textures/icon/64.png", clientName.toLowerCase())),
                TextureUtil.class.getResourceAsStream(String.format("/assets/%s/textures/icon/128.png", clientName.toLowerCase())),
        };

        ByteBuffer[] buffer = new ByteBuffer[icons.length];

        for (int i = 0; i < icons.length; i++) {
            try {
                buffer[i] = readImageToBuffer(icons[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return buffer;

    }

    private static ByteBuffer readImageToBuffer(InputStream inputStream) throws IOException {

        BufferedImage image = ImageIO.read(inputStream);

        int[] rgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        ByteBuffer buffer = ByteBuffer.allocate(rgb.length * 4);
        for (int color : rgb) {
            buffer.putInt(color << 8 | color >> 24 & 255);
        }

        buffer.flip();

        return buffer;
    }

}
