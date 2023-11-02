package edu.austral.dissis.chess.app.adapter;

import edu.austral.dissis.chess.gui.Position;
import org.javatuples.Pair;

public class JavaDataAdapter {

    public static Pair<Integer,Integer> adaptPosition(Position position){
        Integer row = position.getRow()-1; Integer column = position.getColumn()-1;
        return new Pair<>(row, column);
    }
}
