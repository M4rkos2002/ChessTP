package edu.austral.dissis.chess.app.gamemanegment.state;

import edu.austral.dissis.chess.app.gamemanegment.Game;
import org.javatuples.Pair;

public interface GameState {

    Game update(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord);
    Boolean canUpdate(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord);
}
