package edu.austral.dissis.chess.app.gamemanegment;

import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.gamemanegment.state.justmove.CheckersJustMove;
import edu.austral.dissis.chess.app.gamemanegment.state.GameState;
import edu.austral.dissis.chess.app.gamemanegment.state.promote.CheckersPromote;
import edu.austral.dissis.chess.app.piecemanegment.PieceController;
import edu.austral.dissis.chess.app.playermanegment.PlayerController;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.special.checker.CheckerCapture;
import edu.austral.dissis.chess.app.rule.movements.special.checker.QueenCheckerCapture;
import edu.austral.dissis.chess.app.tablegame.RuleChecker;
import edu.austral.dissis.chess.app.tablegame.defense.CheckDefense;
import org.javatuples.Pair;

public class CheckersGame implements Game{

    private final RuleChecker ruleChecker;
    private final BoardController boardController;
    private final PlayerController playerController;
    private final PieceController pieceController;
    private final CheckDefense checkDefense;

    public CheckersGame(RuleChecker ruleChecker, BoardController boardController, PlayerController playerController, PieceController pieceController, CheckDefense checkDefense) {
        this.ruleChecker = ruleChecker;
        this.boardController = boardController;
        this.playerController = playerController;
        this.pieceController = pieceController;
        this.checkDefense = checkDefense;
    }

    @Override
    public Game create(RuleChecker ruleChecker, BoardController boardController, PlayerController playerController, PieceController pieceController, CheckDefense checkDefense) {
        return new CheckersGame(ruleChecker, boardController, playerController, pieceController, checkDefense);
    }

    @Override
    public Pair<Game, Result> move(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord) {
        GameState justMove = new CheckersJustMove(); GameState promote = new CheckersPromote();
        if(promote.canUpdate(this, fromCoord, toCoord)){
            return new Pair<>(promote.update(this, fromCoord, toCoord), Result.PROMOTE_MOVE);
        }
        else if(justMove.canUpdate(this, fromCoord, toCoord)){
            Pair<Game, Result> answer = new Pair<>(justMove.update(this, fromCoord, toCoord), Result.JUST_MOVE);
            return answer;
        }
        else{
            return new Pair<>(this, Result.UNREACHABLE_MOVE);
        }
    }

    @Override
    public RuleChecker getRuleChecker() {
        return ruleChecker;
    }

    @Override
    public PlayerController getPlayerController() {
        return playerController;
    }

    @Override
    public PieceController getPieceController() {
        return pieceController;
    }

    @Override
    public CheckDefense getCheckDefense() {
        return checkDefense;
    }

    @Override
    public BoardController getBoardController() {
        return boardController;
    }

}
