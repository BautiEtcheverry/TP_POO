package frontend;

import backend.model.Figure;
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
    private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private final ToggleButton deleteButton = new ToggleButton("Borrar");
    private final ToggleGroup tools = new ToggleGroup();
    private Runnable onDeleteAction;
    private Figure selectedFigure;
    private final Map<ToggleButton, FigureBuilder> figureBuilderMap;
    private final ToggleButton[] toolsArr = {selectionButton,rectangleButton, squareButton, circleButton, ellipseButton,deleteButton};

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

        selectionButton.setToggleGroup(tools);
        selectionButton.setPrefWidth(90);
        selectionButton.setCursor(Cursor.HAND);
        deleteButton.setPrefWidth(90);
        deleteButton.setCursor(Cursor.HAND);
        deleteButton.setOnAction(actionEvent -> onDeleteAction.run());


    }

    public ToggleButton getSelectionButton(){return this.selectionButton;}

    public void setOnDeleteAction(Runnable action) {
        this.onDeleteAction = action;
    }

    public boolean getSelectionStatus(){return selectionButton.isSelected();}

    public FigureBuilder getBuilder(){
        Toggle bottomSelected = tools.getSelectedToggle();
        if(!(bottomSelected instanceof ToggleButton)){
            return null;
        }
        return figureBuilderMap.get(bottomSelected);
    }
    public ToggleGroup getGroup(){
        return tools;
    }
    public ToggleButton[] getBottomArr(){
        return this.toolsArr;
    }
}
