package org.gamechanger.boardGameCommons;

public enum GameChoice {
    
    CONNECT_FOUR(1, "Connect Four"),
    TIC_TAC_TOE(2, "Tic Tac Toe");

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
