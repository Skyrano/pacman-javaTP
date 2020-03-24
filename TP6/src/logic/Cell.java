package logic;

/**
 * A cell in the board of the current step of the pacman game
 *
 * @author Pascale Launay
 *
 * @inv !(isWall() && hasPacman())
 * @inv !(isWall() && !getGhost().isEmpty())
 * @inv !(isWall() && getFruit() != null)
 */
public interface Cell
{

    /**
     * Check whether this cell is a wall, i.e. cannot contain a fruit or an
     * element
     *
     * @return true if this cell is a wall
     */
    boolean isWall();

    /**
     * Check whether this cell contains a pacman
     *
     * @return true if this cell contains a pacman
     */
    boolean hasPacman();

    /**
     * Check whether this cell contains ghosts and, if any, give the name of one
     * of them
     *
     * @return the name of one of the ghosts in the cell, if any
     */
    String getGhost();

    /**
     * Check whether this cell contains a fruit and returns the name of the
     * fruit if it exists
     *
     * @return the name of the fruit in this cell, if any. Null if this cell has
     *         no fruit
     */
    String getFruit();
}
