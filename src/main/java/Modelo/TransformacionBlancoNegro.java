package Modelo;

import java.awt.*;

public class TransformacionBlancoNegro extends Transformacion{

    public TransformacionBlancoNegro(Imagen img) {
        super.img = img;
    }

    @Override
    public void transformar(Point punto1, Point punto2) {

        int inicioX = punto1.x;
        int inicioY = punto1.y;
        int limiteX = punto2.x - 1;
        int limiteY = punto2.y - 1;

        String txt = "Blanco y negro";

        for (int i = inicioX; i < limiteX; i++) {
            for (int j = inicioY; j < limiteY; j++) {
                int aux = img.getColor(i,j);

                int r = (aux >> 16) & 0xff;
                int g = (aux >> 8) & 0xff;
                int b = aux & 0xff;

                int color = (r+g+b) / 3;

                if (color > 127){
                    color = (255 << 16) | (255 << 8) | 255;
                } else {
                    color = 0;
                }

                img.setColor(color,i,j);
            }
        }

        img.actualizarImagen(txt);
    }
}
