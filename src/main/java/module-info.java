module com.willhero.willhero {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.willhero.willhero to javafx.fxml;
    exports com.willhero.willhero;
}