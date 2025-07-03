package frontend;
import backend.Drawers;
import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class JFXDrawer implements Drawers{
    private GraphicsContext gc;
    private final ColorPicker fillColorPicker = new ColorPicker(Color.YELLOW);
    private static final Color CLARIFICATION = Color.rgb(255, 255, 255, 0.7);
    private static final Color DARKENING = Color.rgb(0, 0, 0, 0.7);


    public JFXDrawer(GraphicsContext gc){
        this.gc=gc;
    }

    private void paintE(Point centerPoint, double sMayorAxis, double sMinorAxis){
        gc.strokeOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
        gc.fillOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
    }

    private void paintR(Point topLeft, Point bottomRight){
        gc.strokeRect(topLeft.getX(), topLeft.getY(),
                Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
        gc.fillRect(topLeft.getX(), topLeft.getY(),
                Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
    }

    @Override
    public void drawEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis,boolean darkening,boolean lightening,boolean vMirror, boolean hMirror){
        gc.setFill(fillColorPicker.getValue());
        paintE(centerPoint,sMayorAxis,sMinorAxis);

        if(lightening) {
            gc.setFill(CLARIFICATION);
            paintE(centerPoint,sMayorAxis,sMinorAxis);
        }
        if(darkening) {
            gc.setFill(DARKENING);
            paintE(centerPoint,sMayorAxis,sMinorAxis);
        }
        if(vMirror){
            drawVerticalMirrorEllipse(centerPoint,sMayorAxis,sMinorAxis);
        }
        if(hMirror){
            drawHorizontalMirrorEllipse(centerPoint,sMayorAxis,sMinorAxis);
        }
    }


    @Override
    public void drawRectangle(Point topLeft,Point bottomRight,boolean darkening,boolean lightening,boolean vMirror, boolean hMirror){
        gc.setFill(fillColorPicker.getValue());
        paintR(topLeft,bottomRight);

        if(lightening) {
            gc.setFill(CLARIFICATION);
            paintR(topLeft,bottomRight);
        }
        if(darkening) {
            gc.setFill(DARKENING);
            paintR(topLeft,bottomRight);
        }
        if(vMirror){
            drawVerticalMirrorRectangle(topLeft,bottomRight);
        }
        if(hMirror){
            drawHorizontalMirrorRectangle(topLeft,bottomRight);
        }
    }

    public void drawVerticalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        double mirroredX = centerPoint.getX() + sMayorAxis;
        Point mirroredCenter = new Point(mirroredX, centerPoint.getY());
        gc.setFill(fillColorPicker.getValue());
        paintE(mirroredCenter, sMayorAxis, sMinorAxis);
    }

    public void drawVerticalMirrorRectangle(Point topLeft, Point bottomRight) {
        double width = bottomRight.getX() - topLeft.getX();
        double newTopLeftX = bottomRight.getX();
        Point newTopLeft = new Point(newTopLeftX, topLeft.getY());
        Point newBottomRight = new Point(newTopLeftX + width, bottomRight.getY());
        gc.setFill(fillColorPicker.getValue());
        paintR(newTopLeft,newBottomRight);
    }

    public void drawHorizontalMirrorRectangle(Point topLeft, Point bottomRight){
        double height = bottomRight.getY() - topLeft.getY();
        double newTopLeftY = topLeft.getY() + height;
        Point newBottomRight = new Point(bottomRight.getX(),topLeft.getY());
        Point newTopLeft = new Point(topLeft.getX(),newTopLeftY);
        gc.setFill(fillColorPicker.getValue());
        paintR(newTopLeft,newBottomRight);
    }

    public void drawHorizontalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis){
        double mirroredY = centerPoint.getY() + sMinorAxis;
        Point mirroredCenter = new Point(centerPoint.getX(), mirroredY);
        gc.setFill(fillColorPicker.getValue());
        paintE(mirroredCenter, sMayorAxis, sMinorAxis);
    }

}
