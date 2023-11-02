package edu.austral.dissis.chess.app.rule.movements;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;

public class isObstaculed implements MovementRule {
    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        return board.getPieceFromCoord(toCoord) != null; //if there is a piece, it's obstaculed
    }
}
