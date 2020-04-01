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
     * The constructor
     * @param x the x position of the ghost
     * @param y the y position of the ghost
     * @param caseLength the length of one case in the canvas
     * @param name the name of the ghost
     */
    GhostFigure(int x, int y, int caseLength, String name) {
        super(Color.white, new Arc2D.Double(x+caseLength*0.07,y+caseLength*0.1,caseLength*0.85,caseLength*0.85,320, 260,Arc2D.OPEN));
        this.name = name;
        //new Rectangle2D.Double(x+caseLength*0.17,y+caseLength*0.14,caseLength*0.7,caseLength*0.7)
    }

    /**
     * The getter of the ghost's name
     * @return the String name
     */
    public String getName() {
        return name;
    }
}