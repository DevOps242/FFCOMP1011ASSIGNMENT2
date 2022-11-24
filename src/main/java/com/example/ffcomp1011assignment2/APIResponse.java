package com.example.ffcomp1011assignment2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APIResponse {

    @SerializedName("numfound")
    private int numfound;

    @SerializedName("start")
    private int start;

    @SerializedName("numFoundExact")
    private boolean numFoundExact;

    @SerializedName("docs")
    private ArrayList<String> docs;


    public APIResponse(int numfound, int start, boolean numFoundExact, ArrayList docs) {
        setNumfound(numfound);
        setStart(start);
        setNumFoundExact(numFoundExact);
        setDocs(docs);
    }


    public int getNumfound() {
        return numfound;
    }

    public void setNumfound(int numfound) {
        this.numfound = numfound;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isNumFoundExact() {
        return numFoundExact;
    }

    public void setNumFoundExact(boolean numFoundExact) {
        this.numFoundExact = numFoundExact;
    }

    public ArrayList[] getDocs() {
        return new ArrayList[]{docs};
    }

    public void setDocs(ArrayList<String> docs) {
        this.docs = docs;
    }
}
