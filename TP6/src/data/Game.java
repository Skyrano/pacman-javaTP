package data;

/**
 * An iterator over the initial states of the levels of the pacman game
 *
 * @author Pascale Launay
 *
 * @inv !hasNextLevel() || nextLevel() != null
 */
public interface Game
{

    /**
     * Give the next level of this game
     *
     * @return true if a next level is available
     *
     * @pre hasNextLevel()
     * @post ret != null
     */
    Level nextLevel();

    /**
     * Check whether another level is available after the current level
     *
     * @return true if a next level is available
     */
    boolean hasNextLevel();
}
