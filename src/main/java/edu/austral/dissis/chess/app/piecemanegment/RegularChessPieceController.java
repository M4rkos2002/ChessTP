package edu.austral.dissis.chess.app.piecemanegment;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegularChessPieceController implements PieceController{

    @Override
    public Piece createPiece(String id, List<MovementRule> rules, Pair<Integer, Integer> position, PieceName name, Color color, Boolean initialPosition) {
        return new Piece(id,rules, position, name, color, initialPosition);
    }

    @Override
    public Boolean canMove(Piece piece, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, Board board) {
        List<Boolean> results = new ArrayList<>();
        piece.getRules().forEach(rule -> results.add(rule.check(fromCoord,toCoord,board)));
        for(Boolean result:results){
            if(result){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Pair<Piece,Pair<Integer,Integer>>> getPlayerPieces(Color currentColor, Board board) {
        List<Pair<Piece,Pair<Integer,Integer>>> currentPieces = new ArrayList<>();
        for(int x = 0; x < board.getRowLimit(); x++){
            for(int y = 0; y< board.getColumnLimit(); y++){
                Pair<Integer,Integer> coord = new Pair<>(x,y);
                Piece piece = board.getPieceFromCoord(coord);
                if(piece != null && piece.getColor().equals(currentColor)){
                    currentPieces.add(new Pair<>(piece, coord));
                }
            }
        }
        return Collections.unmodifiableList(currentPieces);
    }

    public Pair<Piece,Pair<Integer,Integer>> getKing(Color currentColor, Board board) {
        List<Pair<Piece,Pair<Integer,Integer>>> king = new ArrayList<>();
        this.getPlayerPieces(currentColor, board).stream().filter(p -> p.getValue0().getName().equals(PieceName.KING))
                .findFirst().ifPresent(king::add);
        return king.get(0);
    }
}
