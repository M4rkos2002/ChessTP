package edu.austral.dissis.chess.app.adapter.handlers.updater.chess;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.List;

public class ChessCheckUpdater implements StateUpdater {

    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentColor) {
        return new InvalidMove(currentColor.toString() + " player king's is checked");
    }
}
