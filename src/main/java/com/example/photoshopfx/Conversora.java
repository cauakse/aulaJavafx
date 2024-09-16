package com.example.photoshopfx;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.event.MouseEvent;
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
        int pixel[] = { 0 , 0 , 0 , 0 };
        int auxp [] = { 0 , 0 , 0 , 0 };

        WritableRaster raster=bimg.getRaster();
        for (int i=0;i<image.getHeight();i++){
            for (int j = 0; j < image.getWidth()/2; j++) {
                raster.getPixel(i,j,auxp);  // obtenha um pixel
                raster.getPixel(i,(int)image.getWidth()-1-j,pixel);
                raster.setPixel(i,j,pixel);
                raster.setPixel(i,(int)image.getWidth()-1-j,auxp);// reaplique o pixel
            }
        }
        return SwingFXUtils.toFXImage(bimg, null);
    }

    public static Image Negativo(Image image) {
        BufferedImage bimg;
        bimg= SwingFXUtils.fromFXImage(image, null);

        // captura pixels da imagem
        int pixel[] = { 0 , 0 , 0 , 0 };
        WritableRaster raster=bimg.getRaster();
        for (int i=0;i<image.getWidth();i++){
            for (int j = 0; j < image.getHeight(); j++) {
                raster.getPixel(i,j,pixel); // obtenha um pixel
                pixel[0]=255-pixel[0];
                pixel[1]=255-pixel[1];
                pixel[2]=255-pixel[2];
                raster.setPixel(i,j,pixel);// reaplique o pixel
            }
        }
        return SwingFXUtils.toFXImage(bimg, null);
    }

    public static Image EspelharVertical(Image image) {

        // converte um Image em BufferedImage
        BufferedImage bimg;
        bimg= SwingFXUtils.fromFXImage(image, null);

        // captura pixels da imagem
        int pixel[] = { 0 , 0 , 0 , 0 };
        int auxp [] = { 0 , 0 , 0 , 0 };

        WritableRaster raster=bimg.getRaster();
        for (int i=0;i<image.getWidth();i++){
            for (int j = 0; j < image.getHeight()/2; j++) {
                raster.getPixel(j,i,auxp);  // obtenha um pixel
                raster.getPixel((int)image.getHeight()-1-j,i,pixel);
                raster.setPixel(j,i,pixel);
                raster.setPixel((int)image.getHeight()-1-j,i,auxp);// reaplique o pixel
            }
        }
        return SwingFXUtils.toFXImage(bimg, null);
    }
    public static Image DetectarBordasIJ(Image image){
        ImagePlus imagePlus = new ImagePlus();
        BufferedImage bimg = SwingFXUtils.fromFXImage(image,null);
        imagePlus.setImage(bimg);
        ImageProcessor imageProcessor=imagePlus.getProcessor();
        imageProcessor.findEdges();
        return SwingFXUtils.toFXImage(imagePlus.getBufferedImage(),null);
   }

    public static Image borrarImagemIJ(Image image) {
        ImagePlus imagePlus = new ImagePlus();
        BufferedImage bimg = SwingFXUtils.fromFXImage(image,null);
        imagePlus.setImage(bimg);
        ImageProcessor imageProcessor=imagePlus.getProcessor();
        imageProcessor.erode();
        return SwingFXUtils.toFXImage(imagePlus.getBufferedImage(),null);
    }

    public static Image calorificarImagemIJ(Image image) {
        ImagePlus imagePlus = new ImagePlus();
        BufferedImage bimg = SwingFXUtils.fromFXImage(image,null);
        imagePlus.setImage(bimg);
        ImageProcessor imageProcessor=imagePlus.getProcessor();
        imageProcessor.autoThreshold();
        return SwingFXUtils.toFXImage(imagePlus.getBufferedImage(),null);
    }

    public static Image dilatarImagemIJ(Image image) {
        ImagePlus imagePlus = new ImagePlus();
        BufferedImage bimg = SwingFXUtils.fromFXImage(image,null);
        imagePlus.setImage(bimg);
        ImageProcessor imageProcessor=imagePlus.getProcessor();
        imageProcessor.dilate();
        return SwingFXUtils.toFXImage(imagePlus.getBufferedImage(),null);
    }


    public static Image desenharImagemIJ(Image image,int x, int y) {
        ImagePlus imagePlus = new ImagePlus();
        BufferedImage bimg = SwingFXUtils.fromFXImage(image,null);
        imagePlus.setImage(bimg);
        ImageProcessor imageProcessor=imagePlus.getProcessor();
        imageProcessor.fillOval(x,y,3,3);
        return SwingFXUtils.toFXImage(imagePlus.getBufferedImage(),null);
    }
}
