package edu.austral.dissis.chess.app.adapter.handlers.updater.chess;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessPromoteUpdater implements StateUpdater {

    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer) {
        ChessPiece fromPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getFrom())).findFirst().get();
        Optional<ChessPiece> toPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getTo())).findFirst();
        if(toPiece.isPresent()){
            pieces = this.promote(this.capture(pieces, move.getTo(), fromPiece, toPiece.get()), fromPiece, move);
        }
        else{
            pieces = this.promote(pieces, fromPiece, move);
        }
        PlayerColor nextPlayer = (currentPlayer.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
        return new NewGameState(pieces, nextPlayer);
    }

    private List<ChessPiece> promote(List<ChessPiece> pieces, ChessPiece fromPiece, Move move){
        return pieces.stream()
                .map(p -> p.equals(fromPiece) ? new ChessPiece(p.getId(), p.getColor(), move.getTo(), "queen") : p)
                .collect(Collectors.toList());
    }

    private List<ChessPiece> capture(List<ChessPiece> pieces, Position to, ChessPiece piece, ChessPiece enemy){
        List<ChessPiece> newPieces = new ArrayList<>();
        for(ChessPiece p:pieces){
            if(p.getId().equals(piece.getId())){
                newPieces.add(new ChessPiece(piece.getId(), piece.getColor(), to, piece.getPieceId()));
            }
            else if(!p.getId().equals(enemy.getId())){
                    newPieces.add(p);
            }
        }
        return newPieces;
    }
}
