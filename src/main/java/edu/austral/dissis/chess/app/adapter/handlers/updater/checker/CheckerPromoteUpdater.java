package edu.austral.dissis.chess.app.adapter.handlers.updater.checker;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.gui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CheckerPromoteUpdater implements StateUpdater {
    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, PlayerColor currentPlayer) {
        ChessPiece fromPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getFrom())).findFirst().get();
        Optional<ChessPiece> toPiece = pieces.stream().filter(p -> p.getPosition().equals(this.getEnemy(move.getFrom(), move.getTo()))).findFirst();
        if(toPiece.get().getColor().equals(currentPlayer)){ //not enemy just it self
            pieces = this.promote(pieces, fromPiece, move);
        }
        else{ //it's an enemy
            pieces = this.promote(this.capture(pieces, fromPiece, move.getFrom(), move.getTo()), fromPiece, move);
        }
        PlayerColor nextPlayer = (currentPlayer.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
        return new NewGameState(pieces, nextPlayer);
    }

    private List<ChessPiece> promote(List<ChessPiece> pieces, ChessPiece fromPiece, Move move){
        return pieces.stream()
                .map(p -> (p.getId().equals(fromPiece.getId())) ? new ChessPiece(p.getId(), p.getColor(), move.getTo(), "queen") : p)
                .collect(Collectors.toList());
    }

    private List<ChessPiece> capture(List<ChessPiece> pieces, ChessPiece fromPiece, Position from,Position to){
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
        return pieces.stream().filter(p -> !p.getPosition().equals(enemy)).collect(Collectors.toList()); //delete enemy
    }

    private Position getEnemy(Position from, Position to){
        if (to.getRow() > from.getRow()) { //goes front
            if (to.getColumn() > from.getColumn()) {return new Position(to.getRow()-1, to.getColumn()-1);} //(1,1)
            else {return new Position(to.getRow()-1, to.getColumn()+1);}//(1,-1)
        }
        else { //goes back
            if (to.getColumn() > from.getColumn()) {return new Position(to.getRow()+1, to.getColumn()-1);} //(-1,1)
            else {return new Position(to.getRow()+1, to.getColumn()+1);} //(-1,-1)
        }
    }
}
