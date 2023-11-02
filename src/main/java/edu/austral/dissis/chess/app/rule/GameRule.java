package edu.austral.dissis.chess.app.rule;

import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.gamemanegment.RegularChessGame;

public interface GameRule {

    boolean validateTurn(Game game);
}
