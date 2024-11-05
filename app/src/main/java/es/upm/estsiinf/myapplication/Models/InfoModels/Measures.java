package es.upm.estsiinf.myapplication.Models.InfoModels;


import com.google.gson.annotations.SerializedName;

public class Measures {
    @SerializedName("us")
    public Us us;
    @SerializedName("metric")
    public Metric metric;
}
