package com.example.ffcomp1011assignment2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LibraryViewController implements Initializable {

    @FXML
    private Label authorLabel;

    @FXML
    private Label bookAuthorLabel;

    @FXML
    private Label bookEditionLabel;

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
    private Label editionLabel;

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
            bookTitleLabel.setVisible(true);
            bookAuthorLabel.setVisible(true);
            bookEditionLabel.setVisible(true);
            bookIsbnLabel.setVisible(true);
            bookPublisherLabel.setVisible(true);
            bookPublishLabel.setVisible(true);
            bookListView.setVisible(true);

            titleLabel.setVisible(true);
            authorLabel.setVisible(true);
            editionLabel.setVisible(true);
            isbnLabel.setVisible(true);
            publisherLabel.setVisible(true);
            publishLabel.setVisible(true);

            listenButton.setVisible(true);
            purchaseBookButton.setVisible(true);
            previewButton.setVisible(true);

        } else {
            bookTitleLabel.setVisible(false);
            bookAuthorLabel.setVisible(false);
            bookEditionLabel.setVisible(false);
            bookIsbnLabel.setVisible(false);
            bookPublisherLabel.setVisible(false);
            bookPublishLabel.setVisible(false);
            bookListView.setVisible(false);

            titleLabel.setVisible(false);
            authorLabel.setVisible(false);
            editionLabel.setVisible(false);
            isbnLabel.setVisible(false);
            publisherLabel.setVisible(false);
            publishLabel.setVisible(false);

            listenButton.setVisible(false);
            purchaseBookButton.setVisible(false);
            previewButton.setVisible(false);


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showLabels(false);
        errorLabel.setVisible(false);
        resultsLabel.setVisible (false);
    }

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
        }
    }
}
