package io.bdan.catapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

@RestController
@SpringBootApplication
public class CatApiApplication {
    private static final String template = "https://data.bdan.io/catpics/%s.jpg";

    int img = getImgNumber();
    private int getImgNumber() {
        NameManager nmMan = new NameManager();
        return nmMan.readImgNumber();
    }

    private String[] nameList = getCatNams();
    private static String[] getCatNams() {
        NameManager nmMan = new NameManager();
        return nmMan.readNames();
    }


    public static void main(String[] args) {
        SpringApplication.run(CatApiApplication.class, args);
    }

    @GetMapping("")
    public CatURL catURL() {
        int i = random();
        //return new CatURL(String.format(template, i),getName(i-1),i,getColor(i-1));
        String url = String.format(template, i);
        return new CatURL(url,getName(i-1),i,getColor(url));
    }

    @GetMapping("/img")
    public CatURL catURL(@RequestParam(value = "id", defaultValue = "0")String id) {
        try {
            int i = Integer.parseInt(id);
            if (i > img || i < 1) {
                return catURL();
            }
            // return new CatURL(String.format(template, i),getName(i-1),i,getColor(i-1));
            String url = String.format(template, i);
            return new CatURL(url,getName(i-1),i,getColor(url));
        } catch (Exception e) {
            return catURL();
        }
    }

    private String colToHex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    private String getColor(String phat) {
        try {
            URL jtUrl   = new URL(phat);
            BufferedImage image = ImageIO.read(jtUrl);
            Color color = getAverageColor(image);
            return  colToHex(color);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Color getAverageColor(BufferedImage bi) {
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
    private String getName(int i) {
        nameList = getCatNams();
        int arrayLng = nameList.length;
        String ret = "noName";
        if (i < arrayLng) {
            ret = nameList[i];
        }
        return ret;
    }

    private int random() {
        img = getImgNumber();
        Random rand = new Random();
        return rand.nextInt(img)+1;
    }
}