package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * A class symbolizing a wall derived from Figure
 *
 * @author Alistair Rameau
 */
public class WallFigure extends Figure{

    /**
     * The constructor
     * @param x the x position of the wall in the canvas
     * @param y the y position of the wall in the canvas
     * @param caseLength the length of one case in the canvas
     */
    WallFigure(int x, int y, int caseLength) {
        super(Color.blue, new Rectangle2D.Double(x,y,caseLength,caseLength));
    }

}
