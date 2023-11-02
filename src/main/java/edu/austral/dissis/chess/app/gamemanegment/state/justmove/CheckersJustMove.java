package edu.austral.dissis.chess.app.gamemanegment.state.justmove;


import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.special.checker.CheckerCapture;
import org.javatuples.Pair;

public class CheckersJustMove extends JustMove {
    @Override
    public Game update(Game game, Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord) {
        Piece piece = game.getBoardController().getBoard().getPieceFromCoord(fromCoord);
        MovementRule capture = new CheckerCapture();
        if (capture.check(fromCoord, toCoord, game.getBoardController().getBoard())) {
            return capture(piece, fromCoord, toCoord, game);
        } else {
            return super.goTo(game, fromCoord, toCoord, piece);
        }
    }

    private Game capture(Piece fromPiece, Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord, Game game) {
        if (toCoord.getValue0() > fromCoord.getValue0()) { //goes front
            if (toCoord.getValue1() > fromCoord.getValue1()) {return swap(new Pair<>(toCoord.getValue0() - 1, toCoord.getValue1() - 1), toCoord, fromPiece, game);} //(1,1)
            else {return swap(new Pair<>(toCoord.getValue0() - 1, toCoord.getValue1() + 1), toCoord, fromPiece, game);}//(1,-1)
        }
        else { //goes back
            if (toCoord.getValue1() > fromCoord.getValue1()) {return swap(new Pair<>(toCoord.getValue0() + 1, toCoord.getValue1() - 1), toCoord, fromPiece, game);} //(-1,1)
            else {return swap(new Pair<>(toCoord.getValue0() + 1, toCoord.getValue1() + 1), toCoord, fromPiece, game);} //(-1,-1)
        }
    }
    private Game swap (Pair < Integer, Integer > enemyPosition, Pair < Integer, Integer > toCoord, Piece piece, Game game){
        Piece newPiece = new Piece(piece.getId(), piece.getRules(), toCoord, piece.getName(), piece.getColor(), false);
        BoardController newBoard = game.getBoardController()
                .deletePieceFromCoord(piece.getPosition())
                .deletePieceFromCoord(enemyPosition)
                .addPieceIntoCoord(newPiece, toCoord);
        return game.create(game.getRuleChecker(), newBoard, game.getPlayerController().next(), game.getPieceController(), null);
    }
}
