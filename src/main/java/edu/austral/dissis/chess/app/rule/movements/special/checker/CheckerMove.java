package edu.austral.dissis.chess.app.rule.movements.special.checker;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.isObstaculed;
import edu.austral.dissis.chess.app.rule.movements.DiagonalMovementGeneral;
import org.javatuples.Pair;

public class CheckerMove implements MovementRule {

    private final MovementRule diagonal = new DiagonalMovementGeneral();
    private final MovementRule isObstaculed = new isObstaculed();

    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        Piece toPiece = board.getPieceFromCoord(toCoord);
        if(toPiece==null) { //there is no piece
            if (Math.abs(fromCoord.getValue0() - toCoord.getValue0()) == 1) { //just 1 step
                Piece actual = board.getPieceFromCoord(fromCoord);
                if(actual.getColor().equals(Color.WHITE) && fromCoord.getValue0()<toCoord.getValue0()){//just move fornt
                    return diagonal.check(fromCoord, toCoord, board); //delegates check because is a vertical movement
                }
                if(actual.getColor().equals(Color.BLACK) && fromCoord.getValue0()>toCoord.getValue0()){//just move back
                    return diagonal.check(fromCoord, toCoord, board); //delegates check because is a vertical movement
                }
            }
        }
        return false;
    }
}
