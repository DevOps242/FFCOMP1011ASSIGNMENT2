module com.example.ffcomp1011assignment2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ffcomp1011assignment2 to javafx.fxml;
    exports com.example.ffcomp1011assignment2;
}