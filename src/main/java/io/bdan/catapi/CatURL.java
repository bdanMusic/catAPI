package io.bdan.catapi;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "width", "height", "averageColor", "name", "url" })
public class CatURL {
    private final String URL;
    private final String Name;
    private final int ID;
    private final String averageColor;
    private final int width;
    private final int height;

    public CatURL(String URL, String name, int id, String averageColor, int width, int height) {
        this.URL = URL;
        this.Name = name;
        this.ID = id;
        this.averageColor = averageColor;
        this.width = width;
        this.height = height;
    }

    public String getURL() {
        return URL;
    }

    public String getName() {
        return Name;
    }

    public int getID() {
        return ID;
    }

    public String getAverageColor() {
        return averageColor;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
