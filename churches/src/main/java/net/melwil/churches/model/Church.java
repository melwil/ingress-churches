package net.melwil.churches.model;

import java.util.ArrayList;
import java.util.List;

public class Church {

    private String lat;
    private String lng;
    private String name;
    private String url;

    private List<String> images = new ArrayList<String>();


    public Church() {}
    
    public Church(String url) {
        this.url = url;
    }


    public String getLat() {
        return lat;
    }


    public void setLat(String lat) {
        this.lat = lat;
    }


    public String getLng() {
        return lng;
    }


    public void setLng(String lng) {
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


    @Override
    public int hashCode() {
        return ("" + lat + lng + name + url).hashCode();
    }
}
