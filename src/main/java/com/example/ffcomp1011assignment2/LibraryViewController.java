package com.example.ffcomp1011assignment2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import java.util.*;

public class LibraryViewController  implements Initializable {

    @FXML
    private VBox detailVBox;

    @FXML
    private Label authorLabel;

    @FXML
    private Label bookAuthorLabel;

    @FXML
    private Label bookLanguageLabel;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookIsbnLabel;

    @FXML
    private ListView<Book> bookListView;

    @FXML
    private Label bookPublishLabel;

    @FXML
    private Label bookPublisherLabel;

    @FXML
    private Label bookTitleLabel;

    @FXML
    private Label resultsLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label isbnLabel;

    @FXML
    private Button listenButton;

    @FXML
    private Button previewButton;

    @FXML
    private Label publishLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private Button purchaseBookButton;

    @FXML
    private Label titleLabel;

    private void showLabels(boolean show){
        if (show) {

            detailVBox.setVisible(true);
            bookListView.setVisible(true);
            listenButton.setVisible(true);
            purchaseBookButton.setVisible(true);
            previewButton.setVisible(true);

        } else {
            detailVBox.setVisible(false);
            previewButton.setVisible(false);
            bookListView.setVisible(false);
            listenButton.setVisible(false);
            purchaseBookButton.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showLabels(false);
        errorLabel.setVisible(false);
        bookImage.setVisible(false);
        resultsLabel.setVisible (false);
        bookListView.getSelectionModel().selectedItemProperty().addListener((observableValue, book, bookSelected) -> {
            if (bookSelected != null){
                showLabels(true);
                bookTitleLabel.setText(bookSelected.getTitle());

                // Validate that the fields are there.
                if (bookSelected.getAuthor() != null)
                    bookAuthorLabel.setText(bookSelected.getAuthor().toString());
                else
                    bookAuthorLabel.setText("N/A");

                if (bookSelected.getLanguage() != null)
                    bookLanguageLabel.setText(bookSelected.getLanguage().toString());
                else
                    bookLanguageLabel.setText("N/A");

//                editionLabel.setText(bookSelected.);
//                isbnLabel.setText(bookSelected);

                if (bookSelected.getPublishers() != null)
                    bookPublisherLabel.setText(bookSelected.getPublishers().toString());
                else
                    bookPublisherLabel.setText("N/A");

                if (bookSelected.isPublishDate() != null)
                    bookPublishLabel.setText(bookSelected.isPublishDate().toString());
                else
                    bookPublishLabel.setText("N/A");

                    Thread fetchBookImageThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (bookSelected.getImageID() != null) {
//                                bookImage.setImage(new Image("https://ia802701.us.archive.org/view_archive.php?archive=/29/items/olcovers123/olcovers123-L.zip&file=1239523-L.jpg"));
//                                bookImage.setImage(new Image("https://m.media-amazon.com/images/M/MV5BZmRiZDlhMWEtOGIzZi00NGVjLTg3NmYtYmQ2YjgzYjMwOWZjXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg"));

                                    // Section take from https://stackoverflow.com/questions/55075985/javafx-image-url-not-loading
                                    // to resolve the issue of my image not loading from the openlibrary.org domain
                                    try{
                                        String imgUrl = APIUtility.getBookImage(bookSelected.getImageID());
                                        URLConnection connection = new URL(imgUrl).openConnection();
                                        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                        Image image = new Image(connection.getInputStream());
                                        bookImage.setImage(image);
                                    } catch(Exception e){
                                        bookImage.setImage(new Image(APIUtility.getBookImage(bookSelected.getImageID())));
                                    }

                                } else {
                                    bookImage.setImage(new Image(Main.class.getResourceAsStream("images/bookDefault.png")));
                                }
                                bookImage.setVisible(true);
                            } catch(IllegalArgumentException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    fetchBookImageThread.start();


            } else {
                    errorLabel.setVisible(true);
                    errorLabel.setText("Book is unavailable.");
            }
        });
    }

    /**
     * Call the API with the search term. and display the results if response object is present.
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    private void search() throws IOException, InterruptedException {
        APIResponse apiResponse = APIUtility.getBooksFromOLBySearch(searchTextField.getText());

        if(apiResponse.getTotalResults() > 0) {
            // Load the list
            bookListView.getItems().clear();
            resultsLabel.setVisible(true);
            bookListView.setVisible(true);

            errorLabel.setVisible(false);
            List<Book> books = apiResponse.getBooks();
//            Collections.sort(books, new Comparator<Book>() {
//                @Override
//                public int compare(Book o1, Book o2) {
//                    return 0;
//                }
//            });
            resultsLabel.setText("Result Found: " + books.size());
            bookListView.getItems().addAll(books);


        } else {
            // Show error message
            resultsLabel.setText("Results Found: 0");
            errorLabel.setText("There was an error, searching your book title.");
            resultsLabel.setVisible(true);
            errorLabel.setVisible(true);
            bookListView.getItems().clear();
        }
    }

    @FXML
    public void listenBook() {

    }

    @FXML
    public void previewBookWeb() throws URISyntaxException, IOException{
//        // Reference - https://www.geekyhacker.com/2021/11/18/open-a-url-in-the-default-browser-in-java/
//        String bookUrl = "https://openlibrary.org/"+bookWork;

    }

    @FXML
    public void purchaseBook() {
        String yahooURL = "http://www.java2s.com";
    }

    /**
     * Load additional information for the book clicked.
     *
     */
    @FXML
    private void getDetails(ActionEvent event) throws IOException, InterruptedException {
        Book book = bookListView.getSelectionModel().getSelectedItem();
        SceneChanger.changeScenes(event, "details-view.fxml", book.getKey());
    }
}
