package com.example.ffcomp1011assignment2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DetailsViewController {


    @FXML
    private Label bookTitle;

    @FXML
    private ImageView bookImage;

    @FXML
    private ImageView creditImage;

    @FXML
    private Label bookAuthor;

    @FXML
    private ImageView relatedBookImage1;
    @FXML
    private ImageView relatedBookImage2;

    @FXML
    private Label relatedBookLabel1;

    @FXML
    private Label relatedBookLabel2;

    @FXML
    private Label bookLanguage;

    @FXML
    private Label bookPageCount;

    @FXML
    private Label bookEdition;

    @FXML
    private Label bookPublisher;

    @FXML
    private Label loadingMessage;

    @FXML
    private Hyperlink purchaseLink;

    @FXML
    private Hyperlink previewLink;

    @FXML
    private VBox loaderContainer;

    @FXML
    private ProgressIndicator progressIndicator;

    private void loadImage(ImageView ImageView,Integer imageID){
        try {
            if (imageID != null) {
                try{
                    String imgUrl = APIUtility.getBookImage(imageID);
                    URLConnection connection = new URL(imgUrl).openConnection();
                    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                    Image image = new Image(connection.getInputStream());
                    ImageView.setImage(image);
                } catch(Exception e){
                    ImageView.setImage(new Image(Main.class.getResourceAsStream("images/bookDefault.png")));
                }

            } else {
                ImageView.setImage(new Image(Main.class.getResourceAsStream("images/bookDefault.png")));
            }
            ImageView.setVisible(true);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void showRelatedBooks(boolean show){
        relatedBookImage1.setVisible(show);
        relatedBookImage2.setVisible(show);
        relatedBookLabel1.setVisible(show);
        relatedBookLabel2.setVisible(show);
    }

    public void getBookDetails(Book book) throws IOException, InterruptedException {
        loaderContainer.setVisible(false);

        loadImage(bookImage,book.getImageID());
        bookTitle.setText(book.getTitle());

        if (book.getAuthor().length() > 0)
            bookAuthor.setText(String.format("By %s", book.getAuthor()));
        else
            bookAuthor.setText("Author unavailable");

        if (book.getLanguage().length() > 0 )
            bookLanguage.setText(book.getLanguage());
        else
            bookLanguage.setText("N/A");

        if (book.getNumberOfPages() != null){
            if (book.getNumberOfPages() > 0)
                bookPageCount.setText(book.getNumberOfPages().toString());
            else
                bookPageCount.setText("N/A");
        }
        else
            bookPageCount.setText("N/A");

        if (book.getEditionCount() != null )
            bookEdition.setText(book.getEditionCount().toString());
        else
            bookEdition.setText("N/A");

        if (book.getPublishers().size() > 0 )
            bookPublisher.setText(book.getPublishers().get(0));
        else
            bookPublisher.setText("N/A");

        creditImage.setImage(new Image(Main.class.getResourceAsStream("images/openlibrary-logo-tighter.png")));
//        Come back to these

        previewLink.setText(String.format("https://openlibrary.org/books/%s", book.getKey().replaceAll("/works/", "")));

        if (book.getAmazonID() != null ){
            purchaseLink.setText(String.format("https://amazon.com/dp/%s", book.getAmazonID().get(0)));
            purchaseLink.setVisible(true);
        } else {
            purchaseLink.setVisible(false);
        }

        previewLink.setVisible(true);

        // load related books.
        // Create a new thread that will fetch the related books.
        Thread fetchRelatedBooks = new Thread(new Runnable() {
            @Override
            public void run() {
                showRelatedBooks(false);
                loaderContainer.setVisible(true);

                progressIndicator.setVisible(true);
                double progress = 0;
                ArrayList<BookDetail> relatedBooks = new ArrayList<>();
                ArrayList<String> relatedBooksSeeds = book.getSeeds();
                double counter = 0;
                if (relatedBooksSeeds.size() > 0) {
                    for(var bookSeed : relatedBooksSeeds) {
                        if (bookSeed.contains("/books/")){
                            if(counter <= 2) {
                                bookSeed = bookSeed.replaceAll("/books/", "");
                                try {
                                    BookDetail bookSnippet = APIUtility.getOLBookDetailByID(bookSeed);
                                    relatedBooks.add(bookSnippet);
                                    counter++;
                                } catch (IOException | InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }

                // loop to increment the progress number
                for (int i = 0; i<=10; i++) {
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    progress += 0.1;
                    // to pass information in the Javafx thread, it cannot be a variable
                    //object
                    final double reportedProgress = progress;

                    // This creates a Thread that can run on the JavaFX platform
                    // once the JavaFX thread has capacity to take it on
                    // runLater() is like calling start() on a thread
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            progressIndicator.setProgress(reportedProgress);

                            // if the progress report is
                            if(reportedProgress > 1 ){
                                try{
                                    showRelatedBooks(true);

                                    if (relatedBooks.size() > 0) {
                                        relatedBookLabel1.setText(relatedBooks.get(0).getTitle());
                                        if (relatedBooks.get(0).getImageUri() != null) {
                                            loadImage(relatedBookImage1, relatedBooks.get(0).getImageUri().get(0));
                                        } else
                                            relatedBookImage1.setImage(new Image(Main.class.getResourceAsStream("images/bookDefault.png")));

                                        if ((relatedBooks.size() > 1)) {
                                            relatedBookLabel2.setText(relatedBooks.get(1).getTitle());
                                            if (relatedBooks.get(1).getImageUri()  != null) {
                                                loadImage(relatedBookImage2, relatedBooks.get(1).getImageUri().get(0));
                                            } else
                                                relatedBookImage2.setImage(new Image(Main.class.getResourceAsStream("images/bookDefault.png")));
                                        } else {
                                            relatedBookLabel2.setVisible(false);
                                            relatedBookImage2.setVisible(false);
                                        }
                                        loaderContainer.setVisible(false);
                                    } else {
                                        relatedBookLabel1.setVisible(false);
                                        relatedBookImage1.setVisible(false);
                                        relatedBookLabel2.setVisible(false);
                                        relatedBookImage2.setVisible(false);
                                        loaderContainer.setVisible(true);
                                        progressIndicator.setVisible(false);
                                        loadingMessage.setVisible(true);
                                        loadingMessage.setText("No Related Books Available.");
                                    }
                                } catch(IllegalArgumentException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
                progressIndicator.setProgress(0);
            }
        });

        // Start the thread to fetch the book.
        fetchRelatedBooks.start();
    }


    @FXML
    public void returnToSearch(ActionEvent event)throws IOException, InterruptedException {
        SceneChanger.changeScenes(event, "library-view.fxml");
    }
}
