package edu.austral.dissis.chess.app.piecemanegment;
import edu.austral.dissis.chess.app.Color;
import edu.austral.dissis.chess.app.rule.MovementRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.javatuples.Pair;

import java.util.List;

public class Piece {

    private final String id;
    private final List<MovementRule> rules;
    private final Pair<Integer, Integer> position;
    private final Boolean initialPosition;
    private final PieceName name;
    private final Color color;

    public Piece(String id, List<MovementRule> rules, Pair<Integer, Integer> position, PieceName name, Color color, Boolean initialPosition) {
        this.id = id;
        this.rules = rules;
        this.position = position;
        this.name = name;
        this.color = color;
        this.initialPosition = initialPosition;
    }


    public String getId() {
        return id;
    }

    public List<MovementRule> getRules() {
        return rules;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public PieceName getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Boolean getInitialPosition(){
        return initialPosition;
    }

}