package view;

import java.awt.Color;
import java.awt.geom.Arc2D;

public class PacmanFigure extends Figure{

    PacmanFigure(int x, int y) {
        super(Color.yellow, new Arc2D.Double(x,y,30,30,(0 % 360)+30, 300, Arc2D.PIE));
    }

}
