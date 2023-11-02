package edu.austral.dissis.chess.app.gamemanegment.state.factory;

import edu.austral.dissis.chess.app.gamemanegment.Result;
import edu.austral.dissis.chess.app.gamemanegment.state.Castling;
import edu.austral.dissis.chess.app.gamemanegment.state.GameState;
import edu.austral.dissis.chess.app.gamemanegment.state.justmove.ChessJustMove;
import edu.austral.dissis.chess.app.gamemanegment.state.promote.ChessPromote;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class RegularGameStates implements  GameStateFactory{

    @Override
    public List<Pair<Result, GameState>> generateStates() {
        List<Pair<Result, GameState>> states = new ArrayList<>();
        states.add(new Pair<>(Result.PROMOTE_MOVE, new ChessPromote()));
        states.add(new Pair<>(Result.CASTLING_MOVE, new Castling()));
        states.add(new Pair<>(Result.JUST_MOVE, new ChessJustMove()));
        return states;
    }
}
