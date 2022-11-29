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


    }

    public static BookDetail getOLBookDetailByID(String bookKey) throws IOException, InterruptedException{

        bookKey = bookKey.replaceAll("/works/", "");

        String uri = String.format("https://openlibrary.org/works/%s.json", bookKey);
        HttpClient client = HttpClient.newHttpClient();

        // Create the https request builder
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());


//        {"covers": [149216], "first_publish_date": "December 30, 1998", "key": "/works/OL1982658W",
//                "authors": [{"type": {"key": "/type/author_role"}, "author": {"key": "/authors/OL238509A"}}],
//            "subjects": ["Java (Computer program language)", "Object-oriented programming (Computer science)",
//                "Objektorientierte Programmierung", "Java (programmeertaal)", "Java (Programmiersprache)",
//                "Langages \u00e0 objets (Informatique)", "Java (Langage de programmation)"], "title": "An Introduction to Object-Oriented Programming With Java",
//                "type": {"key": "/type/work"}, "latest_revision": 7, "revision": 7, "created": {"type": "/type/datetime", "value": "2009-12-09T22:39:56.910459"},
//            "last_modified": {"type": "/type/datetime", "value": "2021-08-16T08:53:04.534341"}}


        System.out.println(response.body());
        System.out.println("book detailed API Utility done.");
        Gson gson = new Gson();

        return gson.fromJson(response.body(), BookDetail.class);

    }

    public static void getBookDetails(List books) throws IOException, InterruptedException{
        ArrayList<Object> bookDetails = new ArrayList<>();

//        for(var book : books) {
//           // HttpResponse<String> response = getOLBookDetailByID(book.toString());
//            bookDetails.add(response.body());
//            break;
//        }

        System.out.println(bookDetails);
    }

    public static String getBookImage(Integer imageID) {
        return String.format("https://covers.openlibrary.org/b/id/%s-L.jpg", imageID);
    }
}
