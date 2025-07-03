package backend.model;

import backend.Drawers;

public abstract class Figure {
//    private boolean lightening;
//    private boolean darkening;
//    private boolean hMirroring;
//    private boolean vMirroring;
//    private boolean selected;
//    private RGBColor fillColor;
//    private BorderType borde;
    private FigureFormat format = new FigureFormat();
    //Instancia de una interfaz que debe de ser implementada por el front para dibujar las figuras.
    private Drawers drawer;
    protected Drawers getDrawer(){return drawer;}

    //ESTO HAY QUE CAMBIARLO
    public void setDrawer(Drawers drawer){
        this.drawer=drawer;
    }
   public void setFillColor(double red, double green, double blue, double opacity){
        setFillColorRGB(new RGBColor(red,green,blue,opacity));
    }
    protected void setFillColorRGB(RGBColor color){
        format.setFillColorRGB( color);
    }
    public RGBColor getfillColor(){
        return format.getFillColor();
    }

    /*
        Metodo para que cada figura se dibuje en el canvas 2d.
    */
    public abstract void drawSelf();

    public boolean hasLightening(){
        return format.getLightening();
    }
    public  boolean hasDarkening(){
        return format.getDarkening();
    }
    public boolean hasHMirroring(){
        return  format.getHMirroring();
    }
    public boolean hasVMirroring(){
        return  format.getVMirroring();
    }
    public boolean isSelected(){
        return format.isSelected();
    }
    public BorderType getBorderType(){
        return format.getBorderType();
    }
    public void setBorderType(BorderType borderType) {
       format.setBorderType(borderType);
    }

    public void setLightening(boolean state) {
        format.setLightening(state);
    }
    public void setDarkening(boolean state){
        format.setDarkening(state);
    }
    public void sethMirroring(boolean state){
        format.setHMirroring(state);
    }
    public void setvMirroring(boolean state){
        format.setVMirroring(state);
    }
    public void setSelected(boolean state){
        format.setSelected(state);
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
    public abstract Figure divideVertical(int N, int times);



}
