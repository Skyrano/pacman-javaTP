package run;

import data.Level;

/**
 * The application entry point
 *
 * @author Pascale Launay
 */
public class Main
{

    /**
     * Launch the application
     *
     * @param args command line arguments (NONE)
     */
    public static void main(String[] args)
    {
        data.GameImpl dataGame = new data.GameImpl();
        dataGame.readLevelFile("level1");
        logic.Game logicGame = new logic.GameMock(dataGame);
        view.Game viewGame = new view.GameMock(logicGame);
        viewGame.animate();
    }
}
