package edu.austral.dissis.chess.app.gamemanegment.state;

import edu.austral.dissis.chess.app.Coordinate;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.tablegame.defense.WillBeChecked;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import org.javatuples.Pair;

import java.util.List;

public class Castling implements GameState{

    @Override
    public Boolean canUpdate(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        if(fromCoord.getValue0().equals(toCoord.getValue0())) { //same row
            Board board = game.getBoardController().getBoard();
            if(fromCoord.getValue0().equals(board.getRowLimit()-1) || fromCoord.getValue0() == 0){ // row is 0 or max
                Pair<Piece, Pair<Integer, Integer>> king = game.getPieceController().getKing(game.getPlayerController().getCurrentColor(), board);
                Piece rook = board.getPieceFromCoord(toCoord); //No one moved
                if (king.getValue0().getInitialPosition() && rook != null && rook.getName().equals(PieceName.ROOK) && rook.getInitialPosition()){ //pieces can do ir
                    if (rook.getPosition().getValue1() == 0 || rook.getPosition().getValue1() == board.getColumnLimit() - 1){ //esquinas
                        List<Coordinate> coordinates = board.getMatrix().get(king.getValue1().getValue0()); //gets row
                        return isBlocked(game, coordinates, fromCoord, toCoord);
                    }
                }
            }
        }
        return false;
    }

    private Boolean isBlocked(Game game, List<Coordinate> coords, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        if(fromCoord.getValue1() < toCoord.getValue1()){ //left castling
            for(int y = toCoord.getValue1()-1; y > fromCoord.getValue1(); y--){
                Piece actualPiece = coords.get(y).getPiece();
                if(actualPiece != null){
                    return false; //should not be blocked
                }
                Boolean checked = new WillBeChecked().predict(game, fromCoord, new Pair<>(coords.get(y).getRow(), coords.get(y).getColumn()));
                if(checked){ //square should not be checked
                    return false;
                }
            }
            return true;
        }
        else{//right
            for(int y = 1; y < fromCoord.getValue1(); y++){
                Piece actualPiece = coords.get(y).getPiece();
                if(actualPiece != null){
                    return false;
                }
                Boolean checked = new WillBeChecked().predict(game, fromCoord, new Pair<>(coords.get(y).getRow(), coords.get(y).getColumn()));
                if(checked){ //square should not be checked
                    return false;
                }
            }
            return true;
        }
    }

    public Game update(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        Piece rook = game.getBoardController().getBoard().getPieceFromCoord(toCoord);
        Piece king = game.getBoardController().getBoard().getPieceFromCoord(fromCoord);
        if(fromCoord.getValue1()<toCoord.getValue1()){//right castling
            BoardController newBoard = game.getBoardController()
                    .deletePieceFromCoord(fromCoord)
                    .deletePieceFromCoord(toCoord)
                    .addPieceIntoCoord(new Piece(rook.getId(), rook.getRules(), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()+1), rook.getName(), rook.getColor(), false), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()+1))
                    .addPieceIntoCoord(new Piece(king.getId(), king.getRules(), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()+2), king.getName(), king.getColor(), false), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()+2));
            return game.create(game.getRuleChecker(), newBoard, game.getPlayerController().next(), game.getPieceController(), game.getCheckDefense());
        }
        else{//left castling
            BoardController newBoard = game.getBoardController()
                    .deletePieceFromCoord(fromCoord)
                    .deletePieceFromCoord(toCoord)
                    .addPieceIntoCoord(new Piece(rook.getId(), rook.getRules(), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()-1), rook.getName(), rook.getColor(), false), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()-1))
                    .addPieceIntoCoord(new Piece(king.getId(), king.getRules(), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()-2), king.getName(), king.getColor(), false), new Pair<>(fromCoord.getValue0(), fromCoord.getValue1()-2));
            return game.create(game.getRuleChecker(), newBoard, game.getPlayerController().next(), game.getPieceController(), game.getCheckDefense());
        }
    }
}
