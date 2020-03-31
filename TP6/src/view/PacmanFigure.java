package view;

import java.awt.Color;
import java.awt.geom.Arc2D;

public class PacmanFigure extends Figure{

    PacmanFigure(int x, int y, int caseLength) {
        super(Color.yellow, new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,(0 % 360)+30, 300, Arc2D.PIE));
    }

}
