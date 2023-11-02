package edu.austral.dissis.chess.app.gamemanegment;

public enum Result {
    JUST_MOVE,
    CHECKED,
    WILL_BE_CHECKED_PREDICTION,
    UNREACHABLE_MOVE,
    CHECK_MATE,
    CASTLING_MOVE,
    PROMOTE_MOVE,
    CHECKER_CAPTURE_MOVE
}
