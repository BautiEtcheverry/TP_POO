package backend.model;

public class Circle extends Ellipse implements Figure {


    public Circle(Point centerPoint, double radius) {
        super(centerPoint, 2 * radius, 2 * radius);
    }

    @Override
    public String toString() {
        return "Círculo [Centro: %s , Radio: %.2f}]".formatted(this.getCenterPoint(), this.getsMayorAxis() / 2);
    }

    public double getRadius() {
        return this.getsMayorAxis() / 2;
    }

}
