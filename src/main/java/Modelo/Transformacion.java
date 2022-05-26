package Modelo;

import java.awt.*;

public abstract class Transformacion {

    protected Imagen img;

    public abstract void transformar(Point punto1, Point punto2);
}
