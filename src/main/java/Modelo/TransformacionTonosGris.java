package Modelo;

import java.awt.*;

public class TransformacionTonosGris extends Transformacion{

    public TransformacionTonosGris(Imagen img) {
        super.img = img;
    }

    @Override
    public void transformar(Point punto1, Point punto2) {

        int inicioX = punto1.x;
        int inicioY = punto1.y;
        int limiteX = punto2.x;
        int limiteY = punto2.y;

        String txt = "Tonos de gris";

        for (int i = inicioX; i < limiteX; i++) {
            for (int j = inicioY; j <  limiteY; j++) {
                int aux = img.getColor(i,j);

                int r = (aux >> 16) & 0x000000ff;
                int g = (aux >> 8) & 0x000000ff;
                int b = aux & 0x000000ff;

                int color = (r+g+b) / 3;

                aux = (color << 16) | (color<<8) | color;

                img.setColor(aux,i,j);
            }
        }

        img.actualizarImagen(txt);
    }


}
