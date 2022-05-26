package Vista;

import Modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelImagen extends JPanel implements PropertyChangeListener, MouseListener, KeyListener {

    private int ancho;
    private int alto;
    private Imagen img;
    private Point punto1;
    private Point punto2;
    private Logger logger = LogManager.getRootLogger();
    private FrameTransformaciones ft;


    public PanelImagen(int ancho, int alto, Imagen img) {
        this.ancho = ancho;
        this.alto = alto;
        this.img = img;
        init1();
    }

    public PanelImagen(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        init1();
    }

    public void init1(){
        setLayout(null);
        setSize(ancho,alto);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (img == null){
            return;
        }

        BufferedImage bi = new BufferedImage(img.getAncho(), img.getAlto(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();

        img.dibujar(g2d);

        if (punto1 != null && punto2 != null){
            dibujarAreaSeleccionada(g2d);
        }

        g.drawImage(bi,0,0,null);
    }

    private void dibujarAreaSeleccionada(Graphics2D g){
        int x1 = punto1.x;
        int y1 = punto1.y;
        int x2 = punto2.x;
        int y2 = punto2.y;
        g.setColor(Color.red);
        g.drawLine(x1,y1,x2,y1);
        g.drawLine(x2,y1,x2,y2);
        g.drawLine(x2,y2,x1,y2);
        g.drawLine(x1,y2,x1,y1);
    }

    public void verificarPunto2(){
        if (punto1.x < 0){
            punto1.x = 0;
        }

        if (punto2.x > img.getAncho()){
            punto2.x = img.getAncho();
        } else if (punto2.x < 0){
            punto2.x = 0;
        }

        if (punto2.y > img.getAlto()){
            punto2.y = img.getAlto();
        } else if (punto2.y < 0 ){
            punto2.y = 0;
        }
    }

    public void setPuntos(Point punto1, Point punto2){
        this.punto1 = punto1;
        this.punto2 = punto2;
        repaint();
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Imagen getImg() {
        return img;
    }

    public void setImg(Imagen img) {
        this.img = img;
        img.addObserver(this);
        img.leerImagen();
    }

    public Point getPunto1() {
        return punto1;
    }

    public void setPunto1(Point punto1) {
        this.punto1 = punto1;
    }

    public Point getPunto2() {
        return punto2;
    }

    public void setPunto2(Point punto2) {
        this.punto2 = punto2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            if (ft != null) {
                ft.dispose();
            }
            logger.info("Se hizo click en la posicion: " + e.getX() + " , " + (e.getY() - 50));
            this.punto1 = new Point(e.getX(), e.getY() - 50);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3){
            if (img != null){
                ft = new FrameTransformaciones(img);
            }
        }
        if (e.getButton() == MouseEvent.BUTTON2){
            if (ft != null) {
                ft.dispose();
            }
            Point aux = null;
            setPuntos(aux,aux);
        }
        if (e.getButton() == MouseEvent.BUTTON1){
            logger.info("Se solto el click en la posicion: " + e.getX() + " , " + (e.getY() - 50));
            this.punto2 = new Point(e.getX(), e.getY()-50);
            verificarPunto2();
            repaint();
            ft = new FrameTransformaciones(img,punto1,punto2);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("IMAGEN")){
            repaint();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            img.restarCambios();
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            img.aumentarCambios();
            repaint();
        }
    }
}
