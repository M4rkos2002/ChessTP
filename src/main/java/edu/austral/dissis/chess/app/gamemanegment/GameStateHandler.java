package edu.austral.dissis.chess.app.gamemanegment;

import edu.austral.dissis.chess.app.gamemanegment.state.GameState;
import org.javatuples.Pair;

import java.util.List;

public class GameStateHandler {

    private final List<Pair<Result, GameState>> updaters;

    public GameStateHandler(List<Pair<Result, GameState>> updaters){
        this.updaters = updaters;
    }

    public Pair<Game, Result> handleResult(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord){
        for(Pair<Result, GameState> updater: updaters){
            if(updater.getValue1().canUpdate(game, fromCoord, toCoord)){
                return new Pair<>(updater.getValue1().update(game, fromCoord, toCoord), updater.getValue0());
            }
        }
        return new Pair<>(game, Result.UNREACHABLE_MOVE);
    }
}
