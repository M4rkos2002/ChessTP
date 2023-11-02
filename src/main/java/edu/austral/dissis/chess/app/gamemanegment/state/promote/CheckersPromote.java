package edu.austral.dissis.chess.app.gamemanegment.state.promote;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.HorizontalMovement;
import edu.austral.dissis.chess.app.rule.movements.VerticalMovement;
import edu.austral.dissis.chess.app.rule.movements.isObstaculed;
import edu.austral.dissis.chess.app.rule.movements.special.checker.*;
import edu.austral.dissis.chess.app.rule.movements.special.king.KingMovement;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class CheckersPromote extends Promote{

    private final MovementRule isObstaculed = new isObstaculed();
    private final MovementRule rule = new CheckerCapture();

    @Override
    public Boolean canUpdate(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        Board board = game.getBoardController().getBoard();
        Piece piece = board.getPieceFromCoord(fromCoord);
        if(piece.getName().equals(PieceName.CHECKER)) {
            if (game.getPlayerController().getCurrentColor().equals(Color.WHITE) && !isObstaculed.check(null, toCoord, game.getBoardController().getBoard())) {
                return toCoord.getValue0() == 7; // regular is 6th row and cuurent will not be check
            }
            else if(game.getPlayerController().getCurrentColor().equals(Color.BLACK) && !isObstaculed.check(null, toCoord, game.getBoardController().getBoard())){
                return toCoord.getValue0() == 0;
            }
        }
        return false;
    }

    @Override
    public Game update(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new QueenMove()); rules.add(new QueenCheckerCapture());rules.add(new QueenVerticalMovement());rules.add(new QueenHorizontalMovement());rules.add(new QueenDiagonalMovement());
        return updateHelper(game, fromCoord, toCoord, PieceName.QUEENCHECKER, rules);
    }

    @Override
    public Game updateHelper(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, PieceName newName, List<MovementRule> rules){
        Piece actualPiece = game.getBoardController().getBoard().getPieceFromCoord(fromCoord);
        if(this.is_capture(fromCoord, toCoord, game.getBoardController().getBoard())){ //there is a middle enemey
            Pair<Integer,Integer> enemy = this.getEnemy(fromCoord, toCoord, game.getBoardController().getBoard());
            BoardController nBoard = game.getBoardController()
                    .deletePieceFromCoord(enemy)
                    .deletePieceFromCoord(fromCoord)
                    .addPieceIntoCoord(new Piece(actualPiece.getId(), rules, toCoord, newName, actualPiece.getColor(), actualPiece.getInitialPosition()), toCoord);
            return game.create(game.getRuleChecker(),nBoard,game.getPlayerController().next(), game.getPieceController(), game.getCheckDefense());
        }
        BoardController nBoard = game.getBoardController()
                .deletePieceFromCoord(fromCoord)
                .addPieceIntoCoord(new Piece(actualPiece.getId(), rules, toCoord, newName, actualPiece.getColor(), actualPiece.getInitialPosition()), toCoord);
        return game.create(game.getRuleChecker(), nBoard, game.getPlayerController().next(), game.getPieceController(), game.getCheckDefense());
    }

    private Boolean is_capture(Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord, Board board){
        return rule.check(fromCoord, toCoord, board);
    }

    private Pair<Integer,Integer> getEnemy(Pair<Integer,Integer> fromCoord,Pair<Integer,Integer> toCoord,Board board){
        if (toCoord.getValue0() > fromCoord.getValue0()) { //goes front
            if (toCoord.getValue1() > fromCoord.getValue1()) {return new Pair<>(toCoord.getValue0() - 1, toCoord.getValue1() - 1);} //(1,1)
            else {return new Pair<>(toCoord.getValue0() - 1, toCoord.getValue1() + 1);}//(1,-1)
        }
        else { //goes back
            if (toCoord.getValue1() > fromCoord.getValue1()) {return new Pair<>(toCoord.getValue0() + 1, toCoord.getValue1() - 1);} //(-1,1)
            else {return new Pair<>(toCoord.getValue0() + 1, toCoord.getValue1() + 1);} //(-1,-1)
        }
    }

}
