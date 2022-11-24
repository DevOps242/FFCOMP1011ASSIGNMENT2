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



    public static void getBooksFromOLBySearch(String title) throws IOException, InterruptedException {

        title = title.replaceAll(" ","%20");

        String uri = "http://openlibrary.org/search.json?title="+title;

        // Starts the http client
        HttpClient client = HttpClient.newHttpClient();

        // Create the https request builder
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        // First search the term
        //Get the individual book.
        //https://openlibrary.org/books/OL26793280M.json

        // Creates json file with data from api.
//        HttpResponse<Path> response = client.send(httpRequest, HttpResponse
//                .BodyHandlers
//                .ofFile(Paths.get("booksData.json"))
//        );

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Create a json object and map them to a class object.


        Gson gson = new Gson();
        JsonObject responseR = gson.fromJson(response.body(), JsonObject.class);
        JsonArray responseDocs = responseR.get("docs").getAsJsonArray();
        JsonObject responseX = responseDocs.get(0).getAsJsonObject();
        JsonArray responseSeed = responseX.get("seed").getAsJsonArray();

        ArrayList<String> bookID = new ArrayList<>();

        List seeds = responseSeed.asList();

        for (var book : seeds) {
            if (book.toString().contains("/books/")) {
                bookID.add(book.toString().replaceAll("/books/", ""));
            }
        }

        int counter = 0;

        while (counter < 10) {
            try{

            } catch (Exception e){

            }
            finally {
                counter++;
            }
        }



        //System.out.print(gson.toJson(response.body())); // serializes target to Json


//        ArrayList target2 = gson.fromJson(json, ArrayList.class); // deserializes json into target2
//        System.out.println(target2);
        //System.out.println(gson.fromJson(response.body(), ArrayList.class ));

    }
}
