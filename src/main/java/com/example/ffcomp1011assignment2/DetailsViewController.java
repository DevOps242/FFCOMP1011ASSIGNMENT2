package com.example.ffcomp1011assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.*;




import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DetailsViewController {
//    private Book oldBookData;

    @FXML
    private Label bookTitle;

    @FXML
    private ImageView bookImage;

    @FXML
    private ImageView creditImage;

    @FXML Label bookAuthor;

    @FXML
    private Label bookLanguage;

    @FXML
    private Label bookPageCount;

    @FXML
    private Label bookPublishDate;

    @FXML
    private Label bookPublisher;

    @FXML
    private Hyperlink listenLink;

    @FXML
    private Hyperlink purchaseLink;

    @FXML
    private Hyperlink previewLink;



    @FXML
    private BorderPane rootContainer;

    private void loadImage(Book book){
        try {
            if (book.getImageID() != null) {
                try{
                    String imgUrl = APIUtility.getBookImage(book.getImageID());
                    URLConnection connection = new URL(imgUrl).openConnection();
                    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                    Image image = new Image(connection.getInputStream());
                    bookImage.setImage(image);
                } catch(Exception e){
                    bookImage.setImage(new Image(Main.class.getResourceAsStream("images/bookDefault.png")));
                }

            } else {
                bookImage.setImage(new Image(Main.class.getResourceAsStream("images/bookDefault.png")));
            }
            bookImage.setVisible(true);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void getBookDetails(Book book) throws IOException, InterruptedException {


        //BookDetail bookDetail = APIUtility.getOLBookDetailByID(book.getKey());

//        System.out.println(bookDetail);
        bookTitle.setText(book.getTitle());
        bookAuthor.setText(String.format("By %s", book.getAuthor()));
        loadImage(book);
        bookLanguage.setText(book.getLanguage());
        bookPageCount.setText(null);
        bookPublishDate.setText(null);
        bookPublisher.setText(book.getPublishers().get(0));

//        Come back to these

        previewLink.setText(String.format("https://openlibrary.org/books/%s", book.getKey().replaceAll("/works/", "")));
        if (book.getAmazonID() != null ){
            purchaseLink.setText(String.format("https://amazon.com/dp/%s", book.getAmazonID()));
            purchaseLink.setVisible(true);
        } else {
            purchaseLink.setVisible(false);
        }

        listenLink.setText("");
        listenLink.setVisible(false);

        previewLink.setVisible(true);

        creditImage.setImage(new Image(Main.class.getResourceAsStream("images/openlibrary-logo-tighter.png")));


    }


    @FXML
    public void returnToSearch(ActionEvent event)throws IOException, InterruptedException {
        SceneChanger.changeScenes(event, "library-view.fxml");
    }
}
