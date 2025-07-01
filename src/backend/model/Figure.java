package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.text.ElementIterator;
import java.util.HashMap;
import java.util.Map;

public abstract class Figure {
    private boolean lightening;
    private boolean darkening;
    private boolean hMirroring;
    private boolean vMirroring;


    //Metodo para que cada figura se dibuje en el canvas 2d.
    public abstract void drawSelf(GraphicsContext gc);

    public boolean hasLightening(){
        return lightening;
    }
    public  boolean hasDarkening(){
        return darkening;
    }
    public boolean hasHMirroring(){
        return  hMirroring;
    }
    public boolean hasVMirroring(){
        return  vMirroring;
    }

    public void setLightening(boolean state) {
        lightening = state;
    }

    public void setDarkening(boolean state){
        darkening = state;
    }
    public void sethMirroring(boolean state){
        hMirroring = state;
    }
    public void setvMirroring(boolean state){
        vMirroring = state;
    }
}
