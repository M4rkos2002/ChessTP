package edu.austral.dissis.chess.app.tablegame.defense;

import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

public class WillBeChecked implements Prediction{


    @Override
    public Boolean predict(Game game, Pair<Integer,Integer> fromCoord,Pair<Integer,Integer> toCoord) {
        Piece actual = game.getBoardController().getBoard().getPieceFromCoord(fromCoord);
        if (game.getPieceController().canMove(actual, fromCoord, toCoord, game.getBoardController().getBoard())) {
            BoardController newBoard = game.getBoardController().deletePieceFromCoord(fromCoord).addPieceIntoCoord(actual, toCoord);
            Game newGame = game.create(game.getRuleChecker(), newBoard, game.getPlayerController(), game.getPieceController(), game.getCheckDefense());
            return newGame.getRuleChecker().checkRules(newGame); //if it's true is beacuse the game will be checked, else not
        }
        return false;
    }
}
