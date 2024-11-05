package es.upm.estsiinf.myapplication.Models.InfoModels;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ExtendedIngredient {
    @SerializedName("id")
    public int id;
    @SerializedName("aisle")
    public String aisle;
    @SerializedName("image")
    public String image;
    @SerializedName("consistency")
    public String consistency;
    @SerializedName("name")                                             //---------------------
    public String name;
    @SerializedName("nameClean")
    public String nameClean;
    @SerializedName("original")
    public String original;
    @SerializedName("originalName")
    public String originalName;
    @SerializedName("amount")                                             //---------------------
    public double amount;
    @SerializedName("unit")
    public String unit;
    @SerializedName("meta")                                             //---------------------
    public ArrayList<String> meta;
    @SerializedName("measures")
    public Measures measures;
}
