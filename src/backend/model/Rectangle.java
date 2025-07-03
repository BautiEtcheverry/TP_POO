package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.GregorianCalendar;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public void drawSelf(){
        getDrawer().drawRectangle(topLeft,bottomRight,this.hasDarkening(),this.hasLightening(),hasVMirroring(),hasHMirroring());
    }

    public void drawVerticalMirror(){
        getDrawer().drawVerticalMirrorRectangle(topLeft,bottomRight);
    }

    public void drawHorizontalMirror(){
        getDrawer().drawHorizontalMirrorRectangle(topLeft,bottomRight);
    }

    public void move(double diffX, double diffY){
        getTopLeft().changeX(diffX);
        getBottomRight().changeX(diffX);
        getTopLeft().changeY(diffY);
        getBottomRight().changeY(diffY);
    }
    public void moveTo(double newX, double newY){
        double diffX = newX - topLeft.getX();
        double diffY = newY - topLeft.getY();
        move(diffX, diffY);
    }

    @Override
    public boolean Belongs(Point eventPoint) {
        return eventPoint.getX() > this.getTopLeft().getX() && eventPoint.getX() < this.getBottomRight().getX() &&
                eventPoint.getY() > this.getTopLeft().getY() && eventPoint.getY() < this.getBottomRight().getY();
    }
    @Override
    public Figure clone(double offsetX, double offsetY){
        Rectangle newFigure = new Rectangle(
                new Point(topLeft.getX()+offsetX, topLeft.getY()+offsetY),
                new Point(bottomRight.getX()+offsetX, bottomRight.getY()+offsetY));
        setProperties(newFigure);
        return newFigure;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }


}
