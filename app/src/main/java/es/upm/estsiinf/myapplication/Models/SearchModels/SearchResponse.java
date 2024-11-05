package es.upm.estsiinf.myapplication.Models.SearchModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {
    @SerializedName("results")
    public ArrayList<Result> recetas;
    @SerializedName("offset")
    public int offset;
    @SerializedName("number")
    public int number;
    @SerializedName("totalResults")
    public int totalResults;
}
