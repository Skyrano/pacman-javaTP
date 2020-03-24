package data;

import java.awt.*;

/**
 * A pacman of a level of the pacman game
 *
 * @author Alistair Rameau
 *
 * @inv getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0
 */
public class PacmanImpl implements Pacman {

    /**
     * The position of the element
     */
    private Point location;

    /**
     *Instantiation of a pacman
     * @param location the position of the pacman
     */
    public PacmanImpl(Point location) {
        this.location = location;
        invariant();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0 : "Invariant conditions are violated";
    }

    /**
     * Give the initial location of the element in the game board, s.t. x is the
     * column number and y the row number
     *
     * @return the element location
     */
    @Override
    public Point getLocation() {
        return location;
    }

}
