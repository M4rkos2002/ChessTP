package edu.austral.dissis.chess.app.tablegame.defense;

import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.app.rule.GameRule;
import edu.austral.dissis.chess.app.rule.gamerule.Check;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegularChessCheckProtect implements CheckDefense{


    @Override
    public List<Pair<Piece, Pair<Integer,Integer>>> whoCanProtectKing(Game game){
        List<Pair<Piece, Pair<Integer,Integer>>> result = new ArrayList<>();
        List<Pair<Piece,Pair<Integer,Integer>>> inGame = game.getPieceController().getPlayerPieces(game.getPlayerController().getCurrentColor(), game.getBoardController().getBoard());
        for(Pair<Piece,Pair<Integer,Integer>> possiblePiece: inGame){
            for(int x = 0; x < game.getBoardController().getBoard().getRowLimit(); x++) {
                for (int y = 0; y < game.getBoardController().getBoard().getColumnLimit(); y++) {
                    Pair<Integer,Integer> toCoord = new Pair<>(x,y);
                    Pair<Piece,Pair<Integer,Integer>> answer = canProtect(game, possiblePiece, toCoord);
                    if(answer != null){
                        result.add(answer); //this piece can change check state
                    }
                }
            }
        }
        return Collections.unmodifiableList(result);
    }

    private Pair<Piece,Pair<Integer,Integer>> canProtect(Game game, Pair<Piece,Pair<Integer,Integer>> possiblePiece, Pair<Integer,Integer> coord){
        if(game.getPieceController().canMove(possiblePiece.getValue0(), possiblePiece.getValue1(), coord, game.getBoardController().getBoard())){
            BoardController newBoard = game.getBoardController().deletePieceFromCoord(possiblePiece.getValue1()).addPieceIntoCoord(possiblePiece.getValue0(),coord);
            Game canonicGame = game.create(game.getRuleChecker(),newBoard, game.getPlayerController(), game.getPieceController(), game.getCheckDefense());
            GameRule check = new Check();
            if(check.validateTurn(canonicGame)){
                return null;
            }
            else{
                return new Pair<>(possiblePiece.getValue0(), coord); //is not checked, so returns the piece and where should go
            }
        }
        return null;
    }

    @Override
    public Boolean canAnyProtect(Game game){
        return !this.whoCanProtectKing(game).isEmpty(); //if is empty, there is no defense
    }


}
