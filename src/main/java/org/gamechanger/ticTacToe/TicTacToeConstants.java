package org.gamechanger.ticTacToe;

import org.gamechanger.boardGameCommons.CommonConstants;

/**
 * TicTacToeConstants is a class used to hold constant variables - mainly
 * A constants-only class is not meant to be extended, so marking it final enforces that intent.
 * final signals that the class is complete and self-contained — no inheritance needed or allowed.
 */
public final class TicTacToeConstants extends CommonConstants {

    @Override
    public String WELCOME_MESSAGE() {
        return "Welcome to Tic Tac Toe";
    }
    @Override
    public String PLAYERS_INTRO() {
        return "There are 2 players: x, and y. Player 1 is x, Player 2 is y.";
    }
    @Override
    public String HOW_TO_PLAY() {
        return "To play the game, type in the number of the column and row that you want to place your move in";
    }
    @Override
    public String HOW_TO_PLAY_EXAMPLE() {
        return "For instance, 6,5 to enter in the 6th column, 5th row";
    }
    @Override
    public String HOW_TO_WIN() {
        return "A player wins by connecting 3 moves together - vertically, horizontally or diagonally";
    }
    @Override
    public int COLUMN_SIZE() {
        return 3;
    }
    @Override
    public int ROW_SIZE() {
        return 3;
    }
    @Override
    protected String WINNER_MESSAGE() {
        return "You've won!! Congratulations, and thank you for playing Tic-Tac-Toe. Goodbye!";
    }
    @Override
    protected String EXIT_MESSAGE() {
        return "Thank you for playing Tic-Tac-Toe. Goodbye!";
    }
    @Override
    protected String RESULT_MESSAGE(String winner, String loser) {
        if (winner.matches("Robot")){
            return "You lost to the robots... Better luck next time!! Thank you for playing connect4. Goodbye!";
        } else {
            return "And the winner is... " + winner + "!" + " Better luck next time " + loser + "!! Thank you for playing Tic-Tac-Toe. Goodbye!";
        }
    }
}


