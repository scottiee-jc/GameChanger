package connect4;

/**
 * ConnectFourConstants is a class used to hold constant variables - mainly
 * A constants-only class is not meant to be extended, so marking it final enforces that intent.
 * final signals that the class is complete and self-contained — no inheritance needed or allowed.
 */
public final class ConnectFourConstants {

    public static final String WELCOME_MESSAGE = "Welcome to Connect 4";
    public static final String PLAYERS_INTRO = "There are 2 players: Red, and Yellow. Player 1 is Red, Player 2 is Yellow.";
    public static final String HOW_TO_PLAY = "To play the game, type in the number of the column and row that you want to drop you counter in";
    public static final String HOW_TO_PLAY_EXAMPLE = "For instance, 6,5 to enter in the 6th column, 5th row";
    public static final String HOW_TO_WIN = "A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally";
    public static final int COLUMN_SIZE = 7;
    public static final int ROW_SIZE = 6;
    public static final String BOARD_PIECE = "|   ";
    public static final String END_PIECE = "|   |";
    public static final String NON_DIGIT_CHAR_ERROR = "You entered an incorrect character: %s";
    public static final String NOT_VALID_COLUMN_VAL_ERROR = "Please ensure you enter a digit less than or equal to 7. You entered: %d";
    public static final String NOT_VALID_ROW_VAL_ERROR = "Please ensure you enter a digit less than or equal to 6. You entered: %d";
    public static final String TOO_MANY_INPUTS = "Too many characters input. Please enter TWO numbers seperated by a comma in format 'C,R' where C = column number and R = row number. For instance, '1,3'";
    public static final String TOO_FEW_INPUTS = "Too few characters input. Please enter TWO numbers seperated by a comma in format 'C,R' where C = column number and R = row number. For instance, '1,3'";
    public static final String PLACE_TAKEN = "That place has been taken. Please try again";
    public static final String EXIT_MESSAGE = "Thank you for playing connect4. Goodbye!";
    public static final String WINNER_MESSAGE = "You've won!! Congratulations, and thank you for playing connect4. Goodbye!";
    public static final String LOSER_MESSAGE = "You lost to the robots... Better luck next time!! Thank you for playing connect4. Goodbye!";

}

