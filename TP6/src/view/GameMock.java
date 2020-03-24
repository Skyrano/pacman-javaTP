package view;

import logic.Cell;

/**
 * A mock that simulates the Game interface in order to be used instead of a
 * real implementation
 *
 * @author Pascale Launay
 */
public class GameMock extends Game
{
    /**
     * Constructor
     *
     * @param game the game logic
     */
    public GameMock(logic.Game game)
    {
        super(game);
    }

    @Override
    protected void draw()
    {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < game.getSize(); j++) {
            for (int i = 0; i < game.getSize(); i++) {
                Cell cell = game.getCell(i, j);
                char c = cell.hasPacman() ? 'P' : cell.getGhost() != null ? 'G' : cell.getFruit() != null ? 'o' : cell.isWall() ? 'x' : ' ';
                builder.append(c);
            }
            builder.append('\n');
        }
        System.out.println(builder.toString() + score.toString());
    }
    
}
