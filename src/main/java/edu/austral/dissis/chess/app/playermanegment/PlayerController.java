package edu.austral.dissis.chess.app.playermanegment;

import edu.austral.dissis.chess.app.Color;

public interface PlayerController {

    PlayerController next();
    Color getCurrentColor();
    Color getEnemyColor();
}
