package edu.austral.dissis.chess.app.rule.movements;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMovement extends DiagonalMovementGeneral {
    public DiagonalMovement() {
        super();
    }


    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        for (Pair<Integer, Integer> movement : super.getMovements()) {
            int x = fromCoord.getValue0() + movement.getValue0();
            int y = fromCoord.getValue1() + movement.getValue1();
            while ((x >= 0 && x < board.getRowLimit()) && (y >= 0 && y < board.getColumnLimit())) { //while stays on board
                boolean reached = (x == toCoord.getValue0()) && (y == toCoord.getValue1());
                Piece current = board.getPieceFromCoord(new Pair<>(x,y));
                if(current != null && !reached){//there is an obstacule and did not reach the end
                    break;
                }
                if (reached) { //if I get on toCoord
                    if(current == null){ //empty space
                        return true;
                    }
                    else {
                        Piece actual = board.getPieceFromCoord(fromCoord);//the piece who moves
                        if(!current.getColor().equals(actual.getColor())) { //there is an enemy
                            return true;
                        }
                        else{ //can reach the position, but there is a piece that has same color
                            return false;
                        }
                    }
                }
                x = x + movement.getValue0(); //increment movement
                y = y + movement.getValue1();
            }
        }
        return false;
    }
}