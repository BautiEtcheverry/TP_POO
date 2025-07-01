package backend.model;

public class RGBColor {
    private double red;
    private double green;
    private double blue;
    private double opacity;

    public RGBColor(double red,double green, double blue, double opacity){
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.opacity = opacity;
    }

    public RGBColor(double red,double green, double blue){
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.opacity = 1;
    }

    public double getRed() {
        return red;
    }

    public double getBlue() {
        return blue;
    }

    public double getGreen() {
        return green;
    }
}
