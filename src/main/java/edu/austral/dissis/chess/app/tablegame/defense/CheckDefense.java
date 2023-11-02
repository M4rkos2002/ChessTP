package edu.austral.dissis.chess.app.tablegame.defense;

import edu.austral.dissis.chess.app.gamemanegment.Game;
import org.javatuples.Pair;
import edu.austral.dissis.chess.app.piecemanegment.Piece;

import java.util.List;

public interface CheckDefense {

    List<Pair<Piece, Pair<Integer,Integer>>> whoCanProtectKing(Game game);

    Boolean canAnyProtect(Game game);
}