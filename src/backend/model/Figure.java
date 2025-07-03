package backend.model;

import backend.Drawers;

public abstract class Figure {
    private boolean lightening;
    private boolean darkening;
    private boolean hMirroring;
    private boolean vMirroring;
    private boolean selected;
    private BorderType borde;

    //Instancia de una interfaz que debe de ser implementada por el front para dibujar las figuras.
    private Drawers drawer;
    protected Drawers getDrawer(){return drawer;}

    //ESTO HAY QUE CAMBIARLO
    public void setDrawer(Drawers drawer){
        this.drawer=drawer;
    }

    /*
        Metodo para que cada figura se dibuje en el canvas 2d.
    */
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
    public boolean isSelected(){
        return selected;
    }
    public BorderType getBorderType(){
        return borde;
    }




    public void setBorderType(BorderType borderType) {
        this.borde = borderType;
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
    public void setSelected(boolean state){
        selected = state;
    }

    public abstract void drawVerticalMirror(Figure figure);
    public abstract void drawHorizontalMirror(Figure figure);

    public abstract void move(double diffX, double diffY);
    public abstract void moveTo(double newX, double newY); //Para mover una figura a una posicion especifica.
    public abstract boolean Belongs(Point punto);




    public abstract Figure clone(double offsetX, double offsetY);
    protected void setProperties(Figure otherFigure){
        otherFigure.setDrawer(this.getDrawer());
        otherFigure.setLightening(this.hasLightening());
        otherFigure.setDarkening(this.hasDarkening());
        otherFigure.sethMirroring(this.hasHMirroring());
        otherFigure.setvMirroring(this.hasVMirroring());
        otherFigure.setBorderType(this.getBorderType()); // NUEVO

    }
    public abstract Figure divideHorizontal(int N, int times);




}
