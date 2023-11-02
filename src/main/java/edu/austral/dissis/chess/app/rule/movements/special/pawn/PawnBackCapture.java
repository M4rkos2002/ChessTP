package edu.austral.dissis.chess.app.rule.movements.special.pawn;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.rule.movements.DiagonalMovement;

public class PawnBackCapture implements MovementRule {

    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        Piece toPiece = board.getPieceFromCoord(toCoord);
        if(toPiece != null && fromCoord.getValue0() > toCoord.getValue0() && Math.abs(fromCoord.getValue0()-toCoord.getValue0()) == 1){ //just when they are in the oposite row
            MovementRule rule = new DiagonalMovement();
            return rule.check(fromCoord,toCoord,board);
        }
        return false;
    }
}
