package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Figure {

    private final Point topLeft, bottomRight;

    public Square(Point topLeft, double size) {
        this.topLeft = topLeft;
        this.bottomRight = new Point(topLeft.getX() + size, topLeft.getY() + size);
    }
    public void drawSelf(GraphicsContext gc){
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }
    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

}
