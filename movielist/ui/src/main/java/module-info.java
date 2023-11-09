module ui {
    requires core; //module
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http; 
    requires com.google.gson;
    

    opens ui to javafx.graphics, javafx.fxml, com.google.gson; //ui package
}
