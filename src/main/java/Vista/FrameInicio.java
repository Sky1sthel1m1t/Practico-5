package Vista;

import Modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class FrameInicio extends JFrame{

    private Logger logger = LogManager.getRootLogger();
    private Imagen img;
    private PanelImagen panelImagen;
    private Point punto1;
    private Point punto2;

    public FrameInicio() {
        init1();
    }

    public void init1(){
        setSize(1000,1000);
        setPreferredSize(new Dimension(1000,1000));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem mnSeleccionar = new JMenuItem("Cargar imagen");

        mnSeleccionar.addActionListener(e -> {

            FileNameExtensionFilter jpg = new FileNameExtensionFilter("Imagenes JPG", "jpg");
            FileNameExtensionFilter png = new FileNameExtensionFilter("Imagenes PNG", "png");

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(jpg);
            fc.addChoosableFileFilter(png);

            int respuesta = fc.showOpenDialog(null);

            if (respuesta == JFileChooser.APPROVE_OPTION){
                this.img = new Imagen(fc.getSelectedFile().getPath());
                Dimension imgDimension = new Dimension(img.getAncho(),img.getAlto());
                panelImagen.setImg(img);
                panelImagen.setPreferredSize(imgDimension);
                panelImagen.setSize(imgDimension);
                this.setPreferredSize(imgDimension);
                this.setSize(imgDimension);
                logger.debug("Las para el frame dimensiones son: " + img.getAncho() + " de ancho y " + img.getAlto() + " de alto");
                System.out.println("Las dimensiones del frame son: " + this.getWidth() + " de ancho y " + this.getHeight() + " de alto");
            }
        });

        menuBar.add(menuArchivo);
        menuArchivo.add(mnSeleccionar);
        this.setJMenuBar(menuBar);
        panelImagen = new PanelImagen(this.getWidth(),this.getHeight());
        this.add(panelImagen, BorderLayout.CENTER);
        this.addMouseListener(panelImagen);
        this.addKeyListener(panelImagen);
        this.pack();
    }
}

