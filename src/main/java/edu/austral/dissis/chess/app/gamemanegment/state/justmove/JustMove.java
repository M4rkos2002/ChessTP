package edu.austral.dissis.chess.app.gamemanegment.state.justmove;

import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.gamemanegment.state.GameState;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import org.javatuples.Pair;

public abstract class JustMove implements GameState {

    @Override
    public abstract Game update(Game game, Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord);

    @Override
    public Boolean canUpdate(Game game, Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord){
        Piece piece = game.getBoardController().getBoard().getPieceFromCoord(fromCoord);
        return game.getPieceController().canMove(piece, fromCoord, toCoord, game.getBoardController().getBoard());
    }


    public Game goTo(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, Piece piece){
        BoardController boardController = game.getBoardController()
                .deletePieceFromCoord(fromCoord)
                .addPieceIntoCoord(piece, toCoord);
        return game.create(game.getRuleChecker(), boardController, game.getPlayerController().next(), game.getPieceController(), game.getCheckDefense());
    }
}
