package com.example.ffcomp1011assignment2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookDetail {

    @SerializedName("title")
    private String title;

    @SerializedName("key")
    private String key;

    @SerializedName("covers")
    private ArrayList<Integer> imageUri;


    public BookDetail(String title, String key,  ArrayList<Integer> imageUri) {

        setTitle(title);
        setKey(key);
        setImageUri(imageUri);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<Integer> getImageUri() {
        return imageUri;
    }

    public void setImageUri(ArrayList<Integer> imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public String toString(){
        return String.format("Title: %s", title);
    }
}
