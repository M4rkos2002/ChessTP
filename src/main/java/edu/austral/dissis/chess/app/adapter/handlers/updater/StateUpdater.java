package edu.austral.dissis.chess.app.adapter.handlers.updater;

import edu.austral.dissis.chess.app.gamemanegment.Result;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;

public interface StateUpdater{

    MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentColor);
}
