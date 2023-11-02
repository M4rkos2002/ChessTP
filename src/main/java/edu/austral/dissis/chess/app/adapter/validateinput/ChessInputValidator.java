package edu.austral.dissis.chess.app.adapter.validateinput;

import edu.austral.dissis.chess.gui.*;

import java.util.List;
import java.util.Optional;

public class ChessInputValidator implements InputValidator{
    @Override
    public MoveResult validate(Move move, List<ChessPiece> pieces, PlayerColor currentPlayer) {
        Optional<ChessPiece> fromPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getFrom())).findFirst();
        if(fromPiece.isEmpty()) {
            return new InvalidMove("There is no piece on " + move.getFrom().toString());
        }
        if(!fromPiece.get().getColor().equals(currentPlayer)){
            return new InvalidMove("This piece does not belong to current player");
        }
        return null;
    }
}
