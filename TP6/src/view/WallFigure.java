package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class WallFigure extends Figure{

    WallFigure(int x, int y, int caseLength) {
        super(Color.blue, new Rectangle2D.Double(x,y,caseLength,caseLength));
    }

}
