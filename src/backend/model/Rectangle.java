package backend.model;


import backend.Drawers;
import org.w3c.dom.html.HTMLImageElement;

import java.io.FileFilter;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, RGBColor fillColor, Drawers drawer) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        setDrawer(drawer);
        getFormat().setFillColorRGB(fillColor);
    }

    public void drawSelf(){
        getDrawer().drawRectangle(this);
    }

    public void drawVerticalMirror(Figure figure){
        getDrawer().drawVerticalMirrorRectangle(topLeft,bottomRight);
    }

    public void drawHorizontalMirror(Figure figure){
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
                new Point(bottomRight.getX()+offsetX, bottomRight.getY()+offsetY),
                getFormat().getFillColor());
        setProperties(newFigure);
        return newFigure;
    }


    public Figure divideHorizontal(int N, int times){
        double height = ( bottomRight.getY() - topLeft.getY()) / N; //alto de cada nuevo rectangulo
        double length = (bottomRight.getX() - topLeft.getX()) /N;// ancho de cada nuevo rectangulo
        double startX = topLeft.getX() + times * length; // coordenada x donde empieza cada nuevo cuadrado
        double middle = topLeft.getY() + (bottomRight.getY() - topLeft.getY()) / 2; //medio
        double YCoordinateTL = middle - height/2;
        double YCoordinateBR = middle + height/2;
        Point newCoordinateTopLeft = new Point( startX,YCoordinateTL);
        Point newCoordinateBottomRight = new Point(startX + length, YCoordinateBR);
        Rectangle newFigure = new Rectangle(newCoordinateTopLeft,newCoordinateBottomRight, getFormat().getFillColor());
        setProperties(newFigure);
        return  newFigure;
    }

    public Figure divideVertical(int N,  int times){
        double height = ( bottomRight.getY() - topLeft.getY()) / N;
        double length = (bottomRight.getX() - topLeft.getX()) / N;
        double middle = bottomRight.getX() - (bottomRight.getX() - topLeft.getX()) / 2;
        double startY = bottomRight.getY() - height * times ;
        double XCoordinateTL = middle - length/2;
        double XCoordinateBR = middle + length/2;
        Point newCoordinateTopLeft = new Point( XCoordinateTL, startY - height );
        Point newCoordinateBottomRight = new Point(XCoordinateBR, startY);
        Rectangle newFigure = new Rectangle(newCoordinateTopLeft,newCoordinateBottomRight, getFormat().getFillColor());
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
