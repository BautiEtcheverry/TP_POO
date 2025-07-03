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

public class MultiplyFigure {
    private static final double XOFFSET = 30.0;
    private static final double YOFFSET = 30.0;

    public static void show(Figure figure, Consumer<List<Figure>> clonesCons){
        Stage pop = new Stage();
        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setTitle("Multiplicar");

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

                List<Figure> clones = new ArrayList<>();
                for (int i = 1; i < n; i++) {
                    clones.add(figure.clone(i * XOFFSET, i * YOFFSET));
                }

                clonesCons.accept(clones);
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
