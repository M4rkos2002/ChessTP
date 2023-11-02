package edu.austral.dissis.chess.app.rule.movements;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.ArrayList;
import java.util.List;

public class VerticalMovement extends VerticalMovementGeneral{


    public VerticalMovement() {
        super();
    }

    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        if(fromCoord.getValue1().equals(toCoord.getValue1())) {
            for (Pair<Integer, Integer> movement : super.getMovements()) {
                int x = fromCoord.getValue0() + movement.getValue0();
                while (x >= 0 && x < board.getRowLimit()) {
                    Piece current = board.getPieceFromCoord(new Pair<>(x, fromCoord.getValue1()));
                    boolean reached = toCoord.getValue0().equals(x);
                    if (current != null && !reached) { //obstaculed
                        break;
                    }
                    if (reached) {
                        Piece actual = board.getPieceFromCoord(fromCoord);
                        return check_to_piece(current, actual);
                    }
                    x = x + movement.getValue0();
                }
            }
        }
        return false;
    }

    private boolean check_to_piece(Piece current, Piece actual) {
        if (current == null) { //empty square
            return true;
        } else {
            if (!current.getColor().equals(actual.getColor())) { //is an enemy
                return true;
            } else { //current has same color, so invalid
                return false;
            }
        }
    }
}