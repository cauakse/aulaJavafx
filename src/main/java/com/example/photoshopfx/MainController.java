package com.example.photoshopfx;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {







    public ImageView imageView;
    public String url;
    public Menu transformationMenu;
    public SeparatorMenuItem separator1;
    public MenuItem saveMenuItem;
    public MenuItem saveAsMenuItem;
    public SeparatorMenuItem separator2;
    public Button saveBtn;
    public Button ihBtn;
    public Button ivBtn;
    public Button aboutBtn;
    public Button abrirBtn;
    private boolean flag = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        transformationMenu.setDisable(true);
        separator1.setDisable(true);
        separator2.setDisable(true);
        saveAsMenuItem.setDisable(true);
        saveMenuItem.setDisable(true);
        saveBtn.setVisible(false);
        ihBtn.setVisible(false);
        ivBtn.setVisible(false);
    }

    public void onAbrir(ActionEvent actionEvent) {
        FileChooser fileChooser;
        fileChooser= new FileChooser();
        fileChooser.setInitialDirectory(new File("c://"));
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
            imageView.setFitWidth(image.getWidth()+400);
            imageView.setFitHeight(image.getHeight()+300);
            this.url=imageView.getImage().getUrl();
        }
        transformationMenu.setDisable(false);
        separator1.setDisable(false);
        separator2.setDisable(false);
        saveAsMenuItem.setDisable(false);
        saveMenuItem.setDisable(false);
        saveBtn.setVisible(true);
        ihBtn.setVisible(true);
        ivBtn.setVisible(true);
    }

    public void onSalvar(ActionEvent actionEvent) {
        String url =this.url;
        String formato = url.substring(url.lastIndexOf(".")).replace(".","");
        url = url.replaceFirst("file:/","");
        url = url.replaceAll("/","//");
        url = url.replaceAll("%20"," ");
        File arq = new File(url);
        BufferedImage bimg;
        bimg= SwingFXUtils.fromFXImage(imageView.getImage(), null);
        BufferedImage copy = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
        if(formato.equalsIgnoreCase("jpg") || formato.equalsIgnoreCase("jpeg"))
        {
            Graphics2D g2d = copy.createGraphics();
            g2d.setColor(Color.WHITE); // Or what ever fill color you want...
            g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
            g2d.drawImage(bimg, 0, 0, null);
            g2d.dispose();
        }
        try
        {
            if(formato.equalsIgnoreCase("jpg") || formato.equalsIgnoreCase("jpeg")){
                ImageIO.write(copy,formato,arq);
            }
            else {
                ImageIO.write(bimg, formato, arq);
            }
        }catch(Exception e)
        {
            System.out.println("Erro " + e);; }
    }

    public void onSalvarComo(ActionEvent actionEvent) {
        FileChooser fc;
        fc=new FileChooser();
        String url =this.url;
        fc.setInitialFileName(url.substring(url.lastIndexOf("/")+1).replaceAll("%20",""));
        String formato = url.substring(url.lastIndexOf(".")).replace(".","");
        url = url.replaceFirst("file:/","");
        url = url.replaceAll("/","//");
        url = url.replaceAll("%20"," ");
        url = url.substring(0,url.lastIndexOf("/"));
        fc.setInitialDirectory(new File(url));
        File arq=fc.showSaveDialog(null);
        if(arq!=null)
        {
            BufferedImage bimg;
            bimg= SwingFXUtils.fromFXImage(imageView.getImage(), null);
            BufferedImage copy = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            if(formato.equalsIgnoreCase("jpg") || formato.equalsIgnoreCase("jpeg"))
            {
                Graphics2D g2d = copy.createGraphics();
                g2d.setColor(Color.WHITE); // Or what ever fill color you want...
                g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
                g2d.drawImage(bimg, 0, 0, null);
                g2d.dispose();
            }
            try
            {
                if(formato.equalsIgnoreCase("jpg") || formato.equalsIgnoreCase("jpeg")){
                    ImageIO.write(copy,formato,arq);
                }else {
                    ImageIO.write(bimg, formato, arq);
                }

            }catch(Exception e)
            {
                System.out.println("Erro " + e);; }
        }

    }

    public void onSair(ActionEvent actionEvent) {
        if(flag){
            Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não");
            dialogoExe.setTitle("AVISO!!!");
            dialogoExe.setHeaderText("Existem alterações não salvas");
            dialogoExe.setContentText("Deseja salvar as alterações?");
            dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
            dialogoExe.showAndWait().ifPresent(b -> {
                if (b == btnSim) {
                    onSalvar(null);
                }
                flag=false;
            });
        }
        Platform.runLater(()->{
            Platform.exit();
        });
    }

    public void onTonsCinza(ActionEvent actionEvent) {
        imageView.setImage(Conversora.tonsCinza(imageView.getImage()));
        flag=true;
    }

    public void onPretoBranco(ActionEvent actionEvent) {
        imageView.setImage(Conversora.PretoBranco(imageView.getImage()));
        flag=true;
    }

    public void onEspelharHorizontal(ActionEvent actionEvent) {
        imageView.setImage(Conversora.Espelhar(imageView.getImage()));
        flag=true;
    }

    public void onNegativo(ActionEvent actionEvent) {
        imageView.setImage(Conversora.Negativo(imageView.getImage()));
        flag=true;
    }

    public void onEspelharVertical(ActionEvent actionEvent) {
        imageView.setImage(Conversora.EspelharVertical(imageView.getImage()));
        flag=true;
    }

    public void onSobre(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotoShopFX.class.getResource("sobre-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Sobre Nós");
        stage.show();
    }

    public void onDetectarBordas(ActionEvent actionEvent) {
        imageView.setImage(Conversora.DetectarBordasIJ(imageView.getImage()));
        flag=true;
    }

    public void onAbrirBtn(ActionEvent actionEvent) {
        onAbrir(actionEvent);
    }

    public void onSaveBtn(ActionEvent actionEvent) {
        onSalvar(actionEvent);
    }

    public void onIhBtn(ActionEvent actionEvent) {
        onEspelharHorizontal(actionEvent);
    }

    public void onIvBtn(ActionEvent actionEvent) {
        onEspelharVertical(actionEvent);
    }

    public void onAboutBtn(ActionEvent actionEvent) throws IOException {
        onSobre(actionEvent);
    }

    public void onBorrarImagem(ActionEvent actionEvent) {
        imageView.setImage(Conversora.borrarImagemIJ(imageView.getImage()));
        flag=true;
    }

    public void onCalorificar(ActionEvent actionEvent) {
        imageView.setImage(Conversora.calorificarImagemIJ(imageView.getImage()));
        flag=true;
    }

    public void onDilatar(ActionEvent actionEvent) {
        imageView.setImage(Conversora.dilatarImagemIJ(imageView.getImage()));
        flag=true;
    }

    public void onDrawLine(MouseEvent dragEvent) {
        double scaleX = imageView.getImage().getWidth() / imageView.getBoundsInLocal().getWidth();
        double scaleY = imageView.getImage().getHeight() / imageView.getBoundsInLocal().getHeight() ;
        int x = (int) (dragEvent.getX() * scaleX);
        int y = (int) (dragEvent.getY() * scaleY);
        imageView.setImage(Conversora.desenharImagemIJ(imageView.getImage(),x,y));
        flag=true;
    }


}