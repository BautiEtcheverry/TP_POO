package frontend;
import backend.Drawers;
import backend.model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class JFXDrawer implements Drawers{
    private GraphicsContext gc;
    private final ColorPicker fillColorPicker;
    private static final Color CLARIFICATION = Color.rgb(255, 255, 255, 0.7);
    private static final Color DARKENING = Color.rgb(0, 0, 0, 0.7);


    public JFXDrawer(GraphicsContext gc, ColorPicker fillColorPicker){
        this.gc=gc;
        this.fillColorPicker=fillColorPicker;
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
    public void drawEllipse(Ellipse ellipse){
        applyStrokeStyle(ellipse.getBorderType(), ellipse.isSelected());
        gc.setFill(toColor(ellipse.getfillColor()));
        paintE(ellipse.getCenterPoint(),ellipse.getsMayorAxis(),ellipse.getsMinorAxis());

        if(ellipse.hasLightening()) {
            gc.setFill(CLARIFICATION);
            paintE(ellipse.getCenterPoint(),ellipse.getsMayorAxis(),ellipse.getsMinorAxis());
        }
        if(ellipse.hasDarkening()) {
            gc.setFill(DARKENING);
            paintE(ellipse.getCenterPoint(),ellipse.getsMayorAxis(),ellipse.getsMinorAxis());
        }

        mirrorDrawer(ellipse);
    }


    @Override
    public void drawRectangle(Rectangle rectangle){
        applyStrokeStyle(rectangle.getBorderType(), rectangle.isSelected());
        gc.setFill(toColor(rectangle.getfillColor()));
        paintR(rectangle.getTopLeft(),rectangle.getBottomRight());

        if(rectangle.hasLightening()) {
            gc.setFill(CLARIFICATION);
            paintR(rectangle.getTopLeft(),rectangle.getBottomRight());
        }
        if(rectangle.hasDarkening()) {
            gc.setFill(DARKENING);
            paintR(rectangle.getTopLeft(),rectangle.getBottomRight());
        }

        mirrorDrawer(rectangle);
    }

    private void mirrorDrawer(Figure figure){
        gc.setFill(toColor(figure.getfillColor()));
        if(figure.hasVMirroring()){
            figure.drawVerticalMirror(figure);
        }
        if(figure.hasHMirroring()){
            figure.drawHorizontalMirror(figure);
        }
    }

    public void drawVerticalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        double mirroredX = centerPoint.getX() + sMayorAxis;
        Point mirroredCenter = new Point(mirroredX, centerPoint.getY());
        paintE(mirroredCenter, sMayorAxis, sMinorAxis);
    }

    public void drawVerticalMirrorRectangle(Point topLeft, Point bottomRight) {
        double width = bottomRight.getX() - topLeft.getX();
        double newTopLeftX = bottomRight.getX();
        Point newTopLeft = new Point(newTopLeftX, topLeft.getY());
        Point newBottomRight = new Point(newTopLeftX + width, bottomRight.getY());
        paintR(newTopLeft,newBottomRight);
    }

    public void drawHorizontalMirrorRectangle(Point topLeft, Point bottomRight){
        double height = bottomRight.getY() - topLeft.getY();
        double newTopLeftY = topLeft.getY() + height;
        Point newBottomRight = new Point(bottomRight.getX(),topLeft.getY());
        Point newTopLeft = new Point(topLeft.getX(),newTopLeftY);
        paintR(newTopLeft,newBottomRight);
    }

    public void drawHorizontalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis){
        double mirroredY = centerPoint.getY() + sMinorAxis;
        Point mirroredCenter = new Point(centerPoint.getX(), mirroredY);
        paintE(mirroredCenter, sMayorAxis, sMinorAxis);
    }

    private void applyStrokeStyle(BorderType type, boolean selected) {
        if (type == null) {
            type = BorderType.NORMAL;
        }

        switch (type) {
            case NORMAL -> {
                gc.setLineWidth(1);
                gc.setLineCap(null);
                gc.setLineDashes();
            }
            case PIXELADO -> {
                gc.setLineWidth(5);
                gc.setLineCap(javafx.scene.shape.StrokeLineCap.BUTT);
                gc.setLineDashes(1, 1);
            }
            case PUNTEADO_FINO -> {
                gc.setLineWidth(1);
                gc.setLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
                gc.setLineDashes(2, 6);
            }
            case PUNTEADO_COMPLEJO -> {
                gc.setLineWidth(3);
                gc.setLineCap(javafx.scene.shape.StrokeLineCap.SQUARE);
                gc.setLineDashes(25, 10, 15, 10);
            }
        }
    }
    //Metodo que recibe un RGBColor(clase del backEnd) y devuelve un Color(de javaFx).
    public Color toColor(RGBColor color){
        return new Color(color.getRed(),color.getGreen(),color.getBlue(),color.getOpacity());
    }


}
