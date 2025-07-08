package backend.model.FigureBuilders;

import backend.Drawers;
import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import backend.model.RGBColor;

public class EllipseBuilder implements FigureBuilder{
    @Override
    public Figure builder(Point startPoint, Point endPoint, RGBColor figureColor, Drawers drawer) {
        Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
        double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
        double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
        return new Ellipse(centerPoint, sMayorAxis, sMinorAxis, figureColor, drawer);
    }
}
