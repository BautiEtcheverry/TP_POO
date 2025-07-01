package frontend;

import backend.CanvasState;
import javafx.scene.control.CheckBox;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MainFrame extends VBox {

    public MainFrame(CanvasState canvasState) {

        getChildren().add(new AppMenuBar());
        StatusPane statusPane = new StatusPane();
        getChildren().add(new PaintPane(canvasState, statusPane));
        getChildren().add(statusPane);
    }

}

