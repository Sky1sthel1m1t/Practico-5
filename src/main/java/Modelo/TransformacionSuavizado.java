package Modelo;

import java.awt.*;

public class TransformacionSuavizado extends Transformacion {

    private double[][] filtro;

    public TransformacionSuavizado(Imagen img) {
        super.img = img;
        cargarFiltro();
    }

    @Override
    public void transformar(Point punto1, Point punto2) {
        int[][] aux = new int[img.getAncho()][img.getAlto()];

        for (int i = 0; i < img.getAncho() - 1; i++) {
            for (int j = 0; j < img.getAlto() - 1; j++) {
                aux[i][j] = calcularColor(img.getPixeles(), i,j);
//                int color = calcularColor(img.getPixeles(), i,j);
//                img.setColor(color,i,j);
            }
        }
        img.setPixeles(aux);
//        img.actualizarImagen();
    }

    private int calcularColor(int[][] img, int i, int j){
        int color;

        int iLimite = i + 1;
        int jLimite = j + 1;

        i -= 1;
        j -= 1;

        int[][] aux = new int[3][3];

        int iFiltro = 0;

        for (int k = i; k <= iLimite; k++) {
            int jFiltro = 0;
            for (int l = j; l <= jLimite; l++) {
                try{
                    aux[iFiltro][jFiltro] = img[k][l];
                } catch (Exception e){
                    aux[iFiltro][jFiltro] = 0;
                }
                jFiltro++;
            }
            iFiltro++;
        }

        int r = 0;
        int g = 0;
        int b = 0;

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                int colorAux = aux[k][l];

                r += (colorAux >> 16) * filtro[k][l];
                g += ((colorAux >> 8) & 0xff) * filtro[k][l];
                b += (colorAux & 0xff) * filtro[k][l];

            }
        }

        color = (r<<16) | (g<<8) | b;

//        for (int k = i; k <= iLimite; k++) {
//
//            int jFiltro = 0;
//
//            for (int l = j; l <= jLimite; l++) {
//
//                try {
//                    color += img[k][l] * filtro[iFiltro][jFiltro] ;
//                } catch (Exception e){
//                    color += 0;
//                }
//
//                jFiltro++;
//            }
//
//            iFiltro++;
//
//        }

        return color;
    }

    private void cargarFiltro(){
        this.filtro = new double[3][3];
        filtro[0][0] = 1;
        filtro[0][1] = 2;
        filtro[0][2] = 1;
        filtro[1][0] = 2;
        filtro[1][1] = 4;
        filtro[1][2] = 2;
        filtro[2][0] = 1;
        filtro[2][1] = 2;
        filtro[2][2] = 1;
    }
}
