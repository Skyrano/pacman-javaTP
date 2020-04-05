package logic;

/**
 * The results of a pacman game
 *
 * @author Pascale Launay
 */
public interface Score
{

    /**
     * Give the number of remaining lives for the pacman
     *
     * @return the number of lives
     */
    int lives();

    /**
     * Give the current number of points, i.e. the sum of the values of the
     * eaten fruits
     *
     * @return the current number of points
     */
    int points();

    /**
     * Give the current level number
     *
     * @return the current level
     */
    int level();

}
