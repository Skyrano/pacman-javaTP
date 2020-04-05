package logic;

/**
 * The results of a pacman game
 *
 * @author Alistair Rameau
 */
public class ScoreImpl implements Score {

    /**
     * The number of lives the player has
     */
    private int lives;

    /**
     * The number of points the player has
     */
    private int points;

    /**
     * The number of the level where the player is currently
     */
    private int level;

    /**
     * Constructor of the score
     * Initialization with 3 lives in the first level
     */
    public ScoreImpl() {
        this.lives = 3;
        this.points = 0;
        this.level = 1;
    }

    /**
     * Give the current score information in a unique String
     *
     * @return a String representing the current score
     */
    public String toString() {
        return "Lives: " + lives + "   Points: " + points + "   Level: " + level;
    }


    /**
     * Give the number of remaining lives for the pacman
     *
     * @return the number of lives
     */
    @Override
    public int lives() {
        return this.lives;
    }

    /**
     * Decreases the number of lives the pacman has by one
     */
    public void loseLife() {
        this.lives -= 1;
    }

    /**
     * Give the current number of points, i.e. the sum of the values of the
     * eaten fruits
     *
     * @return the current number of points
     */
    @Override
    public int points() {
        return this.points;
    }

    /**
     * Increases the number of points by a given value
     *
     * @param points_added the number of points earned
     */
    public void addPoints(int points_added) {
        this.points += points_added;
    }

    /**
     * Give the current level number
     *
     * @return the current level
     */
    @Override
    public int level() {
        return this.level;
    }

    /**
     * Increases the value of the level to the next level
     */
    public void nextLevel() {
        this.level += 1;
    }



}
