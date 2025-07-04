package backend.model;

import backend.Drawers;

public abstract class Figure {
    public FigureFormat format = new FigureFormat();

    //Instancia de una interfaz que debe de ser implementada por el front para dibujar las figuras.
    private Drawers drawer;
    public Drawers getDrawer(){return drawer;}

    //ESTO HAY QUE CAMBIARLO
    public void setDrawer(Drawers drawer){
        this.drawer=drawer;
    }

    /*
        Metodo para que cada figura se dibuje en el canvas 2d.
    */
    public abstract void drawSelf();

    public abstract void drawVerticalMirror(Figure figure);
    public abstract void drawHorizontalMirror(Figure figure);

    public abstract void move(double diffX, double diffY);
    public abstract void moveTo(double newX, double newY); //Para mover una figura a una posicion especifica.
    public abstract boolean Belongs(Point punto);


    public abstract Figure clone(double offsetX, double offsetY);
    public FigureFormat getFormat(){return format;}
    protected void setProperties(Figure otherFigure){
        otherFigure.setDrawer(getDrawer());
        format.setFormat(otherFigure.getFormat());
    }
    public abstract Figure divideHorizontal(int N, int times);
    public abstract Figure divideVertical(int N, int times);



}
