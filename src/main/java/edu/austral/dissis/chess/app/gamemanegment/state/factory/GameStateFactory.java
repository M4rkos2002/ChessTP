package edu.austral.dissis.chess.app.gamemanegment.state.factory;

import edu.austral.dissis.chess.app.gamemanegment.Result;
import edu.austral.dissis.chess.app.gamemanegment.state.GameState;
import org.javatuples.Pair;

import java.util.List;


public interface GameStateFactory {

    List<Pair<Result, GameState>> generateStates();
}
