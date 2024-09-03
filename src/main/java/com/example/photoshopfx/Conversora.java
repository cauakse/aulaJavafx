package com.example.photoshopfx;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Conversora {
   public static Image tonsCinza(Image image){
       // converte um Image em BufferedImage
       BufferedImage bimg;
       bimg= SwingFXUtils.fromFXImage(image, null);
       // captura pixels da imagem

       int pixel[] = { 0 , 0 , 0 , 0 };
       WritableRaster raster=bimg.getRaster();
       for (int i=0;i<image.getHeight();i++){
           for (int j = 0; j < image.getWidth(); j++) {
               raster.getPixel(j,i,pixel);  // obtenha um pixel
               int tonscinza =(int)(0.299*pixel[0]+0.587*pixel[1]+0.114*pixel[2]);
               pixel[0]=pixel[1]=pixel[2]=tonscinza;
               raster.setPixel(j,i,pixel);  // reaplique o pixel
           }
       }
       return SwingFXUtils.toFXImage(bimg, null);
    }

    public static Image PretoBranco(Image image){
        // converte um Image em BufferedImage
        BufferedImage bimg;
        bimg= SwingFXUtils.fromFXImage(image, null);
        // captura pixels da imagem

        int pixel[] = { 0 , 0 , 0 , 0 };
        WritableRaster raster=bimg.getRaster();
        for (int i=0;i<image.getHeight();i++){
            for (int j = 0; j < image.getWidth(); j++) {
                raster.getPixel(j,i,pixel);  // obtenha um pixel
                int tonscinza =(int)(0.299*pixel[0]+0.587*pixel[1]+0.114*pixel[2]);
                if(tonscinza>=140){
                    pixel[0]=pixel[1]=pixel[2]=255;
                }
                else
                {
                    pixel[0]=pixel[1]=pixel[2]=0;
                }
                raster.setPixel(j,i,pixel);  // reaplique o pixel
            }
        }
        return SwingFXUtils.toFXImage(bimg, null);
    }

    public static Image Espelhar (Image image){
        // converte um Image em BufferedImage
        BufferedImage bimg;
        bimg= SwingFXUtils.fromFXImage(image, null);

        // captura pixels da imagem
        Image aux = new Image(image.getUrl());
        BufferedImage bimga = SwingFXUtils.fromFXImage(aux,null);
        int pixel[] = { 0 , 0 , 0 , 0 };
        WritableRaster raster=bimg.getRaster();
        WritableRaster rasteraux=bimga.getRaster();
        for (int i=0;i<aux.getHeight();i++){
            for (int j = 0; j < aux.getWidth(); j++) {
                rasteraux.getPixel(j,i,pixel);  // obtenha um pixel
                raster.setPixel(i,j,pixel);  // reaplique o pixel
            }
        }
        return SwingFXUtils.toFXImage(bimg, null);
    }
}
