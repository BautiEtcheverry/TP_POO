package backend.model;

public class Ellipse extends Figure {

    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    public void drawSelf(){
        getDrawer().drawEllipse(this);
    }

    public void drawVerticalMirror(Figure figure){
        getDrawer().drawVerticalMirrorEllipse(centerPoint,sMayorAxis,sMinorAxis);
    }

    public void drawHorizontalMirror(Figure figure){
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
    public void moveTo(double newX, double newY){
        double diffX = newX - centerPoint.getX();
        double diffY = newY - centerPoint.getY();
        move(diffX, diffY);
    }

    @Override
    public boolean Belongs(Point eventPoint) {
        return ((Math.pow(eventPoint.getX() - this.getCenterPoint().getX(), 2) / Math.pow(this.getsMayorAxis(), 2)) +
                (Math.pow(eventPoint.getY() - this.getCenterPoint().getY(), 2) / Math.pow(this.getsMinorAxis(), 2))) <= 0.30;
    }
    @Override
    public Figure clone(double offsetX, double offsetY){
        Ellipse newFigure = new Ellipse(new Point(centerPoint.getX()+offsetX, centerPoint.getY()+offsetY), sMayorAxis,sMinorAxis);
        setProperties(newFigure);
        return newFigure;
    }

    public Figure divideHorizontal(int N,int times){
        //cada vez que lo llame
        Ellipse newFigure = new Ellipse(centerPoint, sMayorAxis/N, sMinorAxis);
        setProperties(newFigure);
        return newFigure;
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
