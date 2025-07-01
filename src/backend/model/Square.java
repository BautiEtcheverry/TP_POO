package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Rectangle {

    public Square(Point topLeft, double size) {
       super(topLeft,new Point(topLeft.getX() + size, topLeft.getY() + size));
    }
    public void drawSelf(GraphicsContext gc){
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
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
