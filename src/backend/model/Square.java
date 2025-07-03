package backend.model;


public class Square extends Rectangle {

    public Square(Point topLeft, double size, RGBColor fillColor) {
       super(topLeft,new Point(topLeft.getX() + size, topLeft.getY() + size), fillColor);
    }

    public void drawSelf(){
        getDrawer().drawRectangle(getTopLeft(),getBottomRight(),hasDarkening(),hasLightening(),hasVMirroring(),hasHMirroring(), isSelected(), getBorderType());
    }

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
