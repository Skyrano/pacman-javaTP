package view;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class GhostFigure extends Figure{

    private String name;

    GhostFigure(int x, int y, int caseLength, String name) {
        super(Color.white, new Arc2D.Double(x+caseLength*0.07,y+caseLength*0.1,caseLength*0.85,caseLength*0.85,320, 260,Arc2D.OPEN));
        this.name = name;
        //new Rectangle2D.Double(x+caseLength*0.17,y+caseLength*0.14,caseLength*0.7,caseLength*0.7)
    }

    public String getName() {
        return name;
    }
}