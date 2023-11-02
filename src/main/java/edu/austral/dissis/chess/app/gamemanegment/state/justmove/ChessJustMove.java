package edu.austral.dissis.chess.app.gamemanegment.state.justmove;

import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import org.javatuples.Pair;

public class ChessJustMove extends JustMove {

    @Override
    public Game update(Game game , Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        Piece piece = game.getBoardController().getBoard().getPieceFromCoord(fromCoord);
        Piece newPiece = new Piece(piece.getId(), piece.getRules(), piece.getPosition(), piece.getName(), piece.getColor(), false);
        Piece toPiece = game.getBoardController().getBoard().getPieceFromCoord(toCoord);
        if(toPiece != null){
            return this.capture(game, fromCoord, toCoord, newPiece);
        }
        else{
            return super.goTo(game, fromCoord, toCoord, newPiece);
        }
    }

    private Game capture(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, Piece piece){
        BoardController boardController = game.getBoardController()
                .deletePieceFromCoord(fromCoord)
                .deletePieceFromCoord(toCoord)
                .addPieceIntoCoord(piece, toCoord);
        return game.create(game.getRuleChecker(), boardController, game.getPlayerController().next(), game.getPieceController(), game.getCheckDefense());
    }
}
