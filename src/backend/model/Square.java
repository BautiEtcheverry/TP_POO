package backend.model;


import backend.Drawers;

public class Square extends Rectangle {

    public Square(Point topLeft, double size, RGBColor fillColor, Drawers drawer) {
       super(topLeft,new Point(topLeft.getX() + size, topLeft.getY() + size), fillColor, drawer);
    }

    public void drawSelf(){getDrawer().drawRectangle(this);}

    public Point getTopLeft() {
        return super.getTopLeft();
    }

    public Point getBottomRight() {
        return super.getBottomRight();
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", super.getTopLeft(), super.getBottomRight());
    }

}
