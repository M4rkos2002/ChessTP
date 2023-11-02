package edu.austral.dissis.chess.app.adapter.handlers.updater.checker;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.List;
import java.util.stream.Collectors;

public class CheckerCaptureMoveUpdater implements StateUpdater {
    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentColor) {
        ChessPiece fromPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getFrom())).findFirst().get();
        pieces = capture(pieces, fromPiece, move.getFrom(), move.getTo(), currentColor);
        currentColor = (currentColor.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
        return new NewGameState(pieces, currentColor);
    }

    private List<ChessPiece> capture(List<ChessPiece> pieces, ChessPiece fromPiece, Position from,Position to, PlayerColor currentPlayer){
        if (to.getRow() > from.getRow()) { //goes front
            if (to.getColumn() > from.getColumn()) {return swap(to.getRow()-1, to.getColumn()-1, fromPiece, pieces, to);} //(1,1)
            else {return swap(to.getRow()-1, to.getColumn()+1, fromPiece, pieces, to);}//(1,-1)
        }
        else { //goes back
            if (to.getColumn() > from.getColumn()) {return swap(to.getRow()+1, to.getColumn()-1, fromPiece, pieces, to);} //(-1,1)
            else {return swap(to.getRow()+1, to.getColumn()+1, fromPiece, pieces, to);} //(-1,-1)
        }
    }

    private List<ChessPiece> swap(int row, int column, ChessPiece fromPiece, List<ChessPiece> pieces, Position to){
        Position enemy = new Position(row, column);
        pieces = pieces.stream().map(p -> (fromPiece.equals(p)) ? new ChessPiece(fromPiece.getId(), fromPiece.getColor(), to, fromPiece.getPieceId()) : p).collect(Collectors.toList()); //update
        return pieces.stream().filter(p -> !p.getPosition().equals(enemy)).collect(Collectors.toList()); //delete
    }
}
