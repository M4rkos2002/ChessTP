package edu.austral.dissis.chess.app.adapter.statefactory;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.app.adapter.handlers.updater.chess.*;
import edu.austral.dissis.chess.app.gamemanegment.Result;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegularChessStateFactory implements StateFactory {
    @Override
    public List<Pair<Result, StateUpdater>> generateStates() {
        List<Pair<Result, StateUpdater>> updaters = new ArrayList<>();
        updaters.add(new Pair<>(Result.CASTLING_MOVE, new ChessCastlingUpdater()));
        updaters.add(new Pair<>(Result.PROMOTE_MOVE, new ChessPromoteUpdater()));
        updaters.add(new Pair<>(Result.JUST_MOVE, new ChessJustMoveUpdater()));
        updaters.add(new Pair<>(Result.CHECKED, new ChessCheckUpdater()));
        updaters.add(new Pair<>(Result.WILL_BE_CHECKED_PREDICTION, new ChessWillBeCheckUpdater()));
        updaters.add(new Pair<>(Result.UNREACHABLE_MOVE, new ChessUnreachableUpdater()));
        updaters.add(new Pair<>(Result.CHECK_MATE, new ChessCheckMateUpdater()));
        return Collections.unmodifiableList(updaters);
    }
}
