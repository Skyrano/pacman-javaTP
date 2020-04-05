package view;

import java.awt.Color;
import java.awt.geom.Arc2D;

/**
 * A class symbolizing a ghost derived from Figure
 *
 * @author Alistair Rameau
 */
public class GhostFigure extends Figure{

    /**
     * The name of the ghost
     */
    private String name;

    /**
     * The state of the ghost eating ability in relation with the pacman
     * true -> can be eaten
     * false -> cannot be eaten
     */
    private static boolean eatableState = false;

    /**
     * The constructor
     * @param x the x position of the ghost
     * @param y the y position of the ghost
     * @param caseLength the length of one case in the canvas
     * @param name the name of the ghost
     */
    GhostFigure(int x, int y, int caseLength, String name) {
        super(giveColor(), new Arc2D.Double(x+caseLength*0.07,y+caseLength*0.1,caseLength*0.85,caseLength*0.85,320, 260,Arc2D.OPEN));
        this.name = name;
    }

    /**
     * Give a certain color depending on the current eating ability
     * @return the color corresponding to the current eating ability
     */
    private static Color giveColor() {
        if (eatableState) {
            return Color.darkGray;
        }
        else {
            return Color.white;
        }
    }

    /**
     * The getter of the ghost's name
     * @return the String name
     */
    public String getName() {
        return name;
    }

    /**
     * Inverse the current state of the ghost eating ability
     */
    public static void changeEatable() {
        eatableState = !eatableState;
    }
}