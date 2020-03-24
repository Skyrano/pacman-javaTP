package data;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * An iterator over the initial states of the levels of the pacman game
 *
 * @author Alistair Rameau
 *
 * @inv !hasNextLevel() || nextLevel() != null
 */
public class GameImpl implements Game {

    /**
     * The index of the level where the player is playing
     */
    private int currentLevel;

    /**
     * Instantiation of the game
     */
    public GameImpl() {
        this.currentLevel = 0;
        invariant();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert !hasNextLevel() || nextLevel() != null : "Invariant conditions are violated";
    }


    /**
     *Create a Level object with all the properties and mapping described in a given file
     * @param filename the file to construct the level from
     * @return a Level object with the properties described in the given file
     */
    public char[][] readLevelFile(String filename) {

        char[][] level;
        ArrayList<Element> elementArrayList = new ArrayList<Element>();
        ArrayList<Fruit> fruitArrayList = new ArrayList<Fruit>();
        Properties properties = new Properties();

        String line;
        Scanner sc = new Scanner("level" + Integer.toString(currentLevel));
        sc.nextLine();
        line = sc.nextLine();
        int i = 0;
        while (!line.contains("#")) {
            i+=1;
        }
        level = new char[i][i];
        sc.close();
        sc = new Scanner("level" + Integer.toString(currentLevel));
        sc.nextLine();
        while (i!=0) {
            level[i] = sc.nextLine().toCharArray();
            i-=1;
        }
        sc.nextLine();

        return level;

    }


    /**
     * Give the next level of this game
     *
     * @return the next level of the levels ArrayList
     *
     * @pre hasNextLevel()
     * @post return != null
     */
    @Override
    public Level nextLevel() {
        assert hasNextLevel() : "Precondition violated";
        currentLevel+=1;
        return null;
    }

    /**
     * Check whether another level is available after the current level
     *
     * @return true if a next level is available
     */
    @Override
    public boolean hasNextLevel() {
        File f = new File("resources/level"+Integer.toString(currentLevel+1));
        if (f.exists()) {
            return true;
        }
        else {
            return false;
        }
    }
}
