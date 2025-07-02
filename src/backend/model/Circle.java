package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, 2 * radius, 2 * radius);
    }

    public void drawSelf(){
        double diameter = getRadius() * 2;
        getDrawer().drawEllipse(centerPoint, diameter,diameter);

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
