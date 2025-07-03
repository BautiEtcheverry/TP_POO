package frontend;


import backend.model.Figure;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class MoveFigure {
    public static void show(Figure figure, Runnable redrawer){
        Stage pop = new Stage();
        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setTitle("Trasladar");

        Label title = new Label("Trasladar");
        title.setStyle(" -fx-font-weight: bold;");

        Label coordsLabel = new Label("Ingrese la coordenada");
        TextField coords = new TextField("0,0"); //Coordenadas por default

        // Botones para realizar o no la operación.
        Button cancel = new Button("Cancelar");
        Button accept = new Button("Aceptar");

        cancel.setOnAction(action -> pop.close());
        accept.setOnAction(action ->{
            String userText = coords.getText().trim();
            String[] coordsComps = userText.split(",");
            if (coordsComps.length == 2) {
                try {
                    double x = Double.parseDouble(coordsComps[0]);
                    double y = Double.parseDouble(coordsComps[1]);
                    if (figure != null) {
                        figure.moveTo(x, y);
                        if (redrawer != null) redrawer.run();
                        pop.close();
                    }
                } catch (NumberFormatException ex) {
                    showError("Coordenadas inválidas. Debe usar formato: x,y");
                }
            } else {
                showError("Coordenadas inválidas. Debe usar formato: x,y");
            }
        });

        HBox inputRow = new HBox(10, coordsLabel, coords);
        inputRow.setAlignment(Pos.CENTER_LEFT);

        HBox buttonRow = new HBox(10, cancel, accept);
        buttonRow.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(15, title, inputRow, buttonRow);
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color: #f5f5f5;");
        layout.setPrefWidth(300);

        pop.setScene(new Scene(layout));
        pop.showAndWait();
    }

    private static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Entrada inválida");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

