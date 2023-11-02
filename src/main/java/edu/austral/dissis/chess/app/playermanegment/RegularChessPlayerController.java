package edu.austral.dissis.chess.app.playermanegment;

import edu.austral.dissis.chess.app.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegularChessPlayerController implements PlayerController{

    private final Player playerWihte;
    private final Player playerBlack;
    private final Player currentPlayer;

    public RegularChessPlayerController(){
        this.playerWihte = new Player(Color.WHITE);
        this.playerBlack = new Player(Color.BLACK);
        this.currentPlayer = playerWihte;
    }

    @Override
    public RegularChessPlayerController next(){
        if(currentPlayer.equals(playerWihte)){
            return new RegularChessPlayerController(playerWihte, playerBlack, playerBlack);
        }
        return new RegularChessPlayerController(playerWihte, playerBlack, playerWihte);
    }

    @Override
    public Color getCurrentColor(){
        return this.getCurrentPlayer().getColor();
    }

    @Override
    public Color getEnemyColor(){
        if(currentPlayer.getColor().equals(Color.WHITE)){
            return Color.BLACK;
        }
        return Color.WHITE;
    }
}
