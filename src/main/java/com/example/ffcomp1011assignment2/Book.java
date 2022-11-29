package com.example.ffcomp1011assignment2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    @SerializedName("title")
    private String title;

    @SerializedName("edition_count")
    private Integer editionCount;

    @SerializedName("subject_facet")
    private ArrayList<String> subject;

    @SerializedName("publisher")
    private ArrayList<String> publishers;

    @SerializedName("number_of_pages_median")
    private Integer numberOfPages;

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

    @SerializedName("id_amazon")
    private ArrayList<String> amazonID;

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Book(Integer numberOfPages, ArrayList<String> amazonID, String key, String title, ArrayList<String> author, Integer editionCount, ArrayList<String> subject, ArrayList<String> publishers, ArrayList<String> language, ArrayList<String> seeds, Integer imageID) {
        setKey(key);
        setTitle(title);
        setAuthor(author);
        setEditionCount(editionCount);
        setSubject(subject);
        setPublishers(publishers);
        setLanguage(language);
        setSeeds(seeds);
        setImageID(imageID);
        setAmazonID(amazonID);
        setNumberOfPages(numberOfPages);

    }

    public ArrayList getAmazonID() {
        return amazonID;
    }

    public void setAmazonID(ArrayList amazonID) {
        this.amazonID = amazonID;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getAuthor() {
        if ( author != null){
            if (author.size() > 0){
                return Arrays.toString(author.toArray()).replace("[", "").replace("]", "");

            } else {
                return "N/A";
            }
        } else {
            return "N/A";
        }
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public String getLanguage() {
        if ( language != null){
            if (language.size() > 0){
                ArrayList<String> languageTransform  = new ArrayList<>();
                for (var text : language) {
                    text = text.substring(0,1).toUpperCase() + text.substring(1);
                    languageTransform.add(text);
                }
                return Arrays.toString(languageTransform.toArray()).replace("[", "").replace("]", "");
            } else {
                return "N/A";
            }
        } else {
            return "N/A";
        }

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEditionCount(Integer editionCount) {
        this.editionCount = editionCount;
    }

    public void setSubject(ArrayList<String> subject) {
        this.subject = subject;
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

    public Integer getEditionCount() {
        return editionCount;
    }

    public String getSubject() {
        if ( subject != null)
            if (subject.size() > 0)
                return subject.get(0);
            else
                return "N/A";
        else
            return "N/A";
    }

    public ArrayList<String> getPublishers() {
        return publishers;
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
