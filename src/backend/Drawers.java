package backend;

import backend.model.Point;

public interface Drawers {
    /*
    Encargada de dibujar una elipse. Tambien utilizable para circulos.
    */
    void drawEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis);

    /*
    Encargada de dibujar una rectangulos. Tambien utilizable para cuadrados.
     */
    void drawRectangle(Point topLeft,Point bottomRight);

    void drawVerticalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis);
    void drawVerticalMirrorRectangle(Point topLeft,Point bottomRight);
    void drawHorizontalMirrorRectangle(Point topLeft,Point bottomRight);
    void drawHorizontalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis);
}
