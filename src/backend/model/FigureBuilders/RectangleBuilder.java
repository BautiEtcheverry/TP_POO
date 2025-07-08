package backend.model.FigureBuilders;

import backend.Drawers;
import backend.model.Rectangle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.RGBColor;

public class RectangleBuilder implements FigureBuilder {
    @Override
    public Figure builder(Point startPoint, Point endPoint, RGBColor figureColor, Drawers drawer) {
        return new Rectangle(startPoint, endPoint, figureColor, drawer);
    }
}