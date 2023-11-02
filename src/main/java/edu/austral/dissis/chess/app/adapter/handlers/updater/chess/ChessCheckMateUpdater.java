package edu.austral.dissis.chess.app.adapter.handlers.updater.chess;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.List;

public class ChessCheckMateUpdater implements StateUpdater {
    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentColor) {
        PlayerColor winner = (currentColor.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
        return new GameOver(winner);
    }
}
