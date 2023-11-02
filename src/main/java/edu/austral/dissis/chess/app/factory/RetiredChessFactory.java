package edu.austral.dissis.chess.app.factory;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.RegularChessBoardController;
import edu.austral.dissis.chess.app.tablegame.RuleChecker;
import edu.austral.dissis.chess.app.tablegame.defense.RegularChessCheckProtect;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.gamemanegment.RegularChessGame;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.app.piecemanegment.RegularChessPieceController;
import edu.austral.dissis.chess.app.playermanegment.RegularChessPlayerController;
import edu.austral.dissis.chess.app.rule.GameRule;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.WinCondition;
import edu.austral.dissis.chess.app.rule.gamerule.Check;
import edu.austral.dissis.chess.app.rule.movements.special.king.KingMovement;
import edu.austral.dissis.chess.app.rule.movements.special.pawn.PawnBackCapture;
import edu.austral.dissis.chess.app.rule.movements.special.pawn.PawnFirstMove;
import edu.austral.dissis.chess.app.rule.movements.special.pawn.PawnFrontCapture;
import edu.austral.dissis.chess.app.rule.movements.special.pawn.PawnMove;
import edu.austral.dissis.chess.app.rule.wincondition.CheckMate;
import edu.austral.dissis.chess.app.rule.wincondition.DeadPosition;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetiredChessFactory implements Factory {


    @Override
    public Game generateChessGame() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(this.generatePawns());
        pieces.addAll(this.generateKings());
        List<GameRule> gameRules = new ArrayList<>(); gameRules.add(new Check());
        List<WinCondition> winConditions = new ArrayList<>(); winConditions.add(new CheckMate()); winConditions.add(new DeadPosition());
        RuleChecker retiredRuleChecker = new RuleChecker(Collections.unmodifiableList(gameRules), Collections.unmodifiableList(winConditions));
        return new RegularChessGame(retiredRuleChecker, new RegularChessBoardController().generateBoardWithPieces(pieces, 8,8), new RegularChessPlayerController(), new RegularChessPieceController(), new RegularChessCheckProtect());
    }

    public List<Piece> generatePawns(){
        List<MovementRule> blackrules = new ArrayList<>();blackrules.add(new PawnFirstMove()); blackrules.add(new PawnMove()); blackrules.add(new PawnFrontCapture());
        List<MovementRule> whiterules = new ArrayList<>();whiterules.add(new PawnFirstMove()); whiterules.add(new PawnMove()); whiterules.add(new PawnBackCapture());
        List<Piece> pawns = new ArrayList<>();
        int id = 1;
        for(int y = 0; y < 8; y++){ //white
            pawns.add(new Piece(String.valueOf(id), blackrules, new Pair<>(1,y), PieceName.PAWN, Color.WHITE, true));
            id++;
        }
        for(int y = 0; y < 8; y++){
            pawns.add(new Piece(String.valueOf(id), whiterules, new Pair<>(6,y), PieceName.PAWN, Color.BLACK, true));
            id++;
        }
        return Collections.unmodifiableList(pawns);
    }

    public List<Piece> generateKings(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new KingMovement());
        List<Piece> kings = new ArrayList<>();
        int id = 17;
        kings.add(new Piece(String.valueOf(id),rules,new Pair<>(0,4),PieceName.KING,Color.WHITE, true));id++;
        kings.add(new Piece(String.valueOf(id),rules,new Pair<>(7,4),PieceName.KING, Color.BLACK, true));
        return Collections.unmodifiableList(kings);
    }
}
