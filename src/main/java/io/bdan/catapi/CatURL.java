package io.bdan.catapi;

public class CatURL {
    private final String URL;
    private final String Name;
    private final int ID;
    private final String averageColor;

    public CatURL(String URL, String name, int id, String averageColor) {
        this.URL = URL;
        this.Name = name;
        this.ID = id;
        this.averageColor = averageColor;
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
}
