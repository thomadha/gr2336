module core {
    requires com.google.gson;
    exports core; //core package
    exports filehandler; //json package

    opens core to com.google.gson; //package
}
