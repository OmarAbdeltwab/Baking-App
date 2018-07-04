package com.example.android.packing.app.Data;

import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("id")
    private Integer id;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("videoURL")
    private String videoURL;
    @SerializedName("thumbnailURL")
    private String thumbnailURL;

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public Integer getId() {
//        return id;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setId(Integer id) {
//        this.id = id;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public String getShortDescription() {
        return shortDescription;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public String getDescription() {
        return description;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setDescription(String description) {
//        this.description = description;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public String getVideoURL() {
        return videoURL;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setVideoURL(String videoURL) {
//        this.videoURL = videoURL;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public String getThumbnailURL() {
        return thumbnailURL;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setThumbnailURL(String thumbnailURL) {
//        this.thumbnailURL = thumbnailURL;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

}