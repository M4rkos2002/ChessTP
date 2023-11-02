package edu.austral.dissis.chess.app.tablegame.defense;

import edu.austral.dissis.chess.app.gamemanegment.Game;
import org.javatuples.Pair;

public interface Prediction {

    Boolean predict(Game game, Pair<Integer,Integer> fromCoord, Pair<Integer,Integer> toCoord);
}
