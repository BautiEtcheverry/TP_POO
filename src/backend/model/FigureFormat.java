package backend.model;

public class FigureFormat {
    private boolean lightening;
    private boolean darkening;
    private boolean hMirroring;
    private boolean vMirroring;
    private boolean selected;
    private RGBColor fillColor;
    private BorderType border;

    public void setFillColor(double red, double green, double blue, double opacity){
        setFillColorRGB(new RGBColor(red,green,blue,opacity));
    }
    public void setFillColorRGB(RGBColor color){
        this.fillColor = color;
    }
    public RGBColor getFillColor() {
        return fillColor;
    }
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
        return border;
    }
    public void setBorderType(BorderType borderType) {
        this.border = borderType;
    }
    public void setLightening(boolean state) {
        lightening = state;
    }
    public void setDarkening(boolean state){
        darkening = state;
    }
    public void setHMirroring(boolean state){
        hMirroring = state;
    }
    public void setVMirroring(boolean state){
        vMirroring = state;
    }
    public void setSelected(boolean state){
        selected = state;
    }
    public void setFormat(FigureFormat otherFigure){
        otherFigure.setLightening(hasLightening());
        otherFigure.setDarkening(hasDarkening());
        otherFigure.setHMirroring(hasHMirroring());
        otherFigure.setVMirroring(hasVMirroring());
        otherFigure.setBorderType(getBorderType()); // NUEVO

    }
}
