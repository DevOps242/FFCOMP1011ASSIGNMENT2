package com.example.ffcomp1011assignment2;

import java.io.IOException;

public class DetailsViewController {

    public void getBookDetails(String bookWork) throws IOException, InterruptedException {
        BookDetail bookDetail = APIUtility.getOLBookDetailByID(bookWork);
    }
}
