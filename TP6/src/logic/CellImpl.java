package logic;

/**
 * A cell in the board of the current step of the pacman game
 *
 * @author Alistair Rameau
 *
 * @inv !(isWall() && hasPacman())
 * @inv !(isWall() && !getGhost().isEmpty())
 * @inv !(isWall() && getFruit() != null)
 */
public class CellImpl implements Cell {

    /**
     * True state if this cell is a wall
     */
    private boolean iswall;

    /**
     * True state if this cell is the pacman
     */
    private boolean haspacman;

    /**
     *The name of the ghost if this cell is a ghost
     */
    private String ghostName;

    /**
     * The name of the fruit if this cell is a fruit
     */
    private String fruitName;

    /**
     * The constructor of the cell
     * @param iswall true state if this cell is a wall
     * @param haspacman true state if this cell is the pacman
     * @param ghostName the name of the ghost if this cell is a ghost
     * @param fruitName the name of the fruit if this cell is a fruit
     */
    public CellImpl(boolean iswall, boolean haspacman, String ghostName, String fruitName) {
        this.iswall = iswall;
        this.haspacman = haspacman;
        this.ghostName = ghostName;
        this.fruitName = fruitName;
        this.invariant();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert !(isWall() && hasPacman()) && !(isWall() && !getGhost().isEmpty()) && !(isWall() && getFruit() != null) : "Invariant conditions are violated";
    }

    /**
     * Check whether this cell is a wall, i.e. cannot contain a fruit or an
     * element
     *
     * @return true if this cell is a wall
     */
    @Override
    public boolean isWall() {
        return iswall;
    }

    /**
     * Check whether this cell contains a pacman
     *
     * @return true if this cell contains a pacman
     */
    @Override
    public boolean hasPacman() {
        return haspacman;
    }

    /**
     * Check whether this cell contains ghosts and, if any, give the name of one
     * of them
     *
     * @return the name of one of the ghosts in the cell, if any
     */
    @Override
    public String getGhost() {
        return ghostName;
    }

    /**
     * Check whether this cell contains a fruit and returns the name of the
     * fruit if it exists
     *
     * @return the name of the fruit in this cell, if any. Null if this cell has
     *         no fruit
     */
    @Override
    public String getFruit() {
        return fruitName;
    }
}
