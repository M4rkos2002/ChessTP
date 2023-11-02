package edu.austral.dissis.chess.app.rule;

import edu.austral.dissis.chess.app.boardmanegment.Board;
import org.javatuples.Pair;

public interface MovementRule {
    boolean check(Pair<Integer,Integer> fromCoord,
                  Pair<Integer,Integer> toCoord,
                  Board board);
}
