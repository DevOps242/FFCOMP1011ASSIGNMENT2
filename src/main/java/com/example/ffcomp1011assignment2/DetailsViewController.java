package com.example.ffcomp1011assignment2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class DetailsViewController {
//    private Book oldBookData;

    @FXML
    private Label bookTitle;

    @FXML Label bookAuthor;

    public void getBookDetails(Book book) throws IOException, InterruptedException {
//        this.oldBookData = book;
        System.out.println(book.getKey());
        BookDetail bookDetail = APIUtility.getOLBookDetailByID(book.getKey());


        System.out.println(bookDetail);
        bookTitle.setText(book.getTitle());
//        bookAuthor.setText(bookDetail.getAuthor());
    }
}
