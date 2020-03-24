package data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * A mock that simulates the Game interface in order to be used instead of a
 * real implementation
 *
 * @author Pascale Launay
 */
public class GameMock implements Game
{

    /**
     * the board size
     */
    private static final int SIZE = 9;

    /**
     * A list of available fruits names
     */
    private static final String[] FRUITS = {"Gomme", "Cerise", "Fraise", "Orange", "Pomme", "Melon", "Galboss", "Cloche", "Clé", "Super"};

    /**
     * the list of elements names
     */
    private static final String[] ELEMENTS = {"pacman", "ghost-1", "ghost-2", "ghost-3", "ghost-4"};

    /**
     * the list of elements locations
     */
    private static final int[] ELEMENTS_LOCATIONS = {4, 4, 3, 3, 3, 5, 5, 3, 5, 5};

    /**
     * the list of properties
     */
    private static final String[] PROPERTIES = {"wait_delay", "500"};

    @Override
    public Level nextLevel()
    {
        return new Level()
        {
            @Override
            public Collection<Element> getElements()
            {
                List<Element> elements = new ArrayList<>();
                for (int i = 0; i < ELEMENTS.length; i++) {
                    String name = ELEMENTS[i];
                    Point point = new Point(ELEMENTS_LOCATIONS[i * 2], ELEMENTS_LOCATIONS[i * 2 + 1]);
                    if ("pacman".equals(name)) {
                        elements.add(new Pacman()
                        {
                            @Override
                            public Point getLocation()
                            {
                                return point;
                            }
                        });
                    } else {
                        elements.add(new Ghost()
                        {
                            @Override
                            public Point getLocation()
                            {
                                return point;
                            }

                            @Override
                            public String getName()
                            {
                                return name;
                            }
                        });
                    }
                }
                return elements;
            }

            @Override
            public int getSize()
            {
                return SIZE;
            }

            @Override
            public boolean isWall(int x, int y)
            {
                return x == 0 || y == 0 || x == SIZE || y == SIZE || (x == 3 && y > 3);
            }

            @Override
            public Fruit getFruit(int x, int y)
            {
                if (isWall(x, y)) {
                    return null;
                }
                return new Fruit()
                {
                    @Override
                    public String getName()
                    {
                        return FRUITS[(x + y) % FRUITS.length];
                    }

                    @Override
                    public int getValue()
                    {
                        return x + y;
                    }

                    @Override
                    public char getKey()
                    {
                        return getName().charAt(0);
                    }
                };
            }

            @Override
            public Properties getProperties()
            {
                Properties properties = new Properties();
                for (int i = 0; i < PROPERTIES.length; i += 2) {
                    properties.put(PROPERTIES[i], PROPERTIES[i + 1]);
                }
                return properties;
            }
        };
    }

    @Override
    public boolean hasNextLevel()
    {
        return true;
    }
}
