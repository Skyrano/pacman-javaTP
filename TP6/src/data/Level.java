package data;

import java.util.Collection;
import java.util.Properties;

/**
 * The initial state of a level of a pacman game
 *
 * @author Pascale Launay
 *
 * @inv getElements() != null && getSize() >= 0 && getProperties() != null
 */
public interface Level
{

    /**
     * Give the elements of this level (pacman and fruits), at their initial
     * locations
     *
     * @return the initial elements of this level
     */
    Collection<Element> getElements();

    /**
     * Give the number of rows/columns in the game board of this level
     *
     * @return the size of the game board
     */
    int getSize();

    /**
     * Check whether the cell at the given location has a wall
     *
     * @param x the cell column index
     * @param y the cell line index
     *
     * @return true if there is a wall at the given location
     *
     * @pre x >= 0 && x < getSize() && x >= 0 && y < getSize()
     */
    boolean isWall(int x, int y);

    /**
     * Give the fruit in the cell at the given location
     *
     * @param x the cell column index
     * @param y the cell line index
     *
     * @return the fruit at the given location (may be null)
     *
     * @pre x >= 0 && x < getSize() && x >= 0 && y < getSize()
     */
    Fruit getFruit(int x, int y);

    /**
     * Give the level properties
     * 
     * @return the level properties 
     */
    Properties getProperties();
}
