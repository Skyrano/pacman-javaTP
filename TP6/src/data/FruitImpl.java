package data;

/**
 * A fruit in a pacman game at some level
 *
 * @author Alistair Rameau
 *
 * @inv getName() != null && !getName().isEmpty() && getValue() > 0
 */
public class FruitImpl implements Fruit {

    /**
     * The key character of the fruit
     */
    private char key;

    /**
     * The name of the fruit
     */
    private String name;

    /**
     * The value in point of the fruit when taken
     */
    private int value;

    /**
     * Instantiation of a new fruit
     * @param key the key character of the fruit
     * @param name the name of the fruit
     * @param value the value in point of the fruit when taken
     */
    public FruitImpl(char key, String name, int value) {
        this.key = key;
        this.name = name;
        this.value = value;
        invariant();
    }

    /**
     * Verify the invariant conditions
     */
    private void invariant() {
        assert getName() != null && !getName().isEmpty() && getValue() > 0 : "Invariant conditions are violated";
    }

    /**
     * Give the key that identifies the fruit
     *
     * @return the fruit key
     */
    @Override
    public char getKey() {
        return key;
    }

    /**
     * Give the value of the fruit, i.e. the number of points gained when a
     * pacman eats this fruit
     *
     * @return the value of the fruit
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Give the name of the fruit
     *
     * @return the name of the fruit
     */
    @Override
    public String getName() {
        return name;
    }
}
