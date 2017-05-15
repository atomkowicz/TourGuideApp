package com.example.android.tourguideapp;

import android.content.Context;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Place implements Serializable {

    private double id;
    private String placeName;
    private int imageId = IMAGE_NOT_AVAILABLE;
    private static final int IMAGE_NOT_AVAILABLE = -1;

    public double getId() {
        return id;
    }

    public void setId(double pId) {
        id = pId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String pPlaceName) {
        placeName = pPlaceName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int pImageId) {
        imageId = pImageId;
    }

    public boolean hasImage() {
        return imageId != IMAGE_NOT_AVAILABLE;
    }

    public Place(double id, String placeName, int artistImageId) {
        this.id = id;
        this.placeName = placeName;
        this.imageId = artistImageId;
    }
}
