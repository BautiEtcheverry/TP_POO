package frontend;
import backend.Drawers;
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
}
