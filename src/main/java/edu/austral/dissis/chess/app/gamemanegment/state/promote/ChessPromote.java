package edu.austral.dissis.chess.app.gamemanegment.state.promote;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.Result;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.DiagonalMovement;
import edu.austral.dissis.chess.app.rule.movements.HorizontalMovement;
import edu.austral.dissis.chess.app.rule.movements.VerticalMovement;
import edu.austral.dissis.chess.app.tablegame.defense.WillBeChecked;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class ChessPromote extends Promote{

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
    public Game update(Game game, Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord) {
        List<MovementRule> queenRules = new ArrayList<>(); queenRules.add(new VerticalMovement()); queenRules.add(new HorizontalMovement()); queenRules.add(new DiagonalMovement());
        return this.updateHelper(game, fromCoord, toCoord, PieceName.QUEEN, queenRules);
    }

    @Override
    public Game updateHelper(Game game, Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, PieceName newName, List<MovementRule> rules) {
        Piece actualPiece = game.getBoardController().getBoard().getPieceFromCoord(fromCoord);
        Piece toPiece = game.getBoardController().getBoard().getPieceFromCoord(toCoord);
        BoardController newBoard = game.getBoardController();
        if(toPiece != null){
            newBoard = newBoard.deletePieceFromCoord(fromCoord)
                    .deletePieceFromCoord(toCoord)
                    .addPieceIntoCoord(new Piece(actualPiece.getId(), rules, toCoord, newName, game.getPlayerController().getCurrentColor(), false), toCoord);
        }
        else{
            newBoard = newBoard.deletePieceFromCoord(fromCoord)//deletes the one who promotes
                    .addPieceIntoCoord(new Piece(actualPiece.getId(), rules, toCoord, newName, game.getPlayerController().getCurrentColor(), false), toCoord);
        }
        return game.create(game.getRuleChecker(),newBoard,game.getPlayerController().next(), game.getPieceController(), game.getCheckDefense());
    }


}
