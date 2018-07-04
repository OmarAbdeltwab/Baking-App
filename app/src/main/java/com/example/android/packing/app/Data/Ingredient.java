package com.example.android.packing.app.Data;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("quantity")

    private double quantity;
    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setQuantity(double quantity) {
//        this.quantity = quantity;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public String getMeasure() {
        return measure;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setMeasure(String measure) {
//        this.measure = measure;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

    public String getIngredient() {
        return ingredient;
    }

// --Commented out by Inspection START (29-May-18 4:00 AM):
//    public void setIngredient(String ingredient) {
//        this.ingredient = ingredient;
//    }
// --Commented out by Inspection STOP (29-May-18 4:00 AM)

}