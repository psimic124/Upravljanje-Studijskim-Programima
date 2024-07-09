module com.example.upravljanjestudijskimprogramima {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.upravljanjestudijskimprogramima to javafx.fxml;
    exports com.example.upravljanjestudijskimprogramima;
}