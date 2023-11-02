package edu.austral.dissis.chess.app.piecemanegment;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;

import java.util.List;

public interface PieceController {

    Piece createPiece(String id,List<MovementRule> rules, Pair<Integer, Integer> position, PieceName name, Color color, Boolean initialPosition);

    Boolean canMove(Piece piece, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, Board board);

    List<Pair<Piece,Pair<Integer,Integer>>> getPlayerPieces(Color currentColor, Board board);

    Pair<Piece, Pair<Integer,Integer>> getKing(Color currentColor, Board board);

}
