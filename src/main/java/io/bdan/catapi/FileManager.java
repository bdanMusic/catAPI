package io.bdan.catapi;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private File nameFile;
    private String[] nameList;

    private File imgNumberFile;
    private int imgNumber;

    public FileManager() {
        //nameFile = new File("src/main/resources/names.txt");
        nameFile = new File("names.txt");
        nameList = readNames();
        imgNumberFile = new File("imgNumber.txt");
        imgNumber = readImgNumber();
    }

    public int readImgNumber() {
        try {
            String imgNumberString = new String(Files.readAllBytes(Paths.get(imgNumberFile.getPath())), Charset.defaultCharset());
            return Integer.parseInt(imgNumberString);
        } catch (Exception e) {
            return 0;
        }
    }

    public String[] readNames() {
        String[] ret = new String[0];
        try {
            String[] lines = new String(Files.readAllBytes(Paths.get(nameFile.getAbsolutePath())), Charset.defaultCharset()).split("\n");
            ret = lines;
        } catch (Exception e) {
            System.out.println("Error reading names.txt");
        }
        return ret;
    }

}
