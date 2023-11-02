package edu.austral.dissis.chess.app.boardmanegment;

import lombok.Getter;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.Coordinate;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
public class RegularChessBoard implements Board {

    private final List<List<Coordinate>> matrix;

    @Override
    public Piece getPieceFromCoord(Pair<Integer, Integer> coord) {
        List<Coordinate> row = matrix.get(coord.getValue0());
        for(Coordinate c:row){
            if(c.getColumn().equals(coord.getValue1())){
                return c.getPiece();
            }
        }
        return null;
    }

    public RegularChessBoard(List<List<Coordinate>> matrix){
        this.matrix = matrix;
    }

    public RegularChessBoard(){
        this.matrix = new ArrayList<>();
    }

    @Override
    public Integer getRowLimit() {
        return matrix.size();
    }

    @Override
    public Integer getColumnLimit() {return matrix.get(0).size();}

    @Override
    public List<List<Coordinate>> getMatrix(){return matrix;}

    @Override
    public List<Piece> getInGamePieces() {
        List<Piece> inGamePieces = new ArrayList<>();
        for(List<Coordinate> row: matrix){
            for(Coordinate c: row){
                if(c.getPiece() != null){
                    inGamePieces.add(c.getPiece());
                }
            }
        }
        return Collections.unmodifiableList(inGamePieces);
    }
}