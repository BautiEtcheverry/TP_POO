package backend;

import backend.model.Point;
import javafx.scene.control.ColorPicker;

public interface Drawers {
    /*
    Encargada de dibujar una elipse. Tambien utilizable para circulos.
    */
    void drawEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, boolean darkening, boolean lightening, boolean vMirror, boolean hMirror);

    /*
    Encargada de dibujar una rectangulos. Tambien utilizable para cuadrados.
     */
    void drawRectangle(Point topLeft,Point bottomRight, boolean darkening,boolean lightening,boolean vMirror, boolean hMirror);

    void drawVerticalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis);
    void drawVerticalMirrorRectangle(Point topLeft,Point bottomRight);
    void drawHorizontalMirrorRectangle(Point topLeft,Point bottomRight);
    void drawHorizontalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis);
}
