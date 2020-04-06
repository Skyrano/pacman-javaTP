package logic;

import data.*;
import view.GhostFigure;
import view.PacmanFigure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The pacman game logic. Allows to play step by step. At each step, a pacman
 * move can be given, and the new game properties are computed
 *
 * @author Alistair Rameau
 *
 * @inv getScore() != null
 * @inv !(!isFinished() && getScore().level() < 0)
 */
public class GameImpl implements Game {


    /**
     * The array representing the eaten elements
     */
    private boolean[][] eaten;

    /**
     * The initial level state
     */
    private Level level;

    /**
     * The current pacman location
     */
    private Point pacmanLocation;

    /**
     * The currents ghosts locations
     */
    private List<Point> ghostLocations;

    /**
     * The currents ghosts names
     */
    private List<String> ghostNames;

    /**
     * The currents ghosts names
     */
    private List<Boolean> ghostEaten;

    /**
     * The score of the player
     */
    private ScoreImpl score;

    /**
     * The super state of the pacman
     */
    private boolean superState;

    /**
     * The timer to schedule the changes in the super state
     */
    private Timer superTimer;

    /**
     * The game data we want to use
     */
    public data.Game game;

    /**
     * Tell if the game is finished with the victory of the player
     */
    private boolean lastLevelFinished;

    /**
     * A grid symbolizing the level to help generate the path for the ghost
     */
    private char[][] grid;


    /**
     * Constructor
     *
     * @param game the initial game state
     */
    public GameImpl(data.Game game)
    {
        this.game = game;
        this.lastLevelFinished = false;
        this.score = new ScoreImpl();
        this.nextLevel();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert getScore() != null && !(!isFinished() && getScore().level() < 0) : "Invariant conditions are violated";
    }


    /**
     * Extract all the data from the next level into the logic class
     */
    private void nextLevel(){
        this.level = game.nextLevel();
        this.score.nextLevel();
        this.eaten = new boolean[level.getSize()][level.getSize()];
        this.ghostLocations = new ArrayList<>();
        this.ghostNames = new ArrayList<>();
        this.ghostEaten = new ArrayList<>();
        for (Element element : level.getElements()) {
            if (element instanceof Pacman) {
                this.pacmanLocation = element.getLocation();
                this.eaten[pacmanLocation.x][pacmanLocation.y] = true;
            } else if (element instanceof Ghost) {
                ghostLocations.add(element.getLocation());
                ghostNames.add(((Ghost) element).getName());
                ghostEaten.add(false);
            }
        }
        this.generateGrid();
        this.superState = false;
        this.superTimer = new Timer();
        this.invariant();
    }

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
    @Override
    public int getSize() {
        assert !isFinished() : "Precondition violated";
        int ret = this.level.getSize();
        assert ret > 0 : "Postcondition violated";
        return ret;
    }

    /**
     * Give the minimum wait time between two invocations of the
     * {@link #play(int, int)} method
     *
     * @return the wait time (in ms)
     *
     * @pre !isFinished()
     * @post ret > 0
     */
    @Override
    public int getWait() {
        assert !isFinished() : "Precondition violated";
        int ret =  Integer.parseInt(this.level.getProperties().getProperty("wait_duration"));
        assert ret > 0 : "Postcondition violated";
        return ret;
    }


    /**
     * Give the duration of the Super item bonus
     *
     * @return the bonus duration (in ms)
     *
     * @pre !isFinished()
     * @post ret > 0
     */
    public int getPowerDuration() {
        assert !isFinished() : "Precondition violated";
        int ret = Integer.parseInt(this.level.getProperties().getProperty("power_duration"));
        assert ret > 0 : "Postcondition violated";
        return ret;
    }



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
    @Override
    public Cell getCell(int x, int y) {
        assert !isFinished() && x >= 0 && x < getSize() && x >= 0 && y < getSize() : "Preconditions violated";
        boolean iswall = level.isWall(x,y);

        boolean haspacman = (pacmanLocation.x == x && pacmanLocation.y == y);

        String ghostName =  null;
        int index = ghostLocations.indexOf(new Point(x,y));
        if (index != -1 && ghostEaten.get(index) == false) {
            ghostName = ghostNames.get(index);
        }

        String fruitName = null;
        Fruit fruit = level.getFruit(x,y);
        if (!eaten[x][y] && fruit!=null) {
            fruitName = fruit.getName();
        }

        return new CellImpl(iswall,haspacman,ghostName,fruitName);
    }

