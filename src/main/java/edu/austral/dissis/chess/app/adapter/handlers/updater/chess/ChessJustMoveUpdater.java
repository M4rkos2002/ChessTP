package edu.austral.dissis.chess.app.adapter.handlers.updater.chess;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessJustMoveUpdater implements StateUpdater {

    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer) {
        ChessPiece fromPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getFrom())).findFirst().get();
        Optional<ChessPiece> toPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getTo())).findFirst();
        if(toPiece.isPresent()){
            pieces = this.capture(pieces, move.getTo(), fromPiece, toPiece.get());
        }
        else{ //there is no enemy
            pieces = this.goTo(pieces, move.getTo(), fromPiece);
        }
        PlayerColor nextPlayer = (currentPlayer.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
        return new NewGameState(pieces, nextPlayer);
    }

    private ChessPiece updatePositionOfPiece(Position position, ChessPiece piece){
        return new ChessPiece(piece.getId(),piece.getColor(),position, piece.getPieceId());
    }

    private List<ChessPiece> goTo(List<ChessPiece> pieces,Position to, ChessPiece piece){
        return pieces.stream().map(p -> (p.equals(piece)) ? new ChessPiece(piece.getId(), piece.getColor(), to, piece.getPieceId()) : p).collect(Collectors.toList());
    }

    private List<ChessPiece> capture(List<ChessPiece> pieces, Position to, ChessPiece piece, ChessPiece enemy){
        List<ChessPiece> newPieces = new ArrayList<>();
        for(ChessPiece p:pieces){
            if(p.equals(piece)){
                newPieces.add(new ChessPiece(piece.getId(), piece.getColor(), to, piece.getPieceId()));
            }
            else if(p.equals(enemy)){
                continue;
            }
            else{
                newPieces.add(p);
            }
        }
        return newPieces;
    }
}
