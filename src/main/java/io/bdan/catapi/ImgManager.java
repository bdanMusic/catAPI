package io.bdan.catapi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgManager {
    private BufferedImage bi;
    private int width;
    private int height;


    public ImgManager(URL imgUrl) throws MalformedURLException, IOException {
        bi = ImageIO.read(imgUrl);
        width = bi.getWidth();
        height = bi.getHeight();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public String getAverageColor() throws IOException {
        Color cl = this.getAverColor();
        return this.colToHex(cl);
    }
    private String colToHex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    private Color getAverColor() {
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
