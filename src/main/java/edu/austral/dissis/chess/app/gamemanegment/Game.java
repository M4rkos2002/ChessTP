package edu.austral.dissis.chess.app.gamemanegment;

import edu.austral.dissis.chess.app.tablegame.RuleChecker;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.tablegame.defense.CheckDefense;
import edu.austral.dissis.chess.app.piecemanegment.PieceController;
import edu.austral.dissis.chess.app.playermanegment.PlayerController;

public interface Game {

    Pair<Game,Result> move(Pair<Integer, Integer> fromCoord, Pair<Integer, Integer> toCoord);

    RuleChecker getRuleChecker();

    PlayerController getPlayerController();

    PieceController getPieceController();

    CheckDefense getCheckDefense();

    BoardController getBoardController();

    Game create(RuleChecker ruleChecker, BoardController boardController, PlayerController playerController, PieceController pieceController, CheckDefense checkDefense);


}
