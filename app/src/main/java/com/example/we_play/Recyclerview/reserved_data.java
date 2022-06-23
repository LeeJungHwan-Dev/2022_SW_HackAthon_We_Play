package com.example.we_play.Recyclerview;

public class reserved_data {
    private String thumbnail;
    private String placeName;
    private String description;

    public reserved_data(String thumbnail, String placeName, String description) {
        this.thumbnail = thumbnail;
        this.placeName = placeName;
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}