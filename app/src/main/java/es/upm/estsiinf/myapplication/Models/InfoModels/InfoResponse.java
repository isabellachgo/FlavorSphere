package es.upm.estsiinf.myapplication.Models.InfoModels;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class InfoResponse {
    @SerializedName("vegetarian")
    public boolean vegetarian;
    @SerializedName("vegan")
    public boolean vegan;
    @SerializedName("glutenFree")
    public boolean glutenFree;
    @SerializedName("dairyFree")
    public boolean dairyFree;
    @SerializedName("veryHealthy")
    public boolean veryHealthy;
    @SerializedName("cheap")
    public boolean cheap;
    @SerializedName("veryPopular")
    public boolean veryPopular;
    @SerializedName("sustainable")
    public boolean sustainable;
    @SerializedName("lowFodmap")
    public boolean lowFodmap;
    @SerializedName("weightWatcherSmartPoints")
    public int weightWatcherSmartPoints;
    @SerializedName("gaps")
    public String gaps;
    @SerializedName("preparationMinutes")
    public int preparationMinutes;
    @SerializedName("cookingMinutes")
    public int cookingMinutes;
    @SerializedName("aggregateLikes")                                 //--------------
    public int aggregateLikes;
    @SerializedName("healthScore")
    public int healthScore;
    @SerializedName("creditsText")
    public String creditsText;
    @SerializedName("sourceName")
    public String sourceName;
    @SerializedName("pricePerServing")
    public double pricePerServing;
    @SerializedName("extendedIngredients")                                //--------------
    public ArrayList<ExtendedIngredient> extendedIngredients;
    @SerializedName("id")
    public int id;
    @SerializedName("title")                                              //--------------
    public String title;
    @SerializedName("readyInMinutes")                                      //--------------
    public int readyInMinutes;
    @SerializedName("servings")                                          //--------------
    public int servings;
    @SerializedName("soureUrl")
    public String sourceUrl;
    @SerializedName("image")                                              //--------------
    public String image;
    @SerializedName("imageType")
    public String imageType;
    @SerializedName("summary")
    public String summary;
    @SerializedName("cuisines")
    public ArrayList<Object> cuisines;
    @SerializedName("dishTypes")
    public ArrayList<String> dishTypes;
    @SerializedName("diets")
    public ArrayList<String> diets;
    @SerializedName("occasions")
    public ArrayList<Object> occasions;
    @SerializedName("winePairing")
    public WinePairing winePairing;
    @SerializedName("instructions")                                  //--------------
    public String instructions;
    @SerializedName("analyzedInstructions")
    public ArrayList<Object> analyzedInstructions;
    @SerializedName("originalId")
    public Object originalId;
    @SerializedName("spoonacularScore")                                    //--------------
    public double spoonacularScore;

}