package data;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * An iterator over the initial states of the levels of the pacman game
 *
 * @author Alistair Rameau
 *
 * @inv !hasNextLevel() || nextLevel() != null
 */
public class GameImpl implements data.Game {

    /**
     * The index of the level where the player is playing
     */
    private int currentLevel;

    /**
     * The path of the level files
     */
    private Path levelPath;

    /**
     * Instantiation of the game
     */
    public GameImpl() {
        this.currentLevel = 0;
        this.levelPath = givePath(currentLevel);
        invariant();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert !hasNextLevel() || nextLevel() != null : "Invariant conditions are violated";
    }


    /**
     * Give a Path object of the level file with the given number
     * @return the path to the file of the level with the given number
     */
    private Path givePath(int number) {
        return Paths.get("resources/level" + Integer.toString(number));
    }


    /**
     *Create a Level object with all the properties and mapping described in the levelPath file
     *
     * @return a Level object with the properties described in the given file
     */
    private Level readLevelFile() {

        char[][] level = null;
        int size = 0;
        ArrayList<Element> elementArrayList = new ArrayList<Element>();
        ArrayList<Fruit> fruitArrayList = new ArrayList<Fruit>();
        Properties properties = new Properties();

        List<String> content;
        try {
            content = Files.readAllLines(levelPath,StandardCharsets.UTF_8);
        }
        catch (IOException except) {
            System.out.println("No file can be found");
            System.out.println(except.toString());
            return null;
        }

        int mode = 0;
        int i = 0;
        while (i < content.size() ) {
            if(content.get(i).contains("#")) {
                i+=1;
                mode+=1;
            }
            if (size != 0 && level == null){
                level = new char[size][size];
            }

            switch (mode) {
                case 1:
                    int j = i;
                    while (!content.get(j).isEmpty()) {
                        size+=1;
                        j+=1;
                    }
                    mode+=1;
                    break;
                case 2:
                    j = 0;
                    while (!content.get(i).isEmpty()) {
                        level[j] = content.get(i).toCharArray();
                        j+=1;
                        i+=1;
                    }
                    i+=1;
                    break;
                case 3:
                    String[] line = content.get(i).split(",");
                    String name = line[0];
                    int xpos = Integer.parseInt(line[1]);
                    int ypos = Integer.parseInt(line[2]);
                    elementArrayList.add(new PacmanImpl(new Point(xpos,ypos)));
                    i+=1;
                    while (!content.get(i).isEmpty()) {
                        line = content.get(i).split(",");
                        name = line[0];
                        xpos = Integer.parseInt(line[1]);
                        ypos = Integer.parseInt(line[2]);
                        elementArrayList.add(new GhostImpl(name,new Point(xpos,ypos)));
                        i+=1;
                    }
                    i+=1;
                    break;
                case 4:
                    char key;
                    int value;
                    while (!content.get(i).isEmpty()) {
                        line = content.get(i).split(",");
                        key = line[0].toCharArray()[0];
                        name = line[1];
                        value = Integer.parseInt(line[2]);
                        fruitArrayList.add(new FruitImpl(key,name,value));
                        i+=1;
                    }
                    i+=1;
                    break;
                case 5:
                    String pkey;
                    String pvalue;
                    while (i < content.size()) {
                        line = content.get(i).split("=");
                        pkey = line[0];
                        pvalue = line[1];
                        properties.setProperty(pkey,pvalue);
                        i+=1;
                    }
                    i+=1;
                    break;
            }
        }

        char[][] reversedLevel = new char[level.length][level.length];
        for(int x = 0;x<level.length;x++) {
            for(int y = 0;y<level.length;y++) {
                reversedLevel[x][y] = level[y][x];
            }
        }

        return new LevelImpl(reversedLevel,elementArrayList,fruitArrayList,properties);
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
        levelPath = givePath(currentLevel);
        return readLevelFile();
    }

    /**
     * Check whether another level is available after the current level
     *
     * @return true if a next level is available
     */
    @Override
    public boolean hasNextLevel() {
        File f = new File(givePath(currentLevel+1).toString());
        if (f.exists()) {
            return true;
        }
        else {
            return false;
        }
    }
}
