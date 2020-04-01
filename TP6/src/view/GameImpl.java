package view;


import logic.Cell;

/**
 * The pacman game view. Displays the game current status and allow user
 * interactions in order to move the pacman
 *
 * @author Alistair Rameau
 */
public class GameImpl extends view.Game{

    /**
     * Constructor. Initialize the view with the initial state of the first step
     * of the game
     *
     * @param game the game logic
     *
     * @pre game != null
     */
    public GameImpl(logic.Game game) {
        super(game);
    }


    /**
     * Display the current state of the game
     */
    protected void draw () {

        int caseLength = canvas.WIDTH/game.getSize();
        canvas.clear();
        Cell currentCell;
        Figure currentFig = null;
        for (int x = 0;x < game.getSize();x++) {
            for (int y = 0;y< game.getSize();y++) {
                currentCell = game.getCell(x,y);
                if (currentCell.isWall()){
                    currentFig = new WallFigure(x*caseLength,y*caseLength,caseLength);
                }
                else if (currentCell.hasPacman()) {
                    currentFig = new PacmanFigure(x*caseLength,y*caseLength,caseLength);
                }
                else if (currentCell.getGhost() != null) {
                    String name = currentCell.getGhost();
                    currentFig = new GhostFigure(x*caseLength,y*caseLength, caseLength,name);
                }
                else if (currentCell.getFruit() != null) {
                    String name = currentCell.getFruit();
                    currentFig = new FruitFigure(x*caseLength,y*caseLength,caseLength,name);
                }

                if (currentFig!= null) {
                    canvas.draw(currentFig,currentFig.getColor(),currentFig.getShape());
                }
            }
        }
        canvas.redraw();
    }

    @Override
    public void animate() {
        while (!this.game.isFinished()) {
            draw(); // display the current state of the game
            canvas.wait(game.getWait()); // wait for a while (delay between each step)
            int dx = this.canvas.isRightPressed() ? 1 : this.canvas.isLeftPressed() ? -1 : 0;
            int dy = dx != 0 ? 0 : this.canvas.isDownPressed() ? 1 : this.canvas.isUpPressed() ? -1 : 0;
            play(dx, dy); // compute the next step according to the pacman move (given by the arrow key pressed)
            PacmanFigure.changeDirection(dx,dy);
        }
        draw();
    }
}
