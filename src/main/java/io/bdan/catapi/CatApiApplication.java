package io.bdan.catapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

@RestController
@SpringBootApplication
public class CatApiApplication {
    private static final String template = "https://data.bdan.io/catpics/%s.jpg";
    private static final FileManager nmMan = new FileManager();

    private ImgManager imgMan;

    public static void main(String[] args) {
        SpringApplication.run(CatApiApplication.class, args);
    }

    @GetMapping("")
    public CatURL catURL() {
        int i = random();
        String url = String.format(template, i);
        return new CatURL(url,getName(i-1),i,getColor(url),imgMan.getWidth(),imgMan.getHeight());
    }

    @GetMapping("/img")
    public CatURL catURL(@RequestParam(value = "id", defaultValue = "0")String id, @RequestParam(value = "legacy", defaultValue = "false")boolean legacy) {
        try {
            int img = nmMan.getImgNumber();
            int i = Integer.parseInt(id);
            if (legacy) {
                if (i > img || i < 1) {
                    return catURL();
                }
            }
            String url = String.format(template, i);
            return new CatURL(url,getName(i-1),i,getColor(url),imgMan.getWidth(),imgMan.getHeight());
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/urlList")
    public String[] urlList() {
        String[] ret = new String[nmMan.getImgNumber()];
        for (int i = 0; i < nmMan.getImgNumber(); i++) {
            ret[i] = String.format(template, i+1);
        }
        return ret;
    }

    @GetMapping("/nameList")
    public String[] names() {
        return nmMan.getImgList();
    }

    @GetMapping("/idCount")
    public int imgNumber() {
        return nmMan.getImgNumber();
    }

    private String getColor(String phat) {
        try {
            URL jtUrl = new URL(phat);
            imgMan = new ImgManager(jtUrl);
            return imgMan.getAverageColor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getName(int i) {
        int arrayLng = nmMan.getImgList().length;
        String ret = "noName";
        if (i < arrayLng) {
            ret = nmMan.getImgList()[i];
        }
        return ret;
    }

    private int random() {
        Random rand = new Random();
        return rand.nextInt(nmMan.getImgNumber())+1;
    }

}

