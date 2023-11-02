package edu.austral.dissis.chess.app.rule.movements;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.rule.MovementRule;
import lombok.Getter;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VerticalMovementGeneral implements MovementRule {

    private final List<Pair<Integer,Integer>> movements;

    public VerticalMovementGeneral() {
        this.movements = new ArrayList<>();
        movements.add(new Pair<>(1,0));
        movements.add(new Pair<>(-1, 0));
    }

    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        if(fromCoord.getValue1().equals(toCoord.getValue1())) {
            return verify_movements(movements, fromCoord, toCoord, board);
        }
        return false;
    }

    private boolean verify_movements(List<Pair<Integer,Integer>> movements, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, Board board){
        for (Pair<Integer, Integer> movement : movements) {
            int x = fromCoord.getValue0() + movement.getValue0();
            while (x >= 0 && x < board.getRowLimit()) {
                boolean reached = toCoord.getValue0().equals(x);
                if (reached) {
                    return true;
                }
                x = x + movement.getValue0();
            }
        }
        return false;
    }
}
