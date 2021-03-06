package data;

import java.awt.*;

/**
 * A ghost of a level of the pacman game
 *
 * @author Alistair Rameau
 *
 * @inv getName() != null && !getName().isEmpty()
 * @inv getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0
 */
public class GhostImpl implements Ghost {

    /**
     * The name of the ghost
     */
    private String name;

    /**
     * The position of the element
     */
    private Point location;

    /**
     * Instantiation of a ghost
     * @param name the name of the ghost
     * @param location the initial location of the ghost
     */
    public GhostImpl(String name, Point location) {
        this.name = name;
        this.location = location;
        invariant();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0 : "Invariant conditions are violated";
        assert getName() != null && !getName().isEmpty() : "Invariant conditions are violated";
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

    /**
     * Give the name of this ghost (different ghost should have different names)
     *
     * @return the ghost name
     */
    @Override
    public String getName() {
        return name;
    }
}
