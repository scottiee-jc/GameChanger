package org.gamechanger.boardGameCommons;

public enum GameChoice {
    
    TIC_TAC_TOE(1, "Tic Tac Toe"),
    CONNECT_FOUR(2, "Connect Four");

    // SUDOKU?
    // FOOTBALL_QUIZ?
    
    private int value;
    private String name;

    GameChoice(int value, String name) {
        this.value = value;
        this.name = name;
    }

    GameChoice() {
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
