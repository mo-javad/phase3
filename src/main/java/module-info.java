module com.example.phase3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;


    opens com.example.phase3 to javafx.fxml;
    exports com.example.phase3;

    opens controllers to javafx.fxml;
    opens views to javafx.fxml;
    opens models to com.google.gson;
}