package com.example.ffcomp1011assignment2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

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
    private ListView<?> bookListView;

    @FXML
    private Label bookPublishLabel;

    @FXML
    private Label bookPublisherLabel;

    @FXML
    private Label bookTitleLabel;

    @FXML
    private Label editionLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Label isbnLabel;

    @FXML
    private Button listenButton;

    @FXML
    private Button previewLabel;

    @FXML
    private Label publishLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private Button purchaseBookButton;

    @FXML
    private Label titleLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
