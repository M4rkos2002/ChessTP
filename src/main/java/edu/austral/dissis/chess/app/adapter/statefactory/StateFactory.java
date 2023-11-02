package edu.austral.dissis.chess.app.adapter.statefactory;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.app.gamemanegment.Result;
import org.javatuples.Pair;

import java.util.List;

public interface StateFactory {


    List<Pair<Result,StateUpdater>> generateStates();
}
