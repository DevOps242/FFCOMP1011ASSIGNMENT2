package com.example.ffcomp1011assignment2;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;


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
    }


    public static BookDetail getOLBookDetailByID(String bookKey) throws IOException, InterruptedException{

        String uri = String.format("https://openlibrary.org/books/%s.json", bookKey);
        HttpClient client = HttpClient.newHttpClient();

        // Create the https request builder
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        return gson.fromJson(response.body(), BookDetail.class);

    }

    public static String getBookImage(Integer imageID) {
        return String.format("https://covers.openlibrary.org/b/id/%s-L.jpg", imageID);
    }

}
