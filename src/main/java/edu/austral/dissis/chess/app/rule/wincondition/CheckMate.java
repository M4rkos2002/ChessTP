package edu.austral.dissis.chess.app.rule.wincondition;

import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.rule.GameRule;
import edu.austral.dissis.chess.app.rule.WinCondition;
import edu.austral.dissis.chess.app.rule.gamerule.Check;


public class CheckMate implements WinCondition {

    private final GameRule check = new Check();

    @Override
    public boolean hasWon(Game game) {
        if(check.validateTurn(game)){
            return !game.getCheckDefense().canAnyProtect(game); //if there is any piece, game still playable
        }
        return false;
    }
}
