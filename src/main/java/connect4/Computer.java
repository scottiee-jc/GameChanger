package connect4;

import java.util.Deque;

public class Computer {

    final Deque<Move> moves;

    public Computer(Deque<Move> moves) {
        this.moves = moves;
    }

    public Deque<Move> getMoves() {
        return moves;
    }

    public Move getLastMove(){
        if (!moves.isEmpty()){
            return moves.getLast();
        }
        return null;
    }
}
