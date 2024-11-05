package es.upm.estsiinf.myapplication.Models.InfoModels;

import com.google.gson.annotations.SerializedName;

public class Us {
    @SerializedName("amount")
    public double amount;
    @SerializedName("unitShort")
    public String unitShort;
    @SerializedName("unitLong")
    public String unitLong;
}
