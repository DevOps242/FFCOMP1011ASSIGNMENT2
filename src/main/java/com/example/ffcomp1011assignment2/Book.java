package com.example.ffcomp1011assignment2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Book {
    @SerializedName("title")
    private String title;

    @SerializedName("edition_count")
    private int editionCount;

    @SerializedName("publish_date")
    private ArrayList<String> publishDate;

    @SerializedName("publisher")
    private ArrayList<String> publishers;


    @SerializedName("author_name")
    private ArrayList<String> author;

    @SerializedName("language")
    private ArrayList<String> language;

    @SerializedName("key")
    private String key;

    @SerializedName("seed")
    private ArrayList<String> seeds;

    @SerializedName("cover_i")
    private Integer imageID;

    public Book(String key, String title, ArrayList<String> author, int editionCount, ArrayList<String> publishDate, ArrayList<String> publishers, ArrayList<String> language, ArrayList<String> seeds, Integer imageID) {
        setKey(key);
        setTitle(title);
        setAuthor(author);
        setEditionCount(editionCount);
        setPublishDate(publishDate);
        setPublishers(publishers);
        setLanguage(language);
        setSeeds(seeds);
        setImageID(imageID);

    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public ArrayList<String> getLanguage() {
        return language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEditionCount(int editionCount) {
        this.editionCount = editionCount;
    }

    public void setPublishDate(ArrayList<String> publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublishers(ArrayList<String> publishers) {
        this.publishers = publishers;
    }

    public void setLanguage(ArrayList<String> language) {
        this.language = language;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSeeds(ArrayList<String> seeds) {
        this.seeds = seeds;
    }

    public String getTitle() {
        return title;
    }

    public int getEditionCount() {
        return editionCount;
    }

    public ArrayList<String> isPublishDate() {
        return publishDate;
    }

    public ArrayList<String> getPublishers() {
        return publishers;
    }

    public ArrayList<String> isLanguage() {
        return language;
    }

    public String getKey() {
        return key;
    }

    public ArrayList<String> getSeeds() {
        return seeds;
    }

    @Override
    public String toString() {
        return String.format("Title: %s", title);
    }
}
