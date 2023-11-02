package edu.austral.dissis.chess.app.rule.movements.special.checker;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.HorizontalMovement;
import edu.austral.dissis.chess.app.rule.movements.isObstaculed;
import org.javatuples.Pair;

public class QueenHorizontalMovement extends HorizontalMovement {

    private final MovementRule isObstacule = new isObstaculed();
    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board){
        return super.check(fromCoord, toCoord, board) && !isObstacule.check(fromCoord, toCoord, board);
    }
}
