module com.example.cgtask {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cgtask to javafx.fxml;
    exports com.example.cgtask;
}