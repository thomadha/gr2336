module ui {
    requires core; //module
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.graphics, javafx.fxml; //ui package
}
