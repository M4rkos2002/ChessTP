package edu.austral.dissis.chess.app.rule.movements.special.pawn;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.rule.movements.VerticalMovement;

public class PawnMove implements MovementRule {
    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        Piece toPiece = board.getPieceFromCoord(toCoord);
        if(toPiece==null && fromCoord.getValue1().equals(toCoord.getValue1())) { //there is no piece and same column
            if (Math.abs(fromCoord.getValue0() - toCoord.getValue0()) == 1) { //just 1 step
                Piece actual = board.getPieceFromCoord(fromCoord);
                if(actual.getColor().equals(Color.WHITE) && fromCoord.getValue0()<toCoord.getValue0()){//just move fornt
                    MovementRule rule = new VerticalMovement();
                    return rule.check(fromCoord, toCoord, board); //delegates check because is a vertical movement
                }
                if(actual.getColor().equals(Color.BLACK) && fromCoord.getValue0()>toCoord.getValue0()){//just move back
                    MovementRule rule = new VerticalMovement();
                    return rule.check(fromCoord, toCoord, board); //delegates check because is a vertical movement
                }
            }
        }
        return false;
    }
}
