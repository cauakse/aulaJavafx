module com.example.photoshopfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.photoshopfx to javafx.fxml;
    exports com.example.photoshopfx;
}