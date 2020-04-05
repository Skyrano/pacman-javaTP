package view;

import java.awt.*;

/**
 * An abtract class used to implement figures drawable into the canvas
 *
 * @author Alistair Rameau
 */
public abstract class Figure {

    /**
     * The color of the figure from the AWT library
     */
    protected Color color;

    /**
     * The shape of the figure from the AWT library
     */
    protected Shape shape;

    /**
     * The constructor
     * @param color the color of the figure
     * @param shape the shape of the figure
     */
    Figure(Color color, Shape shape) {
        this.shape = shape;
        this.color = color;
    }

    /**
     * Getter of the color attribute
     * @return the color of the figure
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter of the shape attribute
     * @return the shape of the figure
     */
    public Shape getShape() {
        return shape;
    }

}
