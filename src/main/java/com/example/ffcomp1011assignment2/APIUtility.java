package com.example.ffcomp1011assignment2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class APIUtility {



    public static APIResponse getBooksFromOLBySearch(String title) throws IOException, InterruptedException {

        title = title.replaceAll(" ","%20");

        String uri = "http://openlibrary.org/search.json?title="+title;

        // Starts the http client
        HttpClient client = HttpClient.newHttpClient();

        // Create the https request builder
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        // First search the term
        //Get the individual book.
        //https://openlibrary.org/books/OL26793280M.json

         //Creates json file with data from api.
        HttpResponse<Path> responseFile = client.send(httpRequest, HttpResponse
                .BodyHandlers
                .ofFile(Paths.get("booksData.json"))
        );

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Create a json object and map them to a class object.
        Gson gson = new Gson();

        return gson.fromJson(response.body(), APIResponse.class);


//        JsonObject responseR = gson.fromJson(response.body(), JsonObject.class);
//        JsonArray responseDocs = responseR.get("docs").getAsJsonArray();
////        JsonObject responseX = .getAsJsonObject();
//
//        return gson.fromJson(responseR, APIResponse.class);
//        JsonArray responseSeed = responseX.get("seed").getAsJsonArray();

//        ArrayList<String> bookID = new ArrayList<>();

//        List seeds = responseSeed.asList();
//
//        for (var book : seeds) {
//            if (book.toString().contains("/books/")) {
//                bookID.add(book.toString().replaceAll("/books/", ""));
//            }
//        }

//        try{
//            getBookDetails(bookID);
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }

    }

    public static HttpResponse<String> getOLBookDetailByID(String bookID) throws IOException, InterruptedException{

        HttpClient client = HttpClient.newHttpClient();
        System.out.print(bookID);

        String uri = String.format("https://openlibrary.org/books/OL9158246M.json");
        // Create the https request builder
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

    }

    public static void getBookDetails(List books) throws IOException, InterruptedException{
        ArrayList<Object> bookDetails = new ArrayList<>();

        for(var book : books) {
            HttpResponse<String> response = getOLBookDetailByID(book.toString());
            bookDetails.add(response.body());
            break;
        }

        System.out.println(bookDetails);
    }

    public static String getBookImage(int imageID) {
        return String.format("https://covers.openlibrary.org/b/id/%s-M.jpg", imageID);

    }
}
