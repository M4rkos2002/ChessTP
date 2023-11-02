package edu.austral.dissis.chess.app.factory;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.Board;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.boardmanegment.RegularChessBoardController;
import edu.austral.dissis.chess.app.gamemanegment.RegularChessGame;
import edu.austral.dissis.chess.app.piecemanegment.RegularChessPieceController;
import edu.austral.dissis.chess.app.playermanegment.RegularChessPlayerController;
import edu.austral.dissis.chess.app.tablegame.RuleChecker;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
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
import edu.austral.dissis.chess.app.tablegame.defense.RegularChessCheckProtect;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CapablancaFactory implements Factory{
    @Override
    public Game generateChessGame() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(this.generatePawns());
        pieces.addAll(this.generateRooks());
        pieces.addAll(this.generateKnights());
        pieces.addAll(this.generateArchs());
        pieces.addAll(this.generateBishops());
        pieces.addAll(this.generateQueens());
        pieces.addAll(this.generateKings());
        List<GameRule> gameRules = new ArrayList<>(); gameRules.add(new Check());
        List<WinCondition> winConditions = new ArrayList<>(); winConditions.add(new CheckMate());
        RuleChecker capaBlancaRuleChecker = new RuleChecker(Collections.unmodifiableList(gameRules), Collections.unmodifiableList(winConditions));
        BoardController boardController = new RegularChessBoardController().generateBoardWithPieces(pieces, 8,10);
        return new RegularChessGame(capaBlancaRuleChecker, boardController, new RegularChessPlayerController(), new RegularChessPieceController(), new RegularChessCheckProtect());
    }

    public List<Piece> generatePawns(){
        List<MovementRule> blackrules = new ArrayList<>();blackrules.add(new PawnFirstMove()); blackrules.add(new PawnMove()); blackrules.add(new PawnBackCapture());
        List<MovementRule> whiterules = new ArrayList<>();whiterules.add(new PawnFirstMove()); whiterules.add(new PawnMove()); whiterules.add(new PawnFrontCapture());
        List<Piece> pawns = new ArrayList<>();
        int id = 1;
        for(int y = 0; y < 10; y++){ //white
            pawns.add(new Piece(String.valueOf(id), whiterules, new Pair<>(1,y), PieceName.PAWN, Color.WHITE, true));
            id++;
        }
        for(int y = 0; y < 10; y++){
            pawns.add(new Piece(String.valueOf(id), blackrules, new Pair<>(6,y), PieceName.PAWN, Color.BLACK, true));
            id++;
        }
        return Collections.unmodifiableList(pawns);
    }

    public List<Piece> generateRooks(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new HorizontalMovement()); rules.add(new VerticalMovement());
        List<Piece> rooks = new ArrayList<>();
        int id = 21;
        rooks.add(new Piece(String.valueOf(id), rules, new Pair<>(7,0), PieceName.ROOK, Color.BLACK, true));id++;
        rooks.add(new Piece(String.valueOf(id), rules, new Pair<>(7,9), PieceName.ROOK, Color.BLACK, true));id++;
        rooks.add(new Piece(String.valueOf(id), rules, new Pair<>(0,0), PieceName.ROOK, Color.WHITE, true));id++;
        rooks.add(new Piece(String.valueOf(id), rules, new Pair<>(0,9), PieceName.ROOK, Color.WHITE, true));
        return Collections.unmodifiableList(rooks);
    }

    public List<Piece> generateKnights(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new Lmovement());
        List<Piece> knights = new ArrayList<>();
        int id = 25;
        knights.add(new Piece(String.valueOf(id), rules, new Pair<>(7,1), PieceName.KNIGHT, Color.BLACK, true));id++;
        knights.add(new Piece(String.valueOf(id), rules, new Pair<>(7,8), PieceName.KNIGHT, Color.BLACK, true));id++;
        knights.add(new Piece(String.valueOf(id), rules, new Pair<>(0,1), PieceName.KNIGHT, Color.WHITE, true)); id++;
        knights.add(new Piece(String.valueOf(id), rules, new Pair<>(0,8), PieceName.KNIGHT, Color.WHITE, true));
        return Collections.unmodifiableList(knights);
    }

    public List<Piece> generateArchs(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new Lmovement()); rules.add(new DiagonalMovement());
        List<MovementRule> chancellor = new ArrayList<>(); chancellor.add(new Lmovement()); chancellor.add(new VerticalMovement()); chancellor.add(new HorizontalMovement());
        List<Piece> piece = new ArrayList<>();
        int id = 29;
        piece.add(new Piece(String.valueOf(id), rules, new Pair<>(0,2), PieceName.ARCHBISHOP, Color.WHITE, true));id++;
        piece.add(new Piece(String.valueOf(id), chancellor, new Pair<>(0,7), PieceName.CHANCELLOR, Color.WHITE, true));id++;
        piece.add(new Piece(String.valueOf(id), rules, new Pair<>(7,2), PieceName.ARCHBISHOP, Color.BLACK, true));id++;
        piece.add(new Piece(String.valueOf(id), chancellor, new Pair<>(7,7), PieceName.CHANCELLOR, Color.BLACK, true));
        return Collections.unmodifiableList(piece);
    }

    public List<Piece> generateBishops(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new DiagonalMovement());
        List<Piece> bishops = new ArrayList<>();
        int id = 33;
        bishops.add(new Piece(String.valueOf(id), rules, new Pair<>(7,3), PieceName.BOSHOP, Color.BLACK, true));id++;
        bishops.add(new Piece(String.valueOf(id), rules, new Pair<>(7,6), PieceName.BOSHOP, Color.BLACK, true));id++;
        bishops.add(new Piece(String.valueOf(id), rules, new Pair<>(0,3), PieceName.BOSHOP, Color.WHITE, true));id++;
        bishops.add(new Piece(String.valueOf(id), rules, new Pair<>(0,6), PieceName.BOSHOP, Color.WHITE, true));
        return Collections.unmodifiableList(bishops);
    }

    public List<Piece> generateQueens(){
        List<MovementRule> rules = new ArrayList<>();rules.add(new HorizontalMovement()); rules.add(new VerticalMovement()); rules.add(new DiagonalMovement());
        List<Piece> queens = new ArrayList<>();
        int id = 37;
        queens.add(new Piece(String.valueOf(id), rules, new Pair<>(0,5), PieceName.QUEEN, Color.WHITE, true)); id++;
        queens.add(new Piece(String.valueOf(id), rules, new Pair<>(7,5), PieceName.QUEEN, Color.BLACK, true));
        return Collections.unmodifiableList(queens);
    }

    public List<Piece> generateKings(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new KingMovement());
        List<Piece> kings = new ArrayList<>();
        int id = 39;
        kings.add(new Piece(String.valueOf(id),rules,new Pair<>(0,4),PieceName.KING,Color.WHITE, true));id++;
        kings.add(new Piece(String.valueOf(id),rules,new Pair<>(7,4),PieceName.KING, Color.BLACK, true));
        return Collections.unmodifiableList(kings);
    }
}
