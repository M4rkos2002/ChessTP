package edu.austral.dissis.chess.app.rule.movements.special.king;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.ArrayList;
import java.util.List;

public class KingMovement implements MovementRule {

    private final List<Pair<Integer,Integer>> movements;
    public KingMovement(){
        this.movements = new ArrayList<>();
        movements.add(new Pair<>(1,0));
        movements.add(new Pair<>(0,1));
        movements.add(new Pair<>(1,1));
        movements.add(new Pair<>(-1,1));
        movements.add(new Pair<>(-1,0));
        movements.add(new Pair<>(-1,-1));
        movements.add(new Pair<>(0,-1));
        movements.add(new Pair<>(1,-1));
    }
    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        for (Pair<Integer, Integer> movement : movements) {
            int x = fromCoord.getValue0() + movement.getValue0();
            int y = fromCoord.getValue1() + movement.getValue1();
            if ((x > 0 || x < board.getRowLimit()) && (y > 0 || y < board.getColumnLimit())) {
                if(toCoord.getValue0().equals(x) && toCoord.getValue1().equals(y)){//if is the toCoord
                    Piece destination = board.getPieceFromCoord(toCoord);//destination has a piece
                    if(destination == null){
                        return true;
                    }
                    else{
                        Piece actual = board.getPieceFromCoord(fromCoord); //piece who moves
                        if(!destination.getColor().equals(actual.getColor())){ //king can attack
                            return true;
                        }
                        else{//reached but invalid movement because there is a same color piece
                            return false;
                        }
                    }
                }
            }
        }
        return false; //king can´t move
    }
}