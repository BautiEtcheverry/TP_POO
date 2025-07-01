package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public abstract class Figure {
    //Metodo para que cada figura se dibuje en el canvas 2d.
    public abstract void drawSelf(GraphicsContext gc);
    private boolean lightening;
    private boolean darkening;
    private boolean hMirroring;
    private boolean vMirroring;
}
