package logic;

import data.Element;
import data.Fruit;
import data.Ghost;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import data.Level;
import data.Pacman;

/**
 * A mock that simulates the Game interface in order to be used instead of a
 * real implementation
 *
 * @author Pascale Launay
 */
public class GameMock implements Game
{
    private boolean[][] eaten;
    /**
     * the initial level state
     */
    private final Level step;

    /**
     * the current pacman location
     */
    private Point pacmanLocation;

    /**
     * the currents ghosts locations
     */
    private final List<Point> ghostLocations;

    /**
     * Constructor
     *
     * @param game the initial game state
     */
    public GameMock(data.Game game)
    {
        this.step = game.nextLevel();
        this.eaten = new boolean[step.getSize()][step.getSize()];
        this.ghostLocations = new ArrayList<>();
        for (Element element : step.getElements()) {
            if (element instanceof Pacman) {
                pacmanLocation = element.getLocation();
                this.eaten[pacmanLocation.x][pacmanLocation.y] = true;
            } else if (element instanceof Ghost) {
                ghostLocations.add(element.getLocation());
            }
        }
    }

    @Override
    public int getSize()
    {
        return step.getSize();
    }

    @Override
    public int getWait()
    {
        return 500;
    }

    @Override
    public void play(int dx, int dy)
    {
        if (dx != 0 || dy != 0) {
            this.pacmanLocation = new Point(pacmanLocation.x + dx, pacmanLocation.y + dy);
            this.eaten[pacmanLocation.x][pacmanLocation.y] = true;
            for (int i = 0; i < ghostLocations.size(); i++) {
                Point location = ghostLocations.get(i);
                ghostLocations.set(i, new Point(location.x + dx, location.y + dy));
            }
        }
    }

    @Override
    public Cell getCell(int x, int y)
    {
        return new Cell()
        {
            @Override
            public boolean isWall()
            {
                return step.isWall(x, y);
            }

            @Override
            public boolean hasPacman()
            {
                return pacmanLocation.x == x && pacmanLocation.y == y;
            }

            @Override
            public String getFruit()
            {
                if (!isWall() && getGhost() == null && !hasPacman() && !eaten[x][y]) {
                    Fruit fruit = step.getFruit(x, y);
                    if (fruit != null) {
                        return fruit.getName();
                    }
                }
                return null;
            }

            @Override
            public String getGhost()
            {
                for (int i = 0; i < ghostLocations.size(); i++) {
                    Point ghostLocation = ghostLocations.get(i);
                    if (ghostLocation.x == x && ghostLocation.y == y) {
                        return "ghost-" + i;
                    }
                }
                return null;
            }
        };
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public Score getScore()
    {
        return new Score()
        {
            int points;

            @Override
            public int lives()
            {
                return 3;
            }

            @Override
            public int points()
            {
                return points;
            }

            @Override
            public int level()
            {
                return 1;
            }

            @Override
            public String toString()
            {
                return "Lives: 3, Points: " + (++points) + ", Level: 1";
            }
        };
    }
}
