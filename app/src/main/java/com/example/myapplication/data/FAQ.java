package com.example.myapplication.data;

public class FAQ {

    String heading;
    String briefNews;
    int titleImage;
    boolean visibility;

    public FAQ(String heading, String briefNews, int titleImage){
        this.heading = heading;
        this.briefNews = briefNews;
        this.titleImage = titleImage;
        this.visibility = false;
    }

    public boolean isVisibility(){
        return visibility;
    }

    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

}
