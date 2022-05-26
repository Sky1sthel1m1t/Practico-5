package Modelo;

import Vista.PanelImagen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Imagen {

    private String path;
    private int[][] pixeles;
    private int alto;
    private int ancho;
    private PropertyChangeSupport cambios;
    private BufferedImage imgOriginal;

    private Lista<int[][]> listaCambios = new Lista<>();
    private int nroCambios = 0;
    private Lista<String> cambiosString = new Lista<>();

    public Imagen(String path) {
        this.path = path;
        cambios = new PropertyChangeSupport(this);
        leerImagen();
        añadirCambios("Original");
        setNroCambios(0);
    }

    public void leerImagen(){
        BufferedImage bi = null;

        try{
            File f =  new File(path);
            bi = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.imgOriginal = bi;
        initImagen(bi);
    }

    private void initImagen(BufferedImage bi){
        ancho = bi.getWidth();
        alto = bi.getHeight();

        pixeles = new int[ancho][alto];

        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int rgb = bi.getRGB(i,j);
                pixeles[i][j] = rgb;
            }
        }

        cambios.firePropertyChange("IMAGEN", 1,0);
    }

    public void dibujar(Graphics2D g){
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                g.setColor(new Color(pixeles[i][j]));
                g.drawLine(i,j,i,j);
            }
        }
    }

    public void añadirCambios(String txt){

        int aux[][] = new int[ancho][alto];

        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                aux[i][j] = this.pixeles[i][j];
            }
        }

        if (nroCambios < listaCambios.size() - 1) {
            System.out.print("Se estan eliminando los siguientes nodos: ");

            int vecesEliminar = listaCambios.size() - (nroCambios + 1);
            int indiceEliminar = nroCambios + 1;

            for (int i = nroCambios + 1; i <= vecesEliminar; i++) {
                System.out.print(cambiosString.get(indiceEliminar) + " -> ");
                cambiosString.eliminar(indiceEliminar);
                listaCambios.eliminar(indiceEliminar);
            }

        }

        listaCambios.add(aux);
        cambiosString.add(txt);

        String aux1 = "";
        for (int i = 0; i < cambiosString.size(); i++) {
            aux1 += cambiosString.get(i) + " -> ";
        }

        System.out.println("La lista quedaría así: " + aux1);

        this.nroCambios++;
    }

    public void restarCambios(){
        nroCambios--;

        if (nroCambios < 0){
            nroCambios = 0;
        }
        setPixeles(listaCambios.get(nroCambios));
        System.out.println(cambiosString.get(nroCambios));
    }

    public void aumentarCambios(){
        nroCambios++;

        if (nroCambios > listaCambios.size()-1){
            nroCambios = listaCambios.size()-1;
        }

        setPixeles(listaCambios.get(nroCambios));
        System.out.println(cambiosString.get(nroCambios));
    }

    public void seleccionarCambios(int pos){
        this.setPixeles(listaCambios.get(pos));
    }

    public void addObserver(PanelImagen panelImagen){
        cambios.addPropertyChangeListener(panelImagen);
    }

    public void setColor(int color, int i, int j){
        this.pixeles[i][j] = color;
    }

    public int getColor(int i, int j){
        return this.pixeles[i][j];
    }

    public void actualizarImagen(String txt){
        cambios.firePropertyChange("IMAGEN",1,0);
        añadirCambios(txt);
    }

    public void restablecerImagen(){
//        setPixeles(imgOriginal);
//        actualizarImagen();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int[][] getPixeles() {
        return pixeles;
    }

    public void setPixeles(int[][] pixeles) {
        this.pixeles = pixeles;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getNroCambios() {
        return nroCambios;
    }

    public void setNroCambios(int nroCambios) {
        this.nroCambios = nroCambios;
    }

    public Lista<int[][]> getListaCambios() {
        return listaCambios;
    }

    public void setListaCambios(Lista<int[][]> listaCambios) {
        this.listaCambios = listaCambios;
    }
}
