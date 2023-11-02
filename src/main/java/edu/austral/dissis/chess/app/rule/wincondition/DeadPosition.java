package edu.austral.dissis.chess.app.rule.wincondition;

import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.app.rule.WinCondition;

import java.util.List;

public class DeadPosition implements WinCondition {


    @Override
    public boolean hasWon(Game game) {
        return kingVsKing(game) && kingVsKingAndBishop(game) && kingVsKingAndKnight(game);
    }

    private Boolean kingVsKing(Game game){
        List<Piece> pieces = game.getBoardController().getBoard().getInGamePieces();
        if(pieces.size() == 2){
            return pieces.get(0).getName().equals(PieceName.KING) && pieces.get(1).getName().equals(PieceName.KING);
        }
        return false;
    }

    private Boolean kingVsKingAndBishop(Game game){
        List<Piece> pieces = game.getBoardController().getBoard().getInGamePieces();
        if(pieces.size() == 3){
            int kings = 0;
            int bishop = 0;
            for(Piece p: pieces){
                if(p.getName().equals(PieceName.KING)){kings++;}
                else if(p.getName().equals(PieceName.BOSHOP)){bishop++;}
                else{ break; } //should not reach
            }
            return kings + bishop == 3 && bishop == 1;
        }
        return false;
    }

    private Boolean kingVsKingAndKnight(Game game){
        List<Piece> pieces = game.getBoardController().getBoard().getInGamePieces();
        if(pieces.size() == 3){
            int kings = 0;
            int knights = 0;
            for(Piece p: pieces){
                if(p.getName().equals(PieceName.KING)){kings++;}
                else if(p.getName().equals(PieceName.KNIGHT)){knights++;}
                else{ break; } //should not reach
            }
            return kings + knights == 3 && knights == 1;
        }
        return false;
    }
}
