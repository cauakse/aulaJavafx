package com.example.photoshopfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public ImageView imageView;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onAbrir(ActionEvent actionEvent) {
        FileChooser fileChooser;
        fileChooser= new FileChooser();
        fileChooser.setInitialDirectory(new File("d://"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas Imagens","*.jpg","*.png",
                        "*.bmp","*.jpeg","*.webp"),
                new FileChooser.ExtensionFilter("JPEG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG","*.png"),
                new FileChooser.ExtensionFilter("BMP","*.bmp")
        );
        File file=fileChooser.showOpenDialog(null);
        if(file!=null){
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            imageView.setFitWidth(1920);
            imageView.setFitHeight(1350);
        }
    }

    public void onSalvar(ActionEvent actionEvent) {
    }

    public void onSalvarComo(ActionEvent actionEvent) {
    }

    public void onSair(ActionEvent actionEvent) {
        Platform.exit();
    }
}