package edu.austral.dissis.chess.app.factory.checkers;

import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.boardmanegment.BoardController;
import edu.austral.dissis.chess.app.boardmanegment.RegularChessBoardController;
import edu.austral.dissis.chess.app.factory.Factory;
import edu.austral.dissis.chess.app.gamemanegment.CheckersGame;
import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.piecemanegment.Piece;
import edu.austral.dissis.chess.app.piecemanegment.PieceName;
import edu.austral.dissis.chess.app.piecemanegment.RegularChessPieceController;
import edu.austral.dissis.chess.app.playermanegment.RegularChessPlayerController;
import edu.austral.dissis.chess.app.rule.MovementRule;
import edu.austral.dissis.chess.app.rule.movements.special.checker.CheckerCapture;
import edu.austral.dissis.chess.app.rule.movements.special.checker.CheckerMove;
import edu.austral.dissis.chess.app.tablegame.RuleChecker;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckersFactory implements Factory {
    @Override
    public Game generateChessGame() {
        List<Piece> checkers = new ArrayList<>();
        checkers.addAll(generateWhiteCheckers(0,0,1,new ArrayList<>()));
        checkers.addAll(generateBlackCheckers(7,1,13,new ArrayList<>()));
        BoardController boardController = new RegularChessBoardController().generateBoardWithPieces(checkers,8,8);
        return new CheckersGame(new RuleChecker(null, null), boardController, new RegularChessPlayerController(), new RegularChessPieceController(), null);
    }

    public List<Piece> generateWhiteCheckers(int x, int y,int id, List<Piece> checkers){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new CheckerCapture()); rules.add(new CheckerMove());
        if(checkers.size() == 12){return Collections.unmodifiableList(checkers);}
        int newColumn = (y==1)? 0 : 1;
        while(y < 8){
            checkers.add(new Piece(String.valueOf(id), rules, new Pair<>(x,y), PieceName.CHECKER, Color.WHITE, true));
            id++; y = y + 2;
        }
        x++;
        return generateWhiteCheckers(x, newColumn, id, checkers);
    }

    public List<Piece> generateBlackCheckers(int x, int y, int id, List<Piece> checkers){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new CheckerCapture()); rules.add(new CheckerMove());
        if(checkers.size() == 12){return Collections.unmodifiableList(checkers);}
        int newColumn = (y==1)? 0:1;
        while(y < 8){
            checkers.add(new Piece(String.valueOf(id), rules, new Pair<>(x,y), PieceName.CHECKER, Color.BLACK, true));
            id++; y = y + 2;
        }
        x--;
        return generateBlackCheckers(x,newColumn, id, checkers);
    }
}
