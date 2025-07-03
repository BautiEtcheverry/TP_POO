package backend.model;

import backend.Drawers;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jdk.swing.interop.DragSourceContextWrapper;

import javax.swing.text.ElementIterator;
import java.util.HashMap;
import java.util.Map;

public abstract class Figure {
    private boolean lightening;
    private boolean darkening;
    private boolean hMirroring;
    private boolean vMirroring;

    //Instancia de una interfaz que debe de ser implementada por el front para dibujar las figuras.
    private Drawers drawer;
    protected Drawers getDrawer(){return drawer;}

    //ESTO HAY QUE CAMBIARLO
    public void setDrawer(Drawers drawer){
        this.drawer=drawer;
    }


    //Metodo para que cada figura se dibuje en el canvas 2d.
    public abstract void drawSelf();

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

    public abstract void drawVerticalMirror();
    public abstract void drawHorizontalMirror();

    public abstract void move(double diffX, double diffY);
    public abstract void moveTo(double newX, double newY); //Para mover una figura a una posicion especifica.
    public abstract boolean Belongs(Point punto);
}
