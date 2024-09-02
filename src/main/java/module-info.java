module com.example.photoshopfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.photoshopfx to javafx.fxml;
    exports com.example.photoshopfx;
}