package edu.austral.dissis.chess.app.rule.movements;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import lombok.Getter;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiagonalMovementGeneral implements MovementRule {

    private final List<Pair<Integer,Integer>> movements;
    public DiagonalMovementGeneral() {
        this.movements = new ArrayList<>();
        movements.add(new Pair<>(1,1));
        movements.add(new Pair<>(1,-1));
        movements.add(new Pair<>(-1,1));
        movements.add(new Pair<>(-1,-1));
    }

    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        for (Pair<Integer, Integer> movement : movements) {
            int x = fromCoord.getValue0() + movement.getValue0();
            int y = fromCoord.getValue1() + movement.getValue1();
            while ((x >= 0 && x < board.getRowLimit()) && (y >= 0 && y < board.getColumnLimit())) { //while stays on board
                boolean reached = (x == toCoord.getValue0()) && (y == toCoord.getValue1());
                if(reached) { //if I get on toCoord
                    return true;
                }
                x = x + movement.getValue0(); //increment movement
                y = y + movement.getValue1();
            }
        }
        return false;
    }

}
