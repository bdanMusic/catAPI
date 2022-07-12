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

    int img = getImgNumber();
    private int getImgNumber() {
        FileManager nmMan = new FileManager();
        return nmMan.readImgNumber();
    }

    private String[] nameList = getCatNames();
    private static String[] getCatNames() {
        FileManager nmMan = new FileManager();
        return nmMan.readNames();
    }


    public static void main(String[] args) {
        SpringApplication.run(CatApiApplication.class, args);
    }

    @GetMapping("")
    public CatURL catURL() {
        int i = random();
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
            String url = String.format(template, i);
            return new CatURL(url,getName(i-1),i,getColor(url));
        } catch (Exception e) {
            return catURL();
        }
    }


    private String getColor(String phat) {
        try {
            URL jtUrl   = new URL(phat);
            return ImgColor.getAverageColor(jtUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String getName(int i) {
        nameList = getCatNames();
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