    /**
     * Check whether the current game is finished, i.e. the last level is
     * finished or the number of lives is exhausted
     *
     * @return true if the game is finished
     */
    @Override
    public boolean isFinished() {
        return score.lives() == 0 || lastLevelFinished;
    }

    /**
     * Give the score representing the current results of this game.
     *
     * @return the game score
     */
    @Override
    public Score getScore() {
        return this.score;
    }


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
    @Override
    public void play(int dx, int dy) {
        assert !isFinished() && dx >= -1 && dx <= 1 && dy >= -1 && dy <= 1 && !(dx != 0 && dy != 0) : "Precondition violated";
        PacmanFigure.changeDirection(dx,dy);
        this.movePacman(dx,dy);
        this.moveGhosts();
        this.eatGhost();
        this.eatFruit();
        this.levelFinished();
        /*
        for (int i = 0; i < ghostLocations.size(); i++) {
            Point location = ghostLocations.get(i);
            ghostLocations.set(i, new Point(location.x + dx, location.y + dy));
        }*/
    }


    /**
     * Make the pacman eat the fruit at the current pacman position
     */
    private void eatFruit() {
        int x = pacmanLocation.x;
        int y = pacmanLocation.y;
        Fruit fruit = level.getFruit(x,y);
        if (!this.eaten[x][y] && fruit != null) {
            this.eaten[x][y] = true;
            this.score.addPoints(fruit.getValue());
            if (fruit.getKey() == 'S' && !superState && ghostEaten.indexOf(false) != -1) {
                superState = true;
                GhostFigure.changeEatable();
                superTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("test");
                        superState = false;
                        GhostFigure.changeEatable();
                    }
                }, this.getPowerDuration());
            }
        }
    }


    /**
     * Make the pacman eat or be eaten by the ghost at the pacman position
     */
    private void eatGhost() {
        for (int i = 0; i < ghostLocations.size(); i++) {
            Point location = ghostLocations.get(i);
            if (!ghostEaten.get(i) && location.distance(pacmanLocation) == 0) {
                if (superState) {
                    ghostEaten.set(i,true);
                    score.addPoints(200);
                }
                else {
                    score.loseLife();
                }
            }
        }
    }

    /**
     * Verify if the level is finished, i.e. if all the fruits are eaten, and change to next level or finish the game
     */
    private void levelFinished() {
        boolean finished = true;
        for (int i = 0; i < level.getSize(); i++) {
            for (int j = 0; j < level.getSize(); j++) {
                if (getCell(i,j).getFruit() != null && !this.eaten[i][j]) {
                    finished = false;
                    break;
                }
            }
        }
        if (finished && game.hasNextLevel()) {
            this.nextLevel();
        }
        else if (finished) {
            this.lastLevelFinished = true;
        }
    }

    /**
     * Move the pacman to the desired position if there is no wall
     * @param dx the x movement wanted
     * @param dy the y movement wanted
     */
    private void movePacman(int dx, int dy) {
        int nextX = pacmanLocation.x + dx;
        int nextY = pacmanLocation.y + dy;
        if (!(getCell(nextX,nextY).isWall())) {
            this.pacmanLocation = new Point(nextX,nextY);
        }
    }

    /**
     * Move all the ghosts with a defined pattern for each one
     */
    private void moveGhosts() {
        int[] dx = new int[ghostLocations.size()];
        int[] dy = new int[ghostLocations.size()];

        Pathfinding.Dijkstra(this.grid,pacmanLocation,'w');

        for (int i = 0; i < ghostLocations.size(); i++) {
            if(ghostNames.get(i).equals("ghost-1")) {
                System.out.println("On cherche le point suivant");
                ghostLocations.set(i,Pathfinding.closerPoint(ghostLocations.get(i)));
            }
        }
    }

    /**
     * Generate a raw grid of the level, i.e. with only the walls and pathway, to help finding the paths of the ghosts
     * @return the grid corresponding to the current level
     */
    private void generateGrid() {
        this.grid = new char[level.getSize()][level.getSize()];
        for (int i = 0; i < level.getSize(); i++) {
            for (int j = 0; j < level.getSize(); j++) {
                if(getCell(i,j).isWall()) {
                    this.grid[i][j] = 'w';
                }
                else {
                    this.grid[i][j] = 'x';
                }
            }
        }
    }

}
