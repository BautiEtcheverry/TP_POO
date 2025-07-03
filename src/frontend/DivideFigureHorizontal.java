package frontend;

import backend.model.Figure;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DivideFigureHorizontal {

    public static void show(Figure figure, Consumer<List<Figure>> clonesCons){
        Stage pop = new Stage();
        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setTitle("Divide Horizontally");

        Label label = new Label("Ingrese N:");
        TextField input = new TextField();

        // Botones para realizar o no la operación.
        Button cancel = new Button("Cancelar");
        Button accept = new Button("Aceptar");

        cancel.setOnAction(action -> pop.close());
        accept.setOnAction(action->{
            try {
                int n = Integer.parseInt(input.getText().trim());
                if (n < 1) throw new NumberFormatException();

                List<Figure> dividesH = new ArrayList<>();

                for (int i = 0; i< n; i++) {
                    dividesH.add(figure.divideHorizontal(n,i));
                }

                clonesCons.accept(dividesH);
                pop.close();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ingrese un número entero", ButtonType.OK);
                alert.showAndWait();
            }
        });

        VBox root = new VBox(10, label, input, new HBox(10, cancel, accept));
        root.setPadding(new Insets(10));
        pop.setScene(new Scene(root));
        pop.show();
    }

}
