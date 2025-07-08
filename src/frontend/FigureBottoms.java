package frontend;

import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;
import java.util.Map;

public class FigureBottoms {
    private final ToggleButton deleteButton = new ToggleButton("Borrar");
    private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    private final ToggleButton squareButton = new ToggleButton("Cuadrado");
    private final ToggleButton circleButton = new ToggleButton("Círculo");
    private final ToggleButton ellipseButton = new ToggleButton("Elipse");
    private final ToggleGroup tools = new ToggleGroup();
    private final Map<ToggleButton, FigureBuilder> figureBuilderMap = new HashMap<>();
    public FigureBottoms(){
        ToggleButton[] toolsArr = {selectionButton, rectangleButton, squareButton, circleButton, ellipseButton, deleteButton};
        ToggleGroup tools = new ToggleGroup();

        for (ToggleButton tool : toolsArr) {
            tool.setPrefWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        figureBuilderMap = Map.of(
                rectangleButton = ne
        )



    }



    public ToggleGroup getBottomGroup(){
        return tools;
    }

}
