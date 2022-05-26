package Vista;

import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTransformaciones extends JFrame implements ActionListener {

    private Imagen img;

    private JLabel lbEfectos = new JLabel("Efectos");
    private JButton btnTransformacionHorizontal = new JButton("Espejo Horizontal");
    private JButton btnTransformacionVertical = new JButton("Espejo Vertical");
    private JButton btnTransformacionPixelado = new JButton("Pixelado");
    private JButton btnTransformacionSuavizado = new JButton("Suavizado");
    private JButton btnTransformacionBlancoNegro = new JButton("Blanco y Negro");
    private JButton btnImagenOriginal = new JButton("Imagen Original");
    private JButton btnTransformacionTonosGrises = new JButton("Tonos Grises");
    private JButton btnTransformacionFiltroRojo = new JButton("Filtro Rojo");
    private JButton btnTransformacionFiltroVerde = new JButton("Filtro Verde");
    private JButton btnTransformacionFiltroAzul = new JButton("Filtro Azul");

    private Point punto1;
    private Point punto2;

    public FrameTransformaciones(Imagen img) {
        this.img = img;
        this.punto1 = new Point(0,0);
        this.punto2 = new Point(img.getAncho(), img.getAlto());
        init1();
    }

    public FrameTransformaciones(Imagen img, Point punto1, Point punto2) {
        this.img = img;
        this.punto1 = punto1;
        this.punto2 = punto2;
        init1();
    }

    public void init1() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(250, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLayout(null);
        panel.setVisible(true);

        int x = 45;
        int y = 10;
        lbEfectos.setFont(new Font("Arial", Font.BOLD, 20));
        lbEfectos.setHorizontalAlignment(SwingConstants.CENTER);
        lbEfectos.setBounds(x, y, 150, 50);
        y += 50;
        btnTransformacionVertical.setBounds(x, y, 150, 30);
        y += 40;
        btnTransformacionHorizontal.setBounds(x, y, 150, 30);
        y += 40;
        btnTransformacionPixelado.setBounds(x,y,150,30);
        y += 40;
        btnTransformacionSuavizado.setBounds(x,y,150,30);
        y += 40;
        btnTransformacionBlancoNegro.setBounds(x,y,150,30);
        y += 40;
        btnTransformacionTonosGrises.setBounds(x,y,150,30);
        y += 40;
        btnTransformacionFiltroRojo.setBounds(x,y,150,30);
        y += 40;
        btnTransformacionFiltroVerde.setBounds(x,y,150,30);
        y += 40;
        btnTransformacionFiltroAzul.setBounds(x,y,150,30);
        y += 40;
        btnImagenOriginal.setBounds(x, y, 150, 30);

        btnImagenOriginal.addActionListener(this);
        btnTransformacionBlancoNegro.addActionListener(this);
        btnTransformacionHorizontal.addActionListener(this);
        btnTransformacionVertical.addActionListener(this);
        btnTransformacionPixelado.addActionListener(this);
        btnTransformacionSuavizado.addActionListener(this);
        btnTransformacionTonosGrises.addActionListener(this);
        btnTransformacionFiltroRojo.addActionListener(this);
        btnTransformacionFiltroVerde.addActionListener(this);
        btnTransformacionFiltroAzul.addActionListener(this);

        panel.add(lbEfectos);
        panel.add(btnTransformacionVertical);
        panel.add(btnTransformacionHorizontal);
        panel.add(btnImagenOriginal);
        panel.add(btnTransformacionPixelado);
        panel.add(btnTransformacionSuavizado);
        panel.add(btnTransformacionBlancoNegro);
        panel.add(btnTransformacionTonosGrises);
        panel.add(btnTransformacionFiltroRojo);
        panel.add(btnTransformacionFiltroAzul);
        panel.add(btnTransformacionFiltroVerde);

        this.add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnImagenOriginal) {

            img.restablecerImagen();

        } else if (e.getSource() == btnTransformacionVertical) {

            TransformacionVertical transformacionVertical = new TransformacionVertical(img);
            transformacionVertical.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionHorizontal) {

            TransformacionHorizontal transformacionHorizontal = new TransformacionHorizontal(img);
            transformacionHorizontal.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionPixelado){

            TransformacionPixelado transformacionPixelado = new TransformacionPixelado(img);
            transformacionPixelado.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionSuavizado){

            JOptionPane.showMessageDialog(null,"Este filtro está en construcción");
//            TransformacionSuavizado transformacionSuavizado = new TransformacionSuavizado(img);
//            transformacionSuavizado.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionBlancoNegro){

            TransformacionBlancoNegro transformacionBlancoNegro = new TransformacionBlancoNegro(img);
            transformacionBlancoNegro.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionTonosGrises){

            TransformacionTonosGris transformacionTonosGris = new TransformacionTonosGris(img);
            transformacionTonosGris.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionFiltroRojo){

            TransformarFiltroRojo transformarFiltroRojo = new TransformarFiltroRojo(img);
            transformarFiltroRojo.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionFiltroVerde){

            TransformacionFiltroVerde transformacionFiltroVerde = new TransformacionFiltroVerde(img);
            transformacionFiltroVerde.transformar(punto1,punto2);

        } else if (e.getSource() == btnTransformacionFiltroAzul){

            TransformacionFiltroAzul transformacionFiltroAzul = new TransformacionFiltroAzul(img);
            transformacionFiltroAzul.transformar(punto1,punto2);

        }
    }
}
