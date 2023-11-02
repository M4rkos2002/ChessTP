package edu.austral.dissis.chess.app.adapter.engine;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.adapter.JavaDataAdapter;
import edu.austral.dissis.chess.app.adapter.UIDataAdapter;
import edu.austral.dissis.chess.app.adapter.handlers.UIStateUpdaterHandler;
import edu.austral.dissis.chess.app.adapter.statefactory.RegularChessStateFactory;
import edu.austral.dissis.chess.app.adapter.validateinput.ChessInputValidator;
import edu.austral.dissis.chess.app.gamemanegment.Result;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.app.factory.Factory;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ChessEngineImpl implements GameEngine {

    private Game game;
    private List<ChessPiece> pieces;
    private PlayerColor currentPlayer;

    public ChessEngineImpl(Factory factory){
        game = factory.generateChessGame();
        pieces = game.getBoardController().getBoard().getInGamePieces().stream().map(UIDataAdapter::adaptPiece).collect(Collectors.toList()); //cast Piece List into ChessPiece list
        currentPlayer = UIDataAdapter.adaptColor(game.getPlayerController().getCurrentColor());
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(new BoardSize(game.getBoardController().getBoard().getColumnLimit(), game.getBoardController().getBoard().getRowLimit()),
                                pieces ,
                                UIDataAdapter.adaptColor(Color.WHITE));
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        MoveResult isValid = new ChessInputValidator().validate(move, pieces, currentPlayer);
        if(isValid !=null){ //if is not valid returns null
            return isValid;
        }
        else {
            Pair<Game, Result> state = game.move(JavaDataAdapter.adaptPosition(move.getFrom()), JavaDataAdapter.adaptPosition(move.getTo()));
            game = state.getValue0();
            MoveResult moveResult = new UIStateUpdaterHandler(new RegularChessStateFactory().generateStates()).update(pieces, move, state.getValue1(),currentPlayer);
            if(moveResult != null){
                this.nextPlayer(currentPlayer, moveResult);
                pieces = game.getBoardController().getBoard().getInGamePieces().stream().map(UIDataAdapter::adaptPiece).collect(Collectors.toList());
                return moveResult;
            }
            return null;
        }
    }

    private void nextPlayer(PlayerColor currentPlayer, MoveResult moveResult){
        if(moveResult.getClass().equals(InvalidMove.class)){ //if is invalid, current player should not change
            this.currentPlayer = currentPlayer;
        }
        else {
            this.currentPlayer = (currentPlayer.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
        }
    }
}
