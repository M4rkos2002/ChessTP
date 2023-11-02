package edu.austral.dissis.chess.app.adapter.statefactory;


import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.app.adapter.handlers.updater.checker.CheckerCaptureMoveUpdater;
import edu.austral.dissis.chess.app.adapter.handlers.updater.checker.CheckerJustMoveUpdater;
import edu.austral.dissis.chess.app.adapter.handlers.updater.checker.CheckerPromoteUpdater;
import edu.austral.dissis.chess.app.adapter.handlers.updater.chess.ChessJustMoveUpdater;
import edu.austral.dissis.chess.app.adapter.handlers.updater.chess.ChessUnreachableUpdater;
import edu.austral.dissis.chess.app.gamemanegment.Result;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckersStateFactory implements StateFactory {
    @Override
    public List<Pair<Result, StateUpdater>> generateStates() {
        List<Pair<Result,StateUpdater>> updaters = new ArrayList<>();
        updaters.add(new Pair<>(Result.JUST_MOVE,new CheckerJustMoveUpdater()));
        updaters.add(new Pair<>(Result.UNREACHABLE_MOVE, new ChessUnreachableUpdater()));
        updaters.add(new Pair<>(Result.PROMOTE_MOVE, new CheckerPromoteUpdater()));
        return Collections.unmodifiableList(updaters);
    }
}
