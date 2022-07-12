package io.bdan.catapi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgColor {
    BufferedImage bi;

    private ImgColor (URL imgUrl) throws MalformedURLException, IOException {
        bi = ImageIO.read(imgUrl);
    }

    public static String getAverageColor(URL imgUrl) throws IOException {
        ImgColor imGl = new ImgColor(imgUrl);
        Color cl = imGl.getAverageColor();
        return imGl.colToHex(cl);

    }
    private String colToHex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    private Color getAverageColor() {
        int step = 5;

        int sampled = 0;
        long sumr = 0, sumg = 0, sumb = 0;
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                if (x % step == 0 && y % step == 0) {
                    Color pixel = new Color(bi.getRGB(x, y));
                    sumr += pixel.getRed();
                    sumg += pixel.getGreen();
                    sumb += pixel.getBlue();
                    sampled++;
                }
            }
        }
        int dim = bi.getWidth()*bi.getHeight();
        return new Color(Math.round(sumr / sampled), Math.round(sumg / sampled), Math.round(sumb / sampled));
    }
}
