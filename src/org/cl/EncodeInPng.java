package org.cl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncodeInPng {
    public void encodeAndWrite(File source, File target) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(source);
        if (source.length() > Integer.MAX_VALUE) {
            throw new RuntimeException("Input file too big");
        }
        int fileLength = (int) source.length();
        int sideSize = (int) (Math.sqrt((double) fileLength)) + 1;
        BufferedImage bufferedImage = new BufferedImage(sideSize, sideSize, BufferedImage.TYPE_INT_RGB);

        int diff = (sideSize * sideSize) - fileLength;
        for (int i = 0; i < diff; i++) {
            bufferedImage.setRGB(i, 0, diff);
        }
        int readInteger;
        int x = diff;
        int y = 0;
        while ((readInteger = fileInputStream.read()) != -1) {
            bufferedImage.setRGB(x, y, readInteger);
            if (x == (sideSize - 1)) {
                x = 0;
                y++;
            } else {
                x++;
            }
        }
        ImageIO.write(bufferedImage, "png", target);
        fileInputStream.close();
    }

    public void decodeAndWrite(File source, File target) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(source);
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        int padding = (short)bufferedImage.getRGB(0, 0);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int res;
                res = bufferedImage.getRGB(x, y);
                if (!((y == 0) && x < padding)) {
                    fileOutputStream.write(res);
                }
            }
        }
        fileOutputStream.close();
    }
}
