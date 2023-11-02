package edu.austral.dissis.chess.app.gamemanegment.state.promote;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.state.GameState;
import edu.austral.dissis.chess.app.tablegame.defense.WillBeChecked;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.app.rule.MovementRule;
import org.javatuples.Pair;

import java.util.List;

public abstract class Promote implements GameState {


    @Override
    public Boolean canUpdate(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        Board board = game.getBoardController().getBoard();
        Piece piece = board.getPieceFromCoord(fromCoord);
        if(piece.getName().equals(PieceName.PAWN)) {
            if (game.getPlayerController().getCurrentColor().equals(Color.WHITE)) {
                return (fromCoord.getValue0() == board.getRowLimit() - 2)&& (toCoord.getValue0() == board.getRowLimit() - 1) && !(new WillBeChecked().predict(game, fromCoord, toCoord)); // regular is 6th row and cuurent will not be check
            }
            return (fromCoord.getValue0() == 1) && (toCoord.getValue0() == 0) && !(new WillBeChecked().predict(game, fromCoord, toCoord));
        }
        return false;
    }

    @Override
    public abstract Game update(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord);

    public abstract Game updateHelper(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, PieceName newName, List<MovementRule> rules);

}
