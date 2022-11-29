module com.example.ffcomp1011assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;



    opens com.example.ffcomp1011assignment2 to javafx.fxml, com.google.gson;
    exports com.example.ffcomp1011assignment2;
}