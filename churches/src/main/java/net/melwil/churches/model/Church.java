package net.melwil.churches.model;

import java.util.ArrayList;
import java.util.List;

public class Church {

    private double lat;
    private double lng;
    private String name;
    private String url;

    private List<String> images = new ArrayList<String>();


    public Church() {}
    
    public Church(String url) {
        this.url = url;
    }


    public double getLat() {
        return lat;
    }


    public void setLat(double lat) {
        this.lat = lat;
    }


    public double getLng() {
        return lng;
    }


    public void setLng(double lng) {
        this.lng = lng;
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
