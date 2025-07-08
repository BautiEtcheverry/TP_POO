package backend.model.FigureBuilders;

import backend.Drawers;
import backend.model.Figure;
import backend.model.Point;
import backend.model.RGBColor;
import backend.model.Square;

public class SquareBuilder implements FigureBuilder {
    @Override
    public Figure builder(Point startPoint, Point endPoint, RGBColor figureColor, Drawers drawer) {
        double size = Math.abs(endPoint.getX() - startPoint.getY());
        return new Square(startPoint, size,figureColor,drawer);
    }

}
