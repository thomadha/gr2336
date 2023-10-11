module core {
    requires com.google.gson;
    exports core; //core package
    exports json; //json package

    opens core to com.google.gson; //package
}
