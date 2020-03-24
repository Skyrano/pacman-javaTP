package logic;

/**
 * The pacman game logic. Allows to play step by step. At each step, a pacman
 * move can be given, and the new game properties are computed
 *
 * @author Pascale Launay
 *
 * @inv getScore() != null
 * @inv !(!isFinished() && getScore().getLevel() < 0)
 */
public interface Game
{

    /**
     * Give the number of rows/columns in the game board for the current level.
     * Different invocations of this methods should return the same result if
     * getScore().getLevel() return the same result (else, it could be
     * different)
     *
     * @return the current size of the game board
     *
     * @pre !isFinished()
     * @post ret > 0
     */
    int getSize();

    /**
     * Give the minimum wait time between two invocations of the
     * {@link #play(int, int)} method
     *
     * @return the wait time (in ms)
     *
     * @pre !isFinished()
     * @post ret > 0
     */
    int getWait();

    /**
     * Compute the next step. The given pacman move is processed if it is
     * allowed, then the ghosts are moved and the new state of the game is
     * computed accordingly.
     *
     * @param dx the pacman horizontal move (diffence between the current x
     *           location and the expected new one)
     * @param dy the pacman vertical move (diffence between the current y
     *           location and the expected new one)
     *
     * @pre !isFinished()
     * @pre dx >= -1 && dx <= 1 && dy >= -1 && dy <= 1
     * @pre !(dx != 0 && dy != 0)
     */
    void play(int dx, int dy);

    /**
     * Give the cell at the given location
     *
     * @param x the cell column index
     * @param y the cell line index
     *
     * @return the cell at the given location
     *
     * @pre !isFinished()
     * @pre x >= 0 && x < getSize() && x >= 0 && y < getSize()
     * @post ret != null
     */
    Cell getCell(int x, int y);

    /**
     * Check whether the current game is finished, i.e. the last level is
     * finished or the number of lives is exhausted
     *
     * @return true if the game is finished
     */
    boolean isFinished();

    /**
     * Give the score representing the current results of this game.
     *
     * @return the game score
     */
    Score getScore();

}
