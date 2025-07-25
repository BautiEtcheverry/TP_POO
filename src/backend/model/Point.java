package backend.model;

import java.util.Objects;

public class Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void changeX(double x){
        this.x += x;
    }
    public void changeY(double y){
        this.y += y;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Point point &&
                Double.compare(x, point.x) == 0 &&
                Double.compare(y, point.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
