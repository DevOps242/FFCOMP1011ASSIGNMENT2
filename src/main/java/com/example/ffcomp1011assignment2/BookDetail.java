package com.example.ffcomp1011assignment2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class BookDetail {

    @SerializedName("title")
    private String title;

    @SerializedName("key")
    private String key;

    @SerializedName("publishers")
    private ArrayList<String> publishers;

    @SerializedName("number_of_pages")
    private Integer numberOfPages;

    @SerializedName("authors")
    private String author;

    @SerializedName("covers")
    private ArrayList<Integer> imageUri;

    @SerializedName("publish_date")
    private String publishDate;

    @SerializedName("revision")
    private Integer version;

    @SerializedName("source_records")
    private ArrayList<String> sourceUri;

//
//    {"publishers": ["Harpercollins Pub Ltd"], "number_of_pages": 1200, "classifications": {}, "source_records": ["amazon:0261102303"],
//        "title": "Lord of the Rings", "identifiers": {}, "last_modified": {"type": "/type/datetime", "value": "2019-11-30T15:34:32.189572"},
//        "covers": [8447430], "created": {"type": "/type/datetime", "value": "2019-03-19T11:25:57.942886"}, "isbn_13": ["9780261102309"],
//        "full_title": "Lord of the Rings", "isbn_10": ["0261102303"], "publish_date": "Aug 31, 1991", "key": "/books/OL26793280M",
//            "authors": [{"key": "/authors/OL26320A"}], "latest_revision": 3, "works": [{"key": "/works/OL27448W"}], "type": {"key": "/type/edition"}, "revision": 3}

//    public BookDetail(String key, String title, ArrayList<String> author, int editionCount, ArrayList<String> publishDate, ArrayList<String> publishers, ArrayList<String> language, ArrayList<String> seeds, Integer imageID, Integer numberOfPages, String publishDate1, Integer version, ArrayList<String> sourceUri) {
//        super(key, title, author, editionCount, publishDate, publishers, language, seeds, imageID);setNumberOfPages(numberOfPages);
//        setPublishDate(publishDate);
//        setVersion(version);
//        setSourceUri(sourceUri);
//    }
    public BookDetail(String title, String key, ArrayList<String> publishers, Integer numberOfPages, String author, ArrayList<Integer> imageUri, String publishDate, Integer version, ArrayList<String> sourceUri) {

        setTitle(title);
        setKey(key);setPublishers(publishers);
        setNumberOfPages(numberOfPages);
        setAuthor(author);
        setImageUri(imageUri);
        setPublishDate(publishDate);
        setVersion(version);
        setSourceUri(sourceUri);
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

    public ArrayList<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(ArrayList<String> publishers) {
        this.publishers = publishers;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<Integer> getImageUri() {
        return imageUri;
    }

    public void setImageUri(ArrayList<Integer> imageUri) {
        this.imageUri = imageUri;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public ArrayList<String> getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(ArrayList<String> sourceUri) {
        this.sourceUri = sourceUri;
    }

    @Override
    public String toString(){
        return String.format("Title: %s", title);
    }
}
