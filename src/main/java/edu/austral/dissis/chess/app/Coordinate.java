package edu.austral.dissis.chess.app;

import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.gui.ChessPiece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Coordinate {

    private final Integer row; //x
    private final Integer column; //y
    private final Piece piece;
}
