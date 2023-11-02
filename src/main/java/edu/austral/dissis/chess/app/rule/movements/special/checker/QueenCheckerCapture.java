package edu.austral.dissis.chess.app.rule.movements.special.checker;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.DiagonalMovementGeneral;
import edu.austral.dissis.chess.app.rule.movements.VerticalMovementGeneral;
import edu.austral.dissis.chess.app.rule.movements.isObstaculed;
import org.javatuples.Pair;

public class QueenCheckerCapture implements MovementRule {

    private final MovementRule isObstaculed = new isObstaculed();
    private final MovementRule diagonal = new DiagonalMovementGeneral();

    @Override
    public boolean check(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Board board) {
        Piece fromPiece = board.getPieceFromCoord(fromCoord);
        Piece enemyPiece = this.getEnemy(fromCoord, toCoord, board);
        if(enemyPiece != null && !enemyPiece.getColor().equals(fromPiece.getColor())) { //there is an enemy in the middle!!
            if (diagonal.check(fromCoord, toCoord, board)) { // just can reach
                return !isObstaculed.check(null, toCoord, board); //checks if there is a middle and nothing on next
            }
        }
        return false;
    }

    private Piece getEnemy(Pair<Integer,Integer> fromCoord,Pair<Integer,Integer> toCoord,Board board){
        if (toCoord.getValue0() > fromCoord.getValue0()) { //goes front
            if (toCoord.getValue1() > fromCoord.getValue1()) {return board.getPieceFromCoord(new Pair<>(toCoord.getValue0() - 1, toCoord.getValue1() - 1));} //(1,1)
            else {return board.getPieceFromCoord(new Pair<>(toCoord.getValue0() - 1, toCoord.getValue1() + 1));}//(1,-1)
        }
        else { //goes back
            if (toCoord.getValue1() > fromCoord.getValue1()) {return board.getPieceFromCoord(new Pair<>(toCoord.getValue0() + 1, toCoord.getValue1() - 1));} //(-1,1)
            else {return board.getPieceFromCoord(new Pair<>(toCoord.getValue0() + 1, toCoord.getValue1() + 1));} //(-1,-1)
        }
    }
}
