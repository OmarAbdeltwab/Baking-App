package com.example.android.packing.app.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("ingredients")

    private List<Ingredient> ingredients = null;
    @SerializedName("steps")
    private List<Step> steps = null;
    @SerializedName("servings")
    private Integer servings;
    @SerializedName("image")
    private String image;

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

    public String getName() {
        return name;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setName(String name) {
//        this.name = name;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setIngredients(List<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public List<Step> getSteps() {
        return steps;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setSteps(List<Step> steps) {
//        this.steps = steps;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public Integer getServings() {
//        return servings;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setServings(Integer servings) {
//        this.servings = servings;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public String getImage() {
        return image;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setImage(String image) {
//        this.image = image;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

}