package backend.model;


import backend.Drawers;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius, RGBColor fillColor, Drawers drawer) {
        super(centerPoint, 2 * radius, 2 * radius, fillColor,drawer);
    }


    @Override
    public String toString() {
        return "Círculo [Centro: %s , Radio: %.2f}]".formatted(this.getCenterPoint(), this.getsMayorAxis() / 2);
    }
    public double getRadius() {
        return this.getsMayorAxis() / 2;
    }


    @Override
    public boolean Belongs(Point eventPoint) {
        return Math.sqrt(Math.pow(this.getCenterPoint().getX() - eventPoint.getX(), 2) +
                Math.pow(this.getCenterPoint().getY() - eventPoint.getY(), 2)) < this.getRadius();
    }


}
