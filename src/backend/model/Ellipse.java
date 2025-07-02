package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Figure {

    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    public void drawSelf(){
        getDrawer().drawEllipse(centerPoint, sMayorAxis,sMinorAxis);
    }

    public void drawVerticalMirror(){
        getDrawer().drawVerticalMirrorEllipse(centerPoint,sMayorAxis,sMinorAxis);
    }

    public void drawHorizontalMirror(){
        getDrawer().drawHorizontalMirrorEllipse(centerPoint,sMayorAxis,sMinorAxis);
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }
    public void move(double diffX, double diffY){
        getCenterPoint().changeX(diffX);
        getCenterPoint().changeY(diffY);
    }
    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

}
