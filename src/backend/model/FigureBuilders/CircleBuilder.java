package backend.model.FigureBuilders;

import backend.Drawers;
import backend.model.*;

public class CircleBuilder implements FigureBuilder {
    @Override
    public Figure builder(Point startPoint, Point endPoint, RGBColor figureColor, Drawers drawer) {
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
        return new Circle(startPoint, circleRadius, figureColor,drawer);
    }
}
