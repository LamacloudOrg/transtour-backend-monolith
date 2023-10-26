package com.transtour.travel.shared.util;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Component
public class ImageUtil {
    private static final int IMAGE_WIDTH =200;
    private static final int IMAGE_HEIGHT =200;

    public static byte[] resizePngImage(byte[] image, int width, int heigh) throws IOException {

        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(image));
        BufferedImage resizedImage = new BufferedImage(width,heigh,BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage,0,0,width,heigh,null);
        g.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage,"png",baos);

        return baos.toByteArray();
    }

}
