package frontend;
import backend.Drawers;
import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class JFXDrawer implements Drawers{
    private GraphicsContext gc;
    
    public JFXDrawer(GraphicsContext gc){
        this.gc=gc;
    }
    
    @Override
    public void drawEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        gc.strokeOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
        gc.fillOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
    }
    @Override
    public void drawRectangle(Point topLeft,Point bottomRight){
        gc.strokeRect(topLeft.getX(), topLeft.getY(),
                Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
        gc.fillRect(topLeft.getX(), topLeft.getY(),
                Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
    }

    public void drawVerticalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        double mirroredX = centerPoint.getX() + sMayorAxis;
        Point mirroredCenter = new Point(mirroredX, centerPoint.getY());
        drawEllipse(mirroredCenter, sMayorAxis, sMinorAxis);
    }

    public void drawVerticalMirrorRectangle(Point topLeft, Point bottomRight) {
        double width = bottomRight.getX() - topLeft.getX();
        double newTopLeftX = bottomRight.getX();
        Point newTopLeft = new Point(newTopLeftX, topLeft.getY());
        Point newBottomRight = new Point(newTopLeftX + width, bottomRight.getY());

        drawRectangle(newTopLeft, newBottomRight);
    }

    public void drawHorizontalMirrorRectangle(Point topLeft, Point bottomRight){
        double height = bottomRight.getY() - topLeft.getY();
        double newTopLeftY = topLeft.getY() + height;
        Point newBottomRight = new Point(bottomRight.getX(),topLeft.getY());
        Point newTopLeft = new Point(topLeft.getX(),newTopLeftY);
        drawRectangle(newTopLeft, newBottomRight);
    }

    public void drawHorizontalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis){
        double mirroredY = centerPoint.getY() + sMinorAxis;
        Point mirroredCenter = new Point(centerPoint.getX(), mirroredY);
        drawEllipse(mirroredCenter, sMayorAxis, sMinorAxis);
    }

}
