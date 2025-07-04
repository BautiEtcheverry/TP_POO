package backend.model;

public class FigureFormat {
    private boolean lightening;
    private boolean darkening;
    private boolean hMirroring;
    private boolean vMirroring;
    private boolean selected;
    private RGBColor fillColor;
    private BorderType border;

    protected void setFillColor(double red, double green, double blue, double opacity){
        setFillColorRGB(new RGBColor(red,green,blue,opacity));
    }
    protected void setFillColorRGB(RGBColor color){
        this.fillColor = color;
    }
    protected RGBColor getFillColor() {
        return fillColor;
    }
    protected boolean getLightening(){
        return lightening;
    }
    protected  boolean getDarkening(){
        return darkening;
    }
    protected boolean getHMirroring(){
        return  hMirroring;
    }
    protected boolean getVMirroring(){
        return  vMirroring;
    }
    protected boolean isSelected(){
        return selected;
    }
    protected BorderType getBorderType(){
        return border;
    }
    protected void setBorderType(BorderType borderType) {
        this.border = borderType;
    }
    protected void setLightening(boolean state) {
        lightening = state;
    }
    protected void setDarkening(boolean state){
        darkening = state;
    }
    protected void setHMirroring(boolean state){
        hMirroring = state;
    }
    protected void setVMirroring(boolean state){
        vMirroring = state;
    }
    protected void setSelected(boolean state){
        selected = state;
    }
    protected void setFormat(FigureFormat otherFigure){
        otherFigure.setLightening(this.getLightening());
        otherFigure.setDarkening(this.getDarkening());
        otherFigure.setHMirroring(this.getHMirroring());
        otherFigure.setVMirroring(this.getVMirroring());
        otherFigure.setBorderType(this.getBorderType()); // NUEVO

    }
}
