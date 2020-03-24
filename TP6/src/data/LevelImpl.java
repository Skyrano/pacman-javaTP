package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;


/**
 * The initial state of a level of a pacman game
 *
 * @author Alistair Rameau
 *
 * @inv getElements() != null && getSize() >= 0 && getProperties() != null
 */
public class LevelImpl implements Level {

    /**
     * The collection containing all the elements of the level
     */
    private ArrayList<Element> elementArrayList;

    /**
     * The list of all the fruits in the level
     */
    private ArrayList<Fruit> fruitArrayList;

    /**
     * The size of the level
     */
    private int size;

    /**
     * The properties of the level
     */
    private Properties properties;

    /**
     * The grid symbolizing the level
     */
    private char[][] level;


    /**
     * Instantiation of a level
     * @param level the map of the level
     * @param elementArrayList the list of all the elements in the level
     * @param fruitArrayList the list of all the fruits in the level
     * @param properties the properties of the level
     */
    public LevelImpl(char[][] level, ArrayList<Element> elementArrayList, ArrayList<Fruit> fruitArrayList, Properties properties) {
        this.level = level;
        this.elementArrayList = elementArrayList;
        this.fruitArrayList = fruitArrayList;
        this.properties = properties;
        this.size = level.length;
        invariant();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert getElements() != null && getSize() >= 0 && getProperties() != null : "Invariant conditions are violated";
    }


    /**
     * Give the elements of this level (pacman and ghost), at their initial
     * locations
     *
     * @return the initial elements of this level
     */
    @Override
    public Collection<Element> getElements() {
        return elementArrayList;
    }

    /**
     * Give the fruit in the cell at the given location
     *
     * @param x the cell column index
     * @param y the cell line index
     *
     * @return the fruit at the given location (may be null)
     *
     * @pre x >= 0 && x < getSize() && x >= 0 && y < getSize()
     */
    @Override
    public Fruit getFruit(int x, int y) {
        assert x >= 0 && x < getSize() && x >= 0 && y < getSize() : "Precondition violated";
        if (!isWall(x,y)) {
            char fruitLetter = level[x][y];
            for (Fruit fruit : fruitArrayList) {
                if(fruit.getKey() == fruitLetter) {
                    return fruit;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * Give the number of rows/columns in the game board of this level
     *
     * @return the size of the game board
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Give the level properties
     *
     * @return the level properties
     */
    @Override
    public Properties getProperties() {
        return properties;
    }

    /**
     * Check whether the cell at the given location has a wall
     *
     * @param x the cell column index
     * @param y the cell line index
     *
     * @return true if there is a wall at the given location
     *
     * @pre x >= 0 && x < getSize() && x >= 0 && y < getSize()
     */
    @Override
    public boolean isWall(int x, int y) {
        assert x >= 0 && x < getSize() && x >= 0 && y < getSize() : "Precondition violated";

        if (level[x][y] == 'w') {
            return true;
        }
        return false;
    }
}
