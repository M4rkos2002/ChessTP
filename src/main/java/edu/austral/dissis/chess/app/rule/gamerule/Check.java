package edu.austral.dissis.chess.app.rule.gamerule;

import edu.austral.dissis.chess.app.gamemanegment.RegularChessGame;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.rule.GameRule;
import org.javatuples.Pair;

public class Check implements GameRule {
    @Override
    public boolean validateTurn(Game game){ //returns true when is checked
        Pair<Piece, Pair<Integer,Integer>> king = game.getPieceController()
                .getKing(game.getPlayerController().getCurrentColor(), game.getBoardController().getBoard());
        for(Pair<Piece,Pair<Integer,Integer>> piece:game.getPieceController().getPlayerPieces(game.getPlayerController().getEnemyColor(), game.getBoardController().getBoard())){
            if(game.getPieceController().canMove(piece.getValue0(), piece.getValue1(), king.getValue1(), game.getBoardController().getBoard())){
                    return true;
            }
        }
        return false; //there is no inGame enemy piece who could reach the king
    }
}
