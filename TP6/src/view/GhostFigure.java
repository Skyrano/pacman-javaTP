package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class GhostFigure extends Figure{

    private String name;

    GhostFigure(int x, int y, String name) {
        super(Color.white, new Rectangle2D.Double(x,y,25,25));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}