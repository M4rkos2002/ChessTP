package edu.austral.dissis.chess.app.rule.movements;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.ArrayList;
import java.util.List;

public class Lmovement implements MovementRule {

    private final List<Pair<Integer,Integer>> movements;

    public Lmovement() {
        this.movements = new ArrayList<>();
        movements.add(new Pair<>(1,-2)); //value0 = horizontal, value1= vertical
        movements.add(new Pair<>(-1,-2)); //moved minus two columns and minus 1 row
        movements.add(new Pair<>(-2,-1));
        movements.add(new Pair<>(1,2));
        movements.add(new Pair<>(2,-1));
        movements.add(new Pair<>(2,1));
        movements.add(new Pair<>(-2,1));
        movements.add(new Pair<>(-1,2));
    }


    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        for (Pair<Integer, Integer> movement : movements) {
            Integer row = fromCoord.getValue0() + movement.getValue0();
            Integer column = fromCoord.getValue1() + movement.getValue1();
            if((row < 0 || row >= board.getRowLimit()) || (column < 0 || column >= board.getColumnLimit())){
                continue;
            }
            boolean reached = row.equals(toCoord.getValue0()) && column.equals(toCoord.getValue1());
            if (reached){
                Piece current = board.getPieceFromCoord(new Pair<>(row, column)); //gets to coordinate
                if (current == null) { //If there is no piece, is a valid movement
                    return true;
                }
                else{
                    Piece actual = board.getPieceFromCoord(new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()));
                    if(!current.getColor().equals(actual.getColor())){ //there is an enemy
                        return true;
                    }
                    else{ //can be reached but current has same color
                        return false;
                    }
                }
            }
        }
        return false;
    }
}