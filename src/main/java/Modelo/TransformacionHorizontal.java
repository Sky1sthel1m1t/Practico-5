package Modelo;

import java.awt.*;

public class TransformacionHorizontal extends Transformacion {

    public TransformacionHorizontal(Imagen img) {
        this.img = img;
    }

    @Override
    public void transformar(Point punto1, Point punto2) {

        int inicioX = punto1.x;
        int inicioY = punto1.y;
        int limiteX = punto2.x;
        int limiteY = punto2.y;

        int filasAux = limiteX - inicioX;
        int columnasAux = limiteY - inicioY;

        String txt = "Horizontal";

        int[][] aux = new int[filasAux][columnasAux];
        int[][] pixeles = img.getPixeles();

        for (int i = limiteX - 1; i >= inicioX; i--) {

            int nuevaI = (limiteX - 1) - i;
            int nuevaJ = 0;

            for (int j = inicioY; j < limiteY; j++) {
                aux[nuevaI][nuevaJ] = pixeles[i][j];
                nuevaJ++;
            }
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
