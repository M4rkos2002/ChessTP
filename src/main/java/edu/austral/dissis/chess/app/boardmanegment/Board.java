package edu.austral.dissis.chess.app.boardmanegment;

import org.javatuples.Pair;
import edu.austral.dissis.chess.app.Coordinate;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.List;

public interface Board {

    Piece getPieceFromCoord(Pair<Integer,Integer> coord);

    Integer getRowLimit();

    Integer getColumnLimit();

    List<List<Coordinate>> getMatrix();

    List<Piece> getInGamePieces();
}
