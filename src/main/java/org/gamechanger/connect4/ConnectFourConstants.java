package org.gamechanger.connect4;

import org.gamechanger.boardGameCommons.CommonConstants;

/**
 * TicTacToeConstants is a class used to hold constant variables - mainly
 * A constants-only class is not meant to be extended, so marking it final enforces that intent.
 * final signals that the class is complete and self-contained — no inheritance needed or allowed.
 */
public class ConnectFourConstants extends CommonConstants {
    public static final String EXIT_MESSAGE = "Thank you for playing connect4. Goodbye!";
    public static final String WINNER_MESSAGE = "You've won!! Congratulations, and thank you for playing connect4. Goodbye!";
    public static final String LOSER_MESSAGE = "You lost to the robots... Better luck next time!! Thank you for playing connect4. Goodbye!";

    @Override
    public String WELCOME_MESSAGE() {
        return "Welcome to Connect 4";
    }

    @Override
    public String PLAYERS_INTRO() {
        return "There are 2 players: Red, and Yellow. Player 1 is Red, Player 2 is Yellow.";
    }

    @Override
    public String HOW_TO_PLAY() {
        return "To play the game, type in the number of the column and row that you want to drop you counter in";
    }

    @Override
    public String HOW_TO_PLAY_EXAMPLE() {
        return "For instance, 6,5 to enter in the 6th column, 5th row";
    }

    @Override
    public String HOW_TO_WIN() {
        return "A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally";
    }

    @Override
    public int COLUMN_SIZE() {
        return 7;
    }

    @Override
    public int ROW_SIZE() {
        return 6;
    }

    @Override
    protected String WINNER_MESSAGE() {
        return null;
    }

    @Override
    protected String EXIT_MESSAGE() {
        return null;
    }

    @Override
    protected String RESULT_MESSAGE(String winner, String loser) {
        return null;
    }
}

