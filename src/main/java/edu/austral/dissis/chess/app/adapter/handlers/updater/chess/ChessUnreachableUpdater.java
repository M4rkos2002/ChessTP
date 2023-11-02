package edu.austral.dissis.chess.app.adapter.handlers.updater.chess;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.List;

public class ChessUnreachableUpdater implements StateUpdater {
    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentColor) {
        return new InvalidMove("Piece can not reach the position");
    }
}
