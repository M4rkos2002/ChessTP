package edu.austral.dissis.chess.app.boardmanegment;

import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.gui.ChessPiece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class RegularChessBoardController implements BoardController{

    private final Board board;
    public RegularChessBoardController(){
        board = new RegularChessBoard();
    }

    @Override
    public BoardController addPieceIntoCoord(Piece piece, Pair<Integer,Integer> coord) {
        List<List<Coordinate>> newMatrix = new ArrayList<>();
        for(List<Coordinate> row: board.getMatrix()){
            if(row.get(0).getRow().equals(coord.getValue0())){
                List<Coordinate> newRow = new ArrayList<>();
                for(Coordinate c: row){
                    if(c.getColumn().equals(coord.getValue1())){
                        newRow.add(new Coordinate(c.getRow(), c.getColumn(), new Piece(piece.getId(), piece.getRules(), coord, piece.getName(), piece.getColor(), piece.getInitialPosition())));
                    }
                    else {
                        newRow.add(c);
                    }
                }
                newMatrix.add(newRow);
            }
            else {
                newMatrix.add(row);
            }
        }
        return new RegularChessBoardController(new RegularChessBoard(Collections.unmodifiableList(newMatrix)));
    }

    @Override
    public BoardController deletePieceFromCoord(Pair<Integer,Integer> coord) {
        List<List<Coordinate>> newMatrix = new ArrayList<>();
        for (List<Coordinate> row : board.getMatrix()) {
            if (row.get(0).getRow().equals(coord.getValue0())) {
                List<Coordinate> newRow = new ArrayList<>();
                for (Coordinate c : row) {
                    if (coord.getValue1().equals(c.getColumn())) {
                        newRow.add(new Coordinate(c.getRow(), c.getColumn(), null)); //out of game
                    }
                    else{
                        newRow.add(c);
                    }
                }
                newMatrix.add(newRow);
            }
            else{
                newMatrix.add(row);
            }
        }
        return new RegularChessBoardController(new RegularChessBoard(Collections.unmodifiableList(newMatrix)));
    }

    @Override
    public BoardController generateBoardWithPieces(List<Piece> pieces, int rows, int columns){
        BoardController actual = this.generateEmptyBoard(rows, columns);
        for(Piece p: pieces){
            actual = actual.addPieceIntoCoord(p , p.getPosition()); //actual is a newBoard
        }
        return actual;
    }

    public BoardController generateEmptyBoard(int rows, int columns) {
        List<List<Coordinate>> matrix = new ArrayList<>();
        for (int x = 0; x < rows; x++) {
            List<Coordinate> row = new ArrayList<>();
            for (int y = 0; y < columns; y++) {
                row.add(new Coordinate(x, y, null)); //adds an empty coordinate
            }
            matrix.add(row); //adds generated row
        }
        return new RegularChessBoardController(new RegularChessBoard(Collections.unmodifiableList(matrix)));
    }

    @Override
    public Board getBoard(){
        return board;
    }
}
