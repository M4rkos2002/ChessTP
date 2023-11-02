package edu.austral.dissis.chess.app.gamemanegment;

import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.state.factory.RegularGameStates;
import edu.austral.dissis.chess.app.gamemanegment.state.justmove.ChessJustMove;
import edu.austral.dissis.chess.app.gamemanegment.state.justmove.JustMove;
import edu.austral.dissis.chess.app.gamemanegment.state.promote.ChessPromote;
import edu.austral.dissis.chess.app.tablegame.RuleChecker;
import edu.austral.dissis.chess.app.tablegame.defense.WillBeChecked;
import edu.austral.dissis.chess.app.gamemanegment.state.*;
import lombok.Getter;
import lombok.Setter;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.tablegame.defense.CheckDefense;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceController;
import edu.austral.dissis.chess.app.playermanegment.PlayerController;

import java.util.List;

@Getter
@Setter
public class RegularChessGame implements Game {

    private final RuleChecker ruleChecker;
    private final BoardController boardController;
    private final PlayerController playerController;
    private final PieceController pieceController;
    private final CheckDefense checkDefense;

    public RegularChessGame(RuleChecker ruleChecker, BoardController boardController, PlayerController playerController, PieceController pieceController, CheckDefense checkDefense) {
        this.ruleChecker = ruleChecker;
        this.boardController = boardController;
        this.playerController = playerController;
        this.pieceController = pieceController;
        this.checkDefense = checkDefense;
    }

    public Game create(RuleChecker ruleChecker, BoardController boardController, PlayerController playerController, PieceController pieceController, CheckDefense checkDefense) {
        return new RegularChessGame(ruleChecker, boardController, playerController, pieceController, checkDefense);
    }

    @Override
    public Pair<Game,Result> move(Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord) {
        Piece piece = boardController.getBoard().getPieceFromCoord(fromCoord);
        if(ruleChecker.hasWon(this)){return new Pair<>(this, Result.CHECK_MATE);}
        else if (ruleChecker.checkRules(this)) {//isChecked or there is a rule
            List<Pair<Piece,Pair<Integer,Integer>>> pieces = checkDefense.whoCanProtectKing(this);
            for(Pair<Piece,Pair<Integer,Integer>> p:pieces){
                if(p.getValue0().equals(piece) && p.getValue1().equals(toCoord)){ //isTheCorrectCoord so piece can protect the king
                    GameState justMove = new ChessJustMove();
                    return new Pair<>(justMove.update(this, fromCoord,toCoord), Result.JUST_MOVE);
                }
            }
            return new Pair<>(this, Result.CHECKED);
        }
        else { //not checked, keep going
            return nonCheckMove(fromCoord, toCoord);
        }
    }

    private Pair<Game,Result> nonCheckMove(Pair<Integer, Integer> fromCoord, Pair<Integer,Integer> toCoord){
        WillBeChecked willBeCheck = new WillBeChecked();
        if(willBeCheck.predict(this, fromCoord,toCoord)){ //should not be check the next movement
            return new Pair<>(this,Result.WILL_BE_CHECKED_PREDICTION);
        }
        else{
            GameStateHandler handler = new GameStateHandler(new RegularGameStates().generateStates());
            return handler.handleResult(this, fromCoord, toCoord);
        }
    }
}