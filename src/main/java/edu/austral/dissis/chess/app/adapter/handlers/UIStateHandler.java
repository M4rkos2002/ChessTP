package edu.austral.dissis.chess.app.adapter.handlers;

import edu.austral.dissis.chess.app.gamemanegment.Result;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;

public interface UIStateHandler {

    MoveResult update(List<ChessPiece> pieces, Move move, Result result, PlayerColor currentPlayer);
}
