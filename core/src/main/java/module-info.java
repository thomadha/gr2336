module movie.core {
    requires com.google.gson;
    exports core;
    exports json;

    opens core to com.google.gson;
}
