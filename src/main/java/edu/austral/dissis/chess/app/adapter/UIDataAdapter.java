package edu.austral.dissis.chess.app.adapter;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.Position;
import org.javatuples.Pair;

public class UIDataAdapter {

    public static ChessPiece adaptPiece(Piece piece){
        PlayerColor color = UIDataAdapter.adaptColor(piece.getColor());
        Position position = UIDataAdapter.adaptPosition(piece.getPosition());
        String pieceId = UIDataAdapter.adaptPieceName(piece.getName());
        return new ChessPiece(piece.getId(), color, position, pieceId);
    }
    public static PlayerColor adaptColor(Color color){
        if(color.equals(Color.WHITE)){
            return PlayerColor.WHITE;
        }
        else{
            return PlayerColor.BLACK;
        }
    }
    public static Position adaptPosition(Pair<Integer,Integer> position){
        int row = position.getValue0()+1; int column = position.getValue1()+1;
        return new Position(row, column);
    }
    public static String adaptPieceName(PieceName name){
        switch (name) {
            case PAWN:
                return "pawn";
            case KNIGHT:
                return "knight";
            case ROOK:
                return "rook";
            case BOSHOP:
                return "bishop";
            case KING:
                return "king";
            case QUEEN:
                return "queen";
            case ARCHBISHOP:
                return "archbishop";
            case CHANCELLOR:
                return "chancellor";
            case CHECKER:
                return "pawn";
            case QUEENCHECKER:
                return "queen";
        }
        return " ";
    }
}
