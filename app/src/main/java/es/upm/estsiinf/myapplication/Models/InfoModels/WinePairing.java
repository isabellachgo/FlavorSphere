package es.upm.estsiinf.myapplication.Models.InfoModels;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WinePairing {
    @SerializedName("pairedWines")
    public ArrayList<Object> pairedWines;
    @SerializedName("pairingText")
    public String pairingText;
    @SerializedName("productMatches")
    public ArrayList<Object> productMatches;
}
