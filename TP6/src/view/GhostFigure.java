package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class GhostFigure extends Figure{

    private String name;

    GhostFigure(int x, int y, int caseLength, String name) {
        super(Color.white, new Rectangle2D.Double(x+caseLength*0.17,y+caseLength*0.14,caseLength*0.7,caseLength*0.7));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}