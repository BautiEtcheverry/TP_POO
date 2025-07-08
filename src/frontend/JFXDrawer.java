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

    private void paintEllipseFill(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        gc.fillOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
    }

    private void paintEllipseStroke(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        gc.strokeOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
    }

    private void paintRectangleFill(Point topLeft, Point bottomRight) {
        gc.fillRect(topLeft.getX(), topLeft.getY(),
                Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
    }

    private void paintRectangleStroke(Point topLeft, Point bottomRight) {
        gc.strokeRect(topLeft.getX(), topLeft.getY(),
                Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
    }


    @Override
    public void drawEllipse(Ellipse ellipse){
        // Guardar el estilo del borde
        BorderType borderType = ellipse.getFormat().getBorderType();

        // Dibujar todos los rellenos primero
        gc.setFill(toColor(ellipse.getFormat().getFillColor()));
        paintEllipseFill(ellipse.getCenterPoint(), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());

        if(ellipse.getFormat().hasLightening()) {
            gc.setFill(CLARIFICATION);
            paintEllipseFill(ellipse.getCenterPoint(), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
        }
        if(ellipse.getFormat().hasDarkening()) {
            gc.setFill(DARKENING);
            paintEllipseFill(ellipse.getCenterPoint(), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
        }
        // Dibujar los reflejos (solo rellenos)
        if(ellipse.getFormat().hasVMirroring() || ellipse.getFormat().hasHMirroring()) {
            gc.setFill(toColor(ellipse.getFormat().getFillColor()));
            mirrorDrawer(ellipse);
        }

        // Finalmente, dibujar el borde de la figura original
        applyStrokeStyle(borderType);
        paintEllipseStroke(ellipse.getCenterPoint(), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());

    }


    @Override
    public void drawRectangle(Rectangle rectangle){
        // Guardar el estilo del borde
        BorderType borderType = rectangle.getFormat().getBorderType();

        // Dibujar todos los rellenos primero
        gc.setFill(toColor(rectangle.getFormat().getFillColor()));
        paintRectangleFill(rectangle.getTopLeft(), rectangle.getBottomRight());


        if(rectangle.getFormat().hasLightening()) {
            gc.setFill(CLARIFICATION);
            paintRectangleFill(rectangle.getTopLeft(), rectangle.getBottomRight());
        }
        if(rectangle.getFormat().hasDarkening()) {
            gc.setFill(DARKENING);
            paintRectangleFill(rectangle.getTopLeft(), rectangle.getBottomRight());
        }

        // Dibujar los reflejos (solo rellenos)
        if(rectangle.getFormat().hasVMirroring() || rectangle.getFormat().hasHMirroring()) {
            gc.setFill(toColor(rectangle.getFormat().getFillColor()));
            mirrorDrawer(rectangle);
        }

        // Finalmente, dibujar el borde de la figura original
        applyStrokeStyle(borderType);
        paintRectangleStroke(rectangle.getTopLeft(), rectangle.getBottomRight());

    }

    private void mirrorDrawer(Figure figure){
        gc.setFill(toColor(figure.getFormat().getFillColor()));
        if(figure.getFormat().hasVMirroring()){
            figure.drawVerticalMirror(figure);
        }
        if(figure.getFormat().hasHMirroring()){
            figure.drawHorizontalMirror(figure);
        }
    }

    public void drawVerticalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        double mirroredX = centerPoint.getX() + sMayorAxis;
        Point mirroredCenter = new Point(mirroredX, centerPoint.getY());
        paintEllipseFill(mirroredCenter, sMayorAxis, sMinorAxis);
        applyStrokeStyle(BorderType.NORMAL);
        paintEllipseStroke(mirroredCenter, sMayorAxis , sMinorAxis);
    }

    public void drawVerticalMirrorRectangle(Point topLeft, Point bottomRight) {
        double width = bottomRight.getX() - topLeft.getX();
        double newTopLeftX = bottomRight.getX();
        Point newTopLeft = new Point(newTopLeftX, topLeft.getY());
        Point newBottomRight = new Point(newTopLeftX + width, bottomRight.getY());
        paintRectangleFill(newTopLeft,newBottomRight);
        applyStrokeStyle(BorderType.NORMAL);
        paintRectangleStroke(newTopLeft, newBottomRight);

    }

    public void drawHorizontalMirrorRectangle(Point topLeft, Point bottomRight){
        double height = bottomRight.getY() - topLeft.getY();
        double newTopLeftY = topLeft.getY() + height;
        Point newBottomRight = new Point(bottomRight.getX(),topLeft.getY());
        Point newTopLeft = new Point(topLeft.getX(),newTopLeftY);
        paintRectangleFill(newTopLeft, newBottomRight);
        applyStrokeStyle(BorderType.NORMAL);
        paintRectangleStroke(newTopLeft, newBottomRight);
    }

    public void drawHorizontalMirrorEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis){
        double mirroredY = centerPoint.getY() + sMinorAxis;
        Point mirroredCenter = new Point(centerPoint.getX(), mirroredY);
        paintEllipseFill(mirroredCenter, sMayorAxis, sMinorAxis);
        applyStrokeStyle(BorderType.NORMAL);
        paintEllipseStroke(mirroredCenter, sMayorAxis, sMinorAxis);

    }

    private void applyStrokeStyle(BorderType type) {
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
