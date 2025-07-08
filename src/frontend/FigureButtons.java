package frontend;

import  backend.model.FigureBuilders.FigureBuilder;
import backend.model.FigureBuilders.CircleBuilder;
import backend.model.FigureBuilders.EllipseBuilder;
import backend.model.FigureBuilders.RectangleBuilder;
import backend.model.FigureBuilders.SquareBuilder;
import javafx.scene.Cursor;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import java.util.Map;

public class FigureButtons {
    private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    private final ToggleButton squareButton = new ToggleButton("Cuadrado");
    private final ToggleButton circleButton = new ToggleButton("Círculo");
    private final ToggleButton ellipseButton = new ToggleButton("Elipse");
    private final ToggleGroup tools = new ToggleGroup();
    private final Map<ToggleButton, FigureBuilder> figureBuilderMap;
    private ToggleButton[] toolsArr = {rectangleButton, squareButton, circleButton, ellipseButton};

    public FigureButtons(){
        for (ToggleButton tool : toolsArr) {
            tool.setPrefWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        figureBuilderMap = Map.of(
                rectangleButton , new RectangleBuilder(),
                squareButton, new SquareBuilder(),
                ellipseButton ,new EllipseBuilder(),
                circleButton, new CircleBuilder()
        );
    }

    public FigureBuilder getBuilder(){
        Toggle bottomSelected = tools.getSelectedToggle();
        if(!(bottomSelected instanceof ToggleButton)){
            return null;
        }
        return figureBuilderMap.get(bottomSelected);
    }

    public ToggleButton[] getBottomGroup(){
        return this.toolsArr;
    }
}
