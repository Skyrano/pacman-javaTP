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
    public void draw () {

        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < game.getSize(); j++) {
            for (int i = 0; i < game.getSize(); i++) {
                Cell cell = game.getCell(i, j);
                char c = cell.hasPacman() ? 'P' : cell.getGhost() != null ? 'G' : cell.getFruit() != null ? 'o' : cell.isWall() ? 'x' : ' ';
                builder.append(c);
            }
            builder.append('\n');
        }
        System.out.println(builder.toString() + score.toString());



        canvas.clear();
        Cell currentCell;
        Figure currentFig = null;
        for (int x = 0;x < game.getSize();x++) {
            for (int y = 0;y< game.getSize();y++) {
                currentCell = game.getCell(x,y);
                if (currentCell.isWall()){
                    currentFig = new WallFigure(x*34,y*34);
                }
                else if (currentCell.hasPacman()) {
                    currentFig = new PacmanFigure(x*34,y*34);
                }
                else if (currentCell.getGhost() != null) {
                    String name = currentCell.getGhost();
                    currentFig = new GhostFigure(x*34,y*34,name);
                }
                else if (currentCell.getFruit() != null) {
                    String name = currentCell.getFruit();
                    currentFig = new FruitFigure(x*34,y*34,name);
                }

                if (currentFig!= null) {
                    canvas.draw(currentFig,currentFig.getColor(),currentFig.getShape());
                }
            }
        }
        canvas.redraw();
    }




}
