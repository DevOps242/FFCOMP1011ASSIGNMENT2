package com.example.ffcomp1011assignment2;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    private Label bookAuthorLabel;

    @FXML
    private Label bookLanguageLabel;

    @FXML
    private ImageView bookImage;

    @FXML
    private ListView<Book> bookListView;

    @FXML
    private Label bookSubjectLabel;

    @FXML
    private Label bookTitleLabel;

    @FXML
    private Label resultsLabel;


    @FXML
    private Label errorLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button listenButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button previewButton;


    @FXML
    private ListView<String> bookPublisherListView;

    @FXML
    private Button purchaseBookButton;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private BorderPane libraryContainer;

    @FXML
    private  BorderPane loadingContainer;

    @FXML
    private VBox loaderContainer;

    @FXML
    private Label loadingMessage;

    @FXML
    private ProgressIndicator progressIndicatorMini;

    private void showLabels(boolean show){
        if (show) {

            detailVBox.setVisible(true);
            bookListView.setVisible(true);
            listenButton.setVisible(true);
            purchaseBookButton.setVisible(true);
            previewButton.setVisible(true);

        } else {
            detailVBox.setVisible(false);
            bookListView.setVisible(false);
            listenButton.setVisible(false);
            purchaseBookButton.setVisible(false);
            previewButton.setVisible(false);
        }
    }

    private void clearFields(){
        bookPublisherListView.getItems().clear();
        bookLanguageLabel.setText(null);
        bookTitleLabel.setText(null);
        bookAuthorLabel.setText(null);
        bookSubjectLabel.setText(null);
        bookImage.setImage(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingContainer.setVisible(false);
        showLabels(false);
        errorLabel.setVisible(true);
        errorLabel.setText("Input Book Title Below");
        bookImage.setVisible(false);
        resultsLabel.setVisible (false);
        searchButton.setDisable(true);


        loaderContainer.setVisible(false);
        loadingMessage.setVisible(false);
        progressIndicatorMini.setVisible(false);

        searchTextField.textProperty().addListener(((obs, oldText, newText) -> {
            if (searchTextField.getText() == null || searchTextField.getText().isBlank()){
                searchButton.setDisable(true);
                errorLabel.setText("Book Title Must Not Be Blank");
            } else {
                errorLabel.setText("Input Book Title Below");
                searchButton.setDisable(false);
                searchButton.setText("Search");
            }
        }));

        bookListView.getSelectionModel().selectedItemProperty().addListener((observableValue, book, bookSelected) -> {
            bookPublisherListView.getItems().clear();
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

                if (bookSelected.getPublishers() != null)
                    bookPublisherListView.getItems().addAll(bookSelected.getPublishers());

                if (bookSelected.getSubject() != null)
                    bookSubjectLabel.setText(bookSelected.getSubject());

                    Thread fetchBookImageThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            progressIndicatorMini.setProgress(0);
                            double progress = 0;
                            bookImage.setVisible(false);
                            loaderContainer.setVisible(true);
                            loadingMessage.setVisible(true);
                            progressIndicatorMini.setVisible(true);
                            try {
                                if (bookSelected.getImageID() != null) {
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

                                for (int i = 0; i<=10; i++) {
                                    try{
                                        Thread.sleep(100);
                                    } catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                    progress += 0.1;

                                    final double reportedProgress = progress;
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressIndicatorMini.setProgress(reportedProgress);

                                            // if the progress report is
                                            if(reportedProgress >= 1 ){
                                                try{
                                                    bookImage.setVisible(true);
                                                    loaderContainer.setVisible(false);
                                                    loadingMessage.setVisible(false);
                                                    progressIndicatorMini.setVisible(false);
                                                } catch(IllegalArgumentException e) {
                                                    bookImage.setVisible(false);
                                                    loaderContainer.setVisible(true);
                                                    loadingMessage.setVisible(true);
                                                    loadingMessage.setText("Error Loading Image");
                                                    progressIndicatorMini.setVisible(false);
                                                }
                                            }
                                        }
                                    });
                                }
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
        detailVBox.setVisible(false);
        libraryContainer.setVisible(false);
        loadingContainer.setVisible(true);
        bookListView.getItems().clear();
        listenButton.setVisible(false);
        previewButton.setVisible(false);
        purchaseBookButton.setVisible(false);
        Thread fireLoaderThread  = new Thread(new Runnable() {
            @Override
            public void run() {
                double progress = 0;
                //                progressIndicator.setVisible(true);

                try {
                    APIResponse apiResponse = APIUtility.getBooksFromOLBySearch(searchTextField.getText());
                    if(apiResponse.getTotalResults() > 0) {
                        // Load the list
                        resultsLabel.setVisible(true);
                        bookListView.setVisible(true);
                        errorLabel.setVisible(false);
                        List<Book> books = apiResponse.getBooks();

                        for (int i = 0; i<=10; i++) {
                            try{
                                Thread.sleep(100);
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            progress += 0.1;

                            final double reportedProgress = progress;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    progressIndicator.setProgress(reportedProgress);

                                    // if the progress report is
                                    if(reportedProgress >= 1 ){
                                        try{
                                            libraryContainer.setVisible(true);
                                            loadingContainer.setVisible(false);
                                            bookListView.getItems().addAll(books);
                                            if (bookListView.getItems().size() > 0)
                                                resultsLabel.setText("Result Found: " + books.size());
                                            progressIndicator.setProgress(0);

                                        } catch(IllegalArgumentException e) {
                                            libraryContainer.setVisible(true);
                                            loadingContainer.setVisible(false);
                                            errorLabel.setText(e.toString());
                                            progressIndicator.setProgress(0);
                                            // Show error message
                                            resultsLabel.setText("Results Found: 0");
                                            errorLabel.setText("There was an error, searching your book title.");
                                            resultsLabel.setVisible(true);
                                            errorLabel.setVisible(true);
                                            bookListView.getItems().clear();
                                        }
                                    }
                                }
                            });
                        }
                    } else {

                        for (int i = 0; i<=10; i++) {
                            try{
                                Thread.sleep(100);
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            progress += 0.1;

                            final double reportedProgress = progress;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    progressIndicator.setProgress(reportedProgress);

                                    // if the progress report is
                                    if(reportedProgress >= 1 ){
                                        try{

                                            bookListView.setVisible(false);
                                            bookListView.getItems().clear();
                                            libraryContainer.setVisible(true);
                                            loadingContainer.setVisible(false);
                                            progressIndicator.setProgress(0);
                                            // Show error message
                                            resultsLabel.setText("Results Found: 0");
                                            errorLabel.setText("There was an error, searching your book title.");
                                            resultsLabel.setVisible(true);
                                            errorLabel.setVisible(true);
                                            showLabels(false);

                                        } catch(IllegalArgumentException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                    }
                } catch(InterruptedException | IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        });
        fireLoaderThread.start();
    }

    @FXML
    public void listenBook(ActionEvent event) {
        loadComingSoon(event, "Listen to Book");
    }

    @FXML
    public void previewBookWeb(ActionEvent event){
        loadComingSoon(event, "Preview Book");
    }

    @FXML
    public void purchaseBook(ActionEvent event) {
        loadComingSoon(event, "Purchase Book");
    }

    /**
     * This method loads alert when button is click to inform the user of feature coming soon.
     * @param event
     * @param mode
     */
    public void loadComingSoon(ActionEvent event, String mode){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Coming Soon...");
        alert.setHeaderText(String.format("If the link is available for %s, it will show on the details page.",mode));
        alert.setContentText("Thank you for choosing Barrie Library! :)");

        alert.showAndWait();
    }

    /**
     * Load additional information for the book clicked on a new scence.
     *
     */
    @FXML
    private void getDetails(ActionEvent event) throws IOException, InterruptedException {
        Book book = bookListView.getSelectionModel().getSelectedItem();
        SceneChanger.changeScenes(event, "details-view.fxml", book);
    }
}
