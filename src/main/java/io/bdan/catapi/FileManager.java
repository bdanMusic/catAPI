package io.bdan.catapi;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class FileManager {
    private File nameFile;
    private String[] imgList;

    private File imgNumberFile;
    private int imgNumber;

    public String[] getImgList() {
        return imgList;
    }

    public int getImgNumber() {
        return imgNumber;
    }

    public FileManager() {
        //nameFile = new File("src/main/resources/names.txt");
        nameFile = new File("names.txt");
        imgList = readNames();
        imgNumberFile = new File("imgNumber.txt");
        imgNumber = readImgNumber();
        Runnable r = new Runnable() {
            public void run() {
                fileWatch(getParent());;
            }
        };
        new Thread(r).start();
        System.err.println("WhatchDog gestartet");
    }

    private String getParent() {
        String ret = nameFile.getAbsolutePath();
        ret = ret.substring(0, ret.lastIndexOf('/'));
        return ret;
    }
    private void fileWatch(String pt) {
        try {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(pt);
        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
        boolean poll = true;
        while (poll) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                System.err.println("Event " + event.kind() + " File " + event.context().toString() + " ausgeführt");
                if (kind == ENTRY_MODIFY) {
                    if (event.context().toString().equals("names.txt")) {
                        imgList = readNames();
                    } else if (event.context().toString().equals("imgNumber.txt")) {
                        imgNumber = readImgNumber();
                    }
                } else if (kind == ENTRY_DELETE) {
                    System.out.println("Error " + event.context().toString() + " was deleted");
                }
            }
            poll = key.reset();
        }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    private int readImgNumber() {
        try {
            String imgNumberString = new String(Files.readAllBytes(Paths.get(imgNumberFile.getAbsolutePath())), Charset.defaultCharset());
            return Integer.parseInt(imgNumberString);
        } catch (Exception e) {
            return 0;
        }
    }

    private String[] readNames() {
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
