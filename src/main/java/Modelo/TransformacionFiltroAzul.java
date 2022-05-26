package Modelo;

import java.awt.*;

public class TransformacionFiltroAzul extends Transformacion{

    public TransformacionFiltroAzul(Imagen img) {
        super.img = img;
    }

    @Override
    public void transformar(Point punto1, Point punto2) {
        int inicioX = punto1.x;
        int inicioY = punto1.y;
        int limiteX = punto2.x;
        int limiteY = punto2.y;
        String txt = "Filtro azul";

        for (int i = inicioX; i < limiteX; i++) {
            for (int j = inicioY; j < limiteY; j++) {
                int aux = img.getColor(i,j);

                int rojo = (aux >> 16) & 0xff;
                int verde = (aux >> 8) & 0xff;
                int azul = (aux) & 0xff;

                aux = azul;

                img.setColor(aux,i,j);
            }
        }

        img.actualizarImagen(txt);
    }
}
