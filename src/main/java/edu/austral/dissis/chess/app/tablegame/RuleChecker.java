package edu.austral.dissis.chess.app.tablegame;

import edu.austral.dissis.chess.app.gamemanegment.Game;
import edu.austral.dissis.chess.app.rule.WinCondition;
import lombok.Getter;
import edu.austral.dissis.chess.app.rule.GameRule;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RuleChecker {

    private final List<GameRule> gameRules;
    private final List<WinCondition> winConditions;

    public RuleChecker(List<GameRule> gameRules, List<WinCondition> winConditions){
        this.gameRules = gameRules;
        this.winConditions = winConditions;
    }

    public Boolean hasWon(Game game){
        List<Boolean> results = new ArrayList<>();
        winConditions.forEach(condition -> results.add(condition.hasWon(game)));
        for(Boolean result:results){
            if(result){
                return true;
            }
        }
        return false;
    }

    public Boolean checkRules(Game game){
        List<Boolean> results = new ArrayList<>();
        gameRules.forEach(rule -> results.add(rule.validateTurn(game)));
        for(Boolean result: results){
            if(result){
                return true;
            }
        }
        return false;
    }
}
