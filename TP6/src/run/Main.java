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
        data.Game dataGame = new data.GameImpl();
        logic.Game logicGame = new logic.GameImpl(dataGame);
        view.Game viewGame = new view.GameImpl(logicGame);
        viewGame.animate();
    }
}
