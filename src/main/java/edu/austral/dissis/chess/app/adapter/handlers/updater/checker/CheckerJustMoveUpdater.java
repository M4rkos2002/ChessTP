package edu.austral.dissis.chess.app.adapter.handlers.updater.checker;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.gui.*;
import org.javatuples.Pair;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CheckerJustMoveUpdater implements StateUpdater {

    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer) {
        Optional<ChessPiece> enemy = pieces.stream().filter(p -> p.getPosition().equals(getEnemy(move.getFrom(), move.getTo()))).findFirst();
        if(enemy.isPresent()){
            CheckerCaptureMoveUpdater updater = new CheckerCaptureMoveUpdater();
            return updater.update(pieces, move, currentPlayer);
        }
        ChessPiece fromPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getFrom())).findFirst().get();
        pieces = this.goTo(pieces, move.getTo(), fromPiece);
        PlayerColor nextPlayer = (currentPlayer.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
        return new NewGameState(pieces, nextPlayer);

    }

    private List<ChessPiece> goTo(List<ChessPiece> pieces,Position to, ChessPiece piece){
        return pieces.stream().map(p -> (p.equals(piece)) ? new ChessPiece(piece.getId(), piece.getColor(), to, piece.getPieceId()) : p).collect(Collectors.toList());
    }

    private Position getEnemy(Position fromCoord, Position toCoord){
        if (toCoord.getRow() > fromCoord.getRow()) { //goes front
            if (toCoord.getColumn() > fromCoord.getColumn()) {return new Position(toCoord.getRow() - 1, toCoord.getColumn() - 1);} //(1,1)
            else {return new Position(toCoord.getRow() - 1, toCoord.getColumn() + 1);}//(1,-1)
        }
        else { //goes back
            if (toCoord.getColumn() > fromCoord.getColumn()) {return new Position(toCoord.getRow() + 1, toCoord.getColumn() - 1);} //(-1,1)
            else {return new Position(toCoord.getRow() + 1, toCoord.getColumn() + 1);} //(-1,-1)
        }
    }
}
