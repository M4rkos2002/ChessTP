package edu.austral.dissis.chess.app.adapter.validateinput;

import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;

public interface InputValidator {

    MoveResult validate(Move move, List<ChessPiece> pieces, PlayerColor currentPlayer);
}
