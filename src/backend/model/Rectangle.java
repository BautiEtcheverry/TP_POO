package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public void drawSelf(){
        getDrawer().drawRectangle(topLeft,bottomRight);
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
