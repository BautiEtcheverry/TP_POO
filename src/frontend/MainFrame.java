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

        CheckBox aclaramiento = new CheckBox("Aclaramiento");
        CheckBox oscurecimiento = new CheckBox("Oscurecimiento");
        CheckBox espejoH = new CheckBox("Espejo Horizontal");
        CheckBox espejoV = new CheckBox("Espejo Vertical");
        HBox effectBar = new HBox(10);
        effectBar.setPadding(new Insets(5));
        effectBar.setStyle("-fx-background-color: #969696;");
        effectBar.getChildren().addAll(
                new Label("Efectos:"),
                aclaramiento,
                oscurecimiento,
                espejoH,
                espejoV
        );
        getChildren().add(effectBar);

        StatusPane statusPane = new StatusPane();
        getChildren().add(new PaintPane(canvasState, statusPane));
        getChildren().add(statusPane);
    }

}
