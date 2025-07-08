package backend.model.FigureBuilders;

import backend.Drawers;
import backend.model.Figure;
import backend.model.Point;
import backend.model.RGBColor;

public interface FigureBuilder {
    Figure builder(Point startPoint, Point endPoint, RGBColor figureColor, Drawers drawer);
}
