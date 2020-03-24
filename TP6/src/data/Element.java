package data;

import java.awt.Point;

/**
 * An element (pacman or ghost) of a level of the pacman game
 *
 * @author Pascale Launay
 *
 * @inv getLocation() != null && getLocation().x >= 0 && getLocation().y >= 0
 */
public interface Element
{

    /**
     * Give the initial location of the element in the game board, s.t. x is the
     * column number and y the row number
     *
     * @return the element location
     */
    Point getLocation();
}
