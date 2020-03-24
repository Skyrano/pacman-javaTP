package data;

/**
 * A ghost of a level of the pacman game
 *
 * @author Pascale Launay
 * 
 * @inv getName() != null && !getName().isEmpty()
 */
public interface Ghost extends Element
{

    /**
     * Give the name of this ghost (different ghost should have different names)
     *
     * @return the ghost name
     */
    String getName();
}
