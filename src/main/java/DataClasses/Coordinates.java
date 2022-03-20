package DataClasses;

public class Coordinates {
    private Float x;//NotNull
    private Double y;//NotNull

    public Coordinates(Float x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
