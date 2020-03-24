package data;

/**
 * A fruit in a pacman game at some level
 *
 * @author Pascale Launay
 *
 * @inv getName() != null && !getName().isEmpty() && getValue() > 0
 */
public interface Fruit
{
    /**
     * Give the key that identifies the fruit
     *
     * @return the fruit key
     */
    char getKey();

    /**
     * Give the name of the fruit
     *
     * @return the name of the fruit
     */
    String getName();

    /**
     * Give the value of the fruit, i.e. the number of points gained when a
     * pacman eats this fruit
     *
     * @return the value of the fruit
     */
    int getValue();
}
