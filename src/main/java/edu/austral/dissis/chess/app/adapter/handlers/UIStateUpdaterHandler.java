package edu.austral.dissis.chess.app.adapter.handlers;

import edu.austral.dissis.chess.app.adapter.handlers.updater.StateUpdater;
import edu.austral.dissis.chess.app.gamemanegment.Result;
import edu.austral.dissis.chess.gui.*;
import org.javatuples.Pair;

import java.util.List;

public class UIStateUpdaterHandler implements UIStateHandler {

    private final List<Pair<Result, StateUpdater>> updaters;

    public UIStateUpdaterHandler(List<Pair<Result,StateUpdater>> updaters){
        this.updaters = updaters;
    }

    @Override
    public MoveResult update(List<ChessPiece> pieces, Move move, Result result, PlayerColor currentPlayer) {
        for(Pair<Result, StateUpdater> updater: updaters){
            if(updater.getValue0().equals(result)){ //mathces the state
                return updater.getValue1().update(pieces, move, currentPlayer); //updates
            }
        }
        return null;//not supose to reach
    }
}
