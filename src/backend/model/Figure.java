package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public interface Figure {
    //Metodo para que cada figura se dibuje en el canvas 2d.
    void drawSelf(GraphicsContext gc);
}
