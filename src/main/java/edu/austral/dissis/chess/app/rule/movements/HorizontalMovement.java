package edu.austral.dissis.chess.app.rule.movements;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.ArrayList;
import java.util.List;

public class HorizontalMovement implements MovementRule {

    private final List<Pair<Integer,Integer>> movements;

    public HorizontalMovement() {
        this.movements = new ArrayList<>();
        movements.add(new Pair<Integer, Integer>(0,1));
        movements.add(new Pair<>(0,-1));
    }

    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        if(fromCoord.getValue0().equals(toCoord.getValue0())) {
            for (Pair<Integer, Integer> movement : movements) { //it's horizontal so i stay on the same row
                int y = fromCoord.getValue1() + movement.getValue1();
                while (y >= 0 && y < board.getColumnLimit()) {
                    boolean reached = toCoord.getValue1().equals(y); //reach the toCoord
                    Piece current = board.getPieceFromCoord(new Pair<>(fromCoord.getValue0(), y));
                    if (current != null && !reached) { //there is an obstacule, so is not valid the movement
                        break;
                    }
                    if (reached) { //if reaches to coord
                        if (current == null) { //empty space
                            return true;
                        } else {
                            Piece actual = board.getPieceFromCoord(fromCoord);//the one who moves
                            if (!current.getColor().equals(actual.getColor())) { //there is an enemy
                                return true;
                            } else { //there is a piece with same color
                                return false;
                            }
                        }
                    }
                    y = y + movement.getValue1();
                }
            }//can´t be reached
        }
        return false;
    }
}
