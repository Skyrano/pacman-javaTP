package view;

import logic.Score;

/**
 * The pacman game view. Displays the game current status and allow user
 * interactions in order to move the pacman
 *
 * @author Pascale Launay
 */
public abstract class Game
{

    /**
     * canvas used to display the game figures
     */
    protected final Canvas canvas;

    /**
     * the game logic
     */
    protected final logic.Game game;

    /**
     * the current game score
     */
    protected final Score score;

    /**
     * Constructor. Initialize the view with the initial state of the first step
     * of the game
     *
     * @param game the game logic
     * 
     * @pre game != null 
     */
    public Game(logic.Game game)
    {
        this.game = game;
        this.canvas = Canvas.getCanvas();
        this.score = game.getScore();
    }

    /**
     * Run the game steps lauched periodically, with a delay between each step.
     * At each step, move the pacman according to the arrow key currently
     * pressed, and compute and display the game state accordingly
     */
    public void animate()
    {
        while (!this.game.isFinished()) {
            draw(); // display the current state of the game
            canvas.wait(game.getWait()); // wait for a while (delay between each step)
            int dx = this.canvas.isRightPressed() ? 1 : this.canvas.isLeftPressed() ? -1 : 0;
            int dy = dx != 0 ? 0 : this.canvas.isDownPressed() ? 1 : this.canvas.isUpPressed() ? -1 : 0;
            play(dx, dy); // compute the next step according to the pacman move (given by the arrow key pressed)
        }
        draw();
    }

    /**
     * Move the pacman according to the given move, move the ghosts and compute
     * the new state of the game
     *
     * @param dx the pacman horizontal move (diffence between the current x
     *           location and the new one)
     * @param dy the pacman vertical move (diffence between the current y
     *           location and the new one)
     *
     * @pre dx >= -1 && dx <= 1 && dy >= -1 && dy <= 1
     * @pre !(dx != 0 && dy != 0)
     */
    protected void play(int dx, int dy)
    {
        this.game.play(dx, dy);
    }

    /**
     * Display the current state of the game
     */
    protected abstract void draw();
}
