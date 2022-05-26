package Modelo;

import java.awt.*;

public class TransformacionPixelado extends Transformacion {

    public TransformacionPixelado(Imagen img) {
        super.img = img;
    }

    @Override
    public void transformar(Point punto1, Point punto2) {

        int inicioX = punto1.x;
        int inicioY = punto1.y;
        int limiteX = punto2.x;
        int limiteY = punto2.y;

        String txt = "Pixelado";

        for (int i = inicioX; i < limiteX; i+=3) {
            for (int j = inicioY; j < limiteY; j+=3) {
                int color = img.getColor(i,j);
                copiarColores(i,j,color,img, limiteX,limiteY);
            }
        }

//        for (int i = 0; i < img.getAncho(); i+=3) {
//            for (int j = 0; j < img.getAlto(); j+=3) {
//                int color = img.getColor(i,j);
//                copiarColores(i,j,color,img);
//            }
//        }

        img.actualizarImagen(txt);
    }

    private void copiarColores(int i, int j, int color, Imagen img, int limiteTotalX, int limiteTotalY){
        int iLimite = i + 3;
        int jLimite = j + 3;

        if (iLimite > limiteTotalX){
            iLimite = iLimite - limiteTotalX;
        }
        if (jLimite > limiteTotalY){
            jLimite = jLimite - limiteTotalY;
        }

        for (int k = i; k < iLimite; k++) {
            for (int l = j; l < jLimite; l++) {
                img.setColor(color,k,l);
            }
        }
    }

}
