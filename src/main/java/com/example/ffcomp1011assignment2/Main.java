package com.example.ffcomp1011assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("library-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Barrie Library");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/logo.png")));

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

//        APIUtility.getBooksFromOLBySearch("Python Books");
//        APIUtility.getOLBookDetailByID("OL8051731M");
        launch();
    }
}
