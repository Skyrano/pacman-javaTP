package view;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * A class symbolizing the pacman derived from Figure
 *
 * @author Alistair Rameau
 */
public class PacmanFigure extends Figure{

    /**
     * The state of the mouth of the pacman
     * true -> mouth opened
     * false -> mouth closed
     */
    private static boolean mouthState = true;

    /**
     * The direction of the pacman
     * 0 -> turned to the right
     * 1 -> turned upwards
     * 2 -> turned to the left
     * 3 -> turned to the bottom
     */
    private static int direction = 0;

    /**
     * The constructor
     * @param x the x position of the pacman in the canvas
     * @param y the y position of the pacman in the canvas
     * @param caseLength the length of one case in the canvas
     */
    PacmanFigure(int x, int y, int caseLength) {
        super(Color.yellow, giveShape(x,y,caseLength));
        changeMouthState();
    }

    /**
     * Change the direction of the pacman with the given parameters
     * @param dx the x difference in the last movement
     * @param dy the y difference in the last movement
     */
    public static void changeDirection(int dx, int dy) {
        if (dx == 1 && dy == 0) {
            direction = 0;
        }
        else if (dx == 0 && dy == -1) {
            direction = 1;
        }
        else if (dx == -1 && dy == 0) {
            direction = 2;
        }
        else if (dx == 0 && dy == 1) {
            direction = 3;
        }
    }

    /**
     * Give the pacman shape corresponding to the parameters and current state
     * @param x the x position of the pacman
     * @param y the y position of the pacman
     * @param caseLength the length of one case in the canvas
     * @return the current shape of the pacman
     */
    private static Shape giveShape (int x, int y, int caseLength) {
        int extent;
        int type;
        int start = 0;
        if (mouthState) {
            extent = 300;
            type = Arc2D.PIE;
        }
        else {
            extent = 360;
            type = Arc2D.OPEN;
        }

        switch (direction) {
            case 0:
                start = 30;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
            case 1:
                start = 120;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
            case 2:
                start = 210;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
            case 3:
                start = 300;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
        }
        return null;
    }


    /**
     * Inverse the current state of the mouth
     */
    private static void changeMouthState() {
        mouthState = !mouthState;
    }

}
