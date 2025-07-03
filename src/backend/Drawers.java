package backend;

import backend.model.BorderType;
import backend.model.Ellipse;
import backend.model.Point;
import backend.model.Rectangle;

public interface Drawers {
    /*
    Encargada de dibujar una elipse. Tambien utilizable para circulos.
    */
    void drawEllipse(Ellipse ellipse);

    /*
    Encargada de dibujar una rectangulos. Tambien utilizable para cuadrados.
     */
    void drawRectangle(Rectangle rectangle);
    void drawVerticalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis);
    void drawVerticalMirrorRectangle(Point topLeft,Point bottomRight);
    void drawHorizontalMirrorRectangle(Point topLeft,Point bottomRight);
    void drawHorizontalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis);
}
