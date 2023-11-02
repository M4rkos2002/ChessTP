package edu.austral.dissis.chess.app.rule.movements.special.pawn;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.VerticalMovement;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

public class PawnFirstMove implements MovementRule {


    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        Piece actual = board.getPieceFromCoord(fromCoord);
        if(actual.getInitialPosition()) { //should be in initial position
            Piece toPiece = board.getPieceFromCoord(toCoord);
            if((toPiece == null) &&Math.abs(fromCoord.getValue0() - toCoord.getValue0()) == 2 && fromCoord.getValue1().equals(toCoord.getValue1())) { //two steps difference and same column
                MovementRule verticalRule = new VerticalMovement();
                return verticalRule.check(fromCoord, toCoord, board); //could move just when piece is on initial position
            }
        }
        return false; //unreachable
    }
}

