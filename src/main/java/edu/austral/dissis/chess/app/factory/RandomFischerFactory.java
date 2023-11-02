package edu.austral.dissis.chess.app.factory;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
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
import edu.austral.dissis.chess.app.rule.movements.DiagonalMovement;
import edu.austral.dissis.chess.app.rule.movements.HorizontalMovement;
import edu.austral.dissis.chess.app.rule.movements.Lmovement;
import edu.austral.dissis.chess.app.rule.movements.VerticalMovement;
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
import java.util.Random;

public class RandomFischerFactory implements Factory{
    @Override
    public Game generateChessGame() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(this.generatePawns());
        pieces.addAll(this.generate8thLine());
        pieces.addAll(this.generate1stLine());
        List<GameRule> gameRules = new ArrayList<>(); gameRules.add(new Check());
        List<WinCondition> winConditions = new ArrayList<>(); winConditions.add(new CheckMate()); winConditions.add(new DeadPosition());
        RuleChecker regularRuleChecker = new RuleChecker(Collections.unmodifiableList(gameRules), Collections.unmodifiableList(winConditions));
        BoardController regularBoardController = new RegularChessBoardController();
        return new RegularChessGame(regularRuleChecker, regularBoardController.generateBoardWithPieces(pieces,8,8), new RegularChessPlayerController(), new RegularChessPieceController(), new RegularChessCheckProtect());
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

    public List<Piece> generate8thLine(){
        Random ran = new Random();
        List<Piece> pieces = new ArrayList<>();
        List<MovementRule> rulesRook = new ArrayList<>(); rulesRook.add(new HorizontalMovement()); rulesRook.add(new VerticalMovement());
        List<MovementRule> rulesKnight = new ArrayList<>(); rulesKnight.add(new Lmovement());
        List<MovementRule> rulesBishop = new ArrayList<>(); rulesBishop.add(new DiagonalMovement());
        List<MovementRule> rulesKing = new ArrayList<>(); rulesKing.add(new KingMovement());
        List<MovementRule> rulesQueen = new ArrayList<>();rulesQueen.add(new HorizontalMovement()); rulesQueen.add(new VerticalMovement()); rulesQueen.add(new DiagonalMovement());
        List<Integer> values = new ArrayList<>();
        int id = 17;
        while(pieces.size() != 2){
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesRook, new Pair<>(7, value), PieceName.ROOK, Color.BLACK, true));
                values.add(value);
                id++;
            }
        }
        while(pieces.size() !=4 ) {
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesKnight, new Pair<>(7, value), PieceName.KNIGHT, Color.BLACK, true));
                values.add(value);
                id++;
            }

        }
        while(pieces.size() != 6){
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesBishop, new Pair<>(7, value), PieceName.BOSHOP, Color.BLACK, true));
                values.add(value);
                id++;
            }
        }
        while(pieces.size() != 7){
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesKing, new Pair<>(7, value), PieceName.KING, Color.BLACK, true));
                values.add(value);
                id++;
            }
        }
        while(pieces.size()!=8){
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesQueen, new Pair<>(7, value), PieceName.QUEEN, Color.BLACK, true));
                values.add(value);
            }
        }
        return Collections.unmodifiableList(pieces);
    }

    public List<Piece> generate1stLine(){
        Random ran = new Random();
        List<Piece> pieces = new ArrayList<>();
        List<MovementRule> rulesRook = new ArrayList<>(); rulesRook.add(new HorizontalMovement()); rulesRook.add(new VerticalMovement());
        List<MovementRule> rulesKnight = new ArrayList<>(); rulesKnight.add(new Lmovement());
        List<MovementRule> rulesBishop = new ArrayList<>(); rulesBishop.add(new DiagonalMovement());
        List<MovementRule> rulesKing = new ArrayList<>(); rulesKing.add(new KingMovement());
        List<MovementRule> rulesQueen = new ArrayList<>();rulesQueen.add(new HorizontalMovement()); rulesQueen.add(new VerticalMovement()); rulesQueen.add(new DiagonalMovement());
        List<Integer> values = new ArrayList<>();
        int id = 25;
        while(pieces.size() != 2){
            int value = ran.nextInt(8);
            if(!values.contains(value)){
                pieces.add(new Piece(String.valueOf(id), rulesRook, new Pair<>(0, value), PieceName.ROOK, Color.WHITE, true));
                values.add(value);
                id++;
            }
        }
        while(pieces.size() !=4 ) {
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesKnight, new Pair<>(0, value), PieceName.KNIGHT, Color.WHITE, true));
                values.add(value);
                id++;
            }

        }
        while(pieces.size() != 6){
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesBishop, new Pair<>(0, value), PieceName.BOSHOP, Color.WHITE, true));
                values.add(value);
                id++;
            }

        }
        while(pieces.size() != 7){
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesKing, new Pair<>(0, value), PieceName.KING, Color.WHITE, true));
                values.add(value);
                id++;
            }
        }
        while(pieces.size()!=8){
            int value = ran.nextInt(8);
            if(!values.contains(value)) {
                pieces.add(new Piece(String.valueOf(id), rulesQueen, new Pair<>(0, value), PieceName.QUEEN, Color.WHITE, true));
                values.add(value);
            }
        }
        return Collections.unmodifiableList(pieces);
    }
}
