package es.upm.estsiinf.myapplication.Models.SearchModels;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public String image;
    @SerializedName("imageType")
    public String imageType;

    private Bitmap imageBmp;

    public Result(int id, String title, String image, String imageType){
        this.id=id;
        this.title=title;
        this.image=image;
        this.imageType=imageType;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public String getImageType() {
        return imageType;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }


    public Bitmap getImageBmp (){ return imageBmp;}
    public void setImageBmp (Bitmap imageBmp){
        this.imageBmp = imageBmp;
    }


}


