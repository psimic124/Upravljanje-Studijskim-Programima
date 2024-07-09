module com.example.upravljanjestudijskimprogramima {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;

    opens com.example.upravljanjestudijskimprogramima to javafx.fxml;
    exports com.example.upravljanjestudijskimprogramima;
}
