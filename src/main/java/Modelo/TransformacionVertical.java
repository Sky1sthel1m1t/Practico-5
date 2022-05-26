package Modelo;

import java.awt.*;

public class TransformacionVertical extends Transformacion {

    public TransformacionVertical(Imagen img) {
        super.img = img;
    }

    @Override
    public void transformar(Point punto1, Point punto2) {

        int inicioX = punto1.x;
        int inicioY = punto1.y;
        int limiteX = punto2.x;
        int limiteY = punto2.y;

        int filasAux = limiteX - inicioX;
        int columnasAux = limiteY - inicioY;

        String txt = "Vertical";

        int[][] aux = new int[filasAux][columnasAux];
        int[][] pixeles = img.getPixeles();

        int nuevaI = 0;

        for (int i = inicioX; i < limiteX; i++) {
            for (int j = limiteY - 1; j >= inicioY; j--) {
                int nuevaJ = (limiteY - 1) - j;
                aux[nuevaI][nuevaJ] = pixeles[i][j];
            }
            nuevaI++;
        }

        int k = 0;

        for (int i = inicioX; i < limiteX; i++) {
            int l = 0;
            for (int j = inicioY; j < limiteY; j++) {
                img.setColor(aux[k][l], i,j);
                l++;
            }
            k++;
        }

        img.actualizarImagen(txt);
    }
}
