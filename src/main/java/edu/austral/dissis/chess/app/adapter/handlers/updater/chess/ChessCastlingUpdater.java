package edu.austral.dissis.chess.app.adapter.handlers.updater.chess;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.List;
import java.util.stream.Collectors;

public class ChessCastlingUpdater implements StateUpdater {

    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer) {
        PlayerColor nextPlayer = (currentPlayer.equals(PlayerColor.WHITE) ? PlayerColor.BLACK:PlayerColor.WHITE);
        if(move.getFrom().getColumn() < move.getTo().getColumn()) {
            return new NewGameState(this.rightCastling(pieces, move, currentPlayer), nextPlayer);
        }
        else{
            return new NewGameState(this.leftCastling(pieces, move, currentPlayer), nextPlayer);
        }
    }

    private List<ChessPiece> rightCastling(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer){
        return pieces.stream().map(p -> {
                    if(p.getColor().equals(currentPlayer)){
                        if (p.getPosition().equals(move.getTo())) {
                            return this.updatePositionOfPiece(new Position(move.getFrom().getRow(), move.getTo().getColumn() - 2), p);
                        }
                        else if(p.getPosition().equals(move.getFrom())){
                            return this.updatePositionOfPiece(new Position(move.getFrom().getRow(), move.getFrom().getColumn()+2), p);
                        }
                        return p;}return p;}).collect(Collectors.toList());
    }

    private List<ChessPiece> leftCastling(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer){
        return pieces.stream().map(p -> {
                    if(p.getColor().equals(currentPlayer)){
                        if (p.getPosition().equals(move.getTo())) {
                            return this.updatePositionOfPiece(new Position(move.getFrom().getRow(), move.getTo().getColumn() + 3), p);
                        }
                        else if(p.getPosition().equals(move.getFrom())){
                            return this.updatePositionOfPiece(new Position(move.getFrom().getRow(), move.getFrom().getColumn()-2), p);
                        }
                        return p;}return p;}).collect(Collectors.toList());
    }

    private ChessPiece updatePositionOfPiece(Position position, ChessPiece piece){
        return new ChessPiece(piece.getId(),piece.getColor(),position, piece.getPieceId());
    }
}
