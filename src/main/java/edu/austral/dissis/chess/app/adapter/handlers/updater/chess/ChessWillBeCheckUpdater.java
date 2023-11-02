package edu.austral.dissis.chess.app.adapter.handlers.updater.chess;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.List;

public class ChessWillBeCheckUpdater implements StateUpdater {



    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer) {
        return new InvalidMove(currentPlayer + " player king's will be checked");
    }
}
