package view;

import java.awt.*;

public abstract class Figure {

    protected Color color;
    protected Shape shape;

    Figure(Color color, Shape shape) {
        this.shape = shape;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

}
