package com.example.ffcomp1011assignment2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIResponse {
    @SerializedName("docs")
    private Book[] docs;

    @SerializedName("numFound")
    private int totalResults;

    @SerializedName("start")
    private int start;

    @SerializedName("numFoundExact")
    private boolean numFoundExact;

    public APIResponse(Book[] docs, int totalResults, boolean numFoundExact, int start) {
        System.out.println(docs);
        System.out.println(totalResults);
        setDocs(docs);
        setTotalResults(totalResults);
        this.numFoundExact = numFoundExact;
        this.start = start;

    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public Book[] getDocs() {
        return docs;
    }

    public void setDocs(Book[] docs) {
        this.docs = docs;
    }

    public List<Book> getBooks() {
        return Arrays.asList(docs);
    }


}
