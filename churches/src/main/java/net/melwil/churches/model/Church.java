package model;

import java.util.ArrayList;
import java.util.List;

public class Church {

    private double xcoord;
    private double ycoord;
    private String name;
    private String url;

    private List<String> images = new ArrayList<String>();


    public Church() {}
    
    public Church(String url) {
        this.url = url;
    }


    public double getXcoord() {
        return xcoord;
    }


    public void setXcoord(double xcoord) {
        this.xcoord = xcoord;
    }


    public double getYcoord() {
        return ycoord;
    }


    public void setYcoord(double ycoord) {
        this.ycoord = ycoord;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<String> getImages() {
        return images;
    }


    public void setImages(List<String> images) {
        this.images = images;
    }
}
