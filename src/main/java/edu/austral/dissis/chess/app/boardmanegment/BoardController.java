package edu.austral.dissis.chess.app.boardmanegment;

import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.gui.ChessPiece;
import org.javatuples.Pair;

import java.util.List;

public interface BoardController {

    BoardController addPieceIntoCoord(Piece piece, Pair<Integer,Integer> coord);

    BoardController deletePieceFromCoord(Pair<Integer,Integer> coord);

    BoardController generateBoardWithPieces(List<Piece> pieces, int rows, int columns);

    Board getBoard();
}