package org.gamechanger.connect4.model;

import org.gamechanger.boardGameCommons.Move;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Computer { // Computer should be final for immutability purposes - only one per game.
    private final Deque<Move> moves = new ArrayDeque<>();
    private final Deque<Move> playerLastMove = new ArrayDeque<>();

    public Computer() {}

    public Move getLastMove(){
        return moves.getLast();
    }
    public Move getPlayerLastMove(){
        return playerLastMove.getLast();
    }
    public void recordMove(Move move) {
        moves.addLast(move);
    }
    public void recordPlayerMove(Move move) {
        playerLastMove.addLast(move);
    }
}
