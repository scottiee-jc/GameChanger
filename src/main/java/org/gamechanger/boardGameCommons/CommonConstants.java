package org.gamechanger.boardGameCommons;

public abstract class CommonConstants {

    public static final String BOARD_PIECE = "|   ";
    public static final String END_PIECE = "|   |";
    public static final String WELCOME_TO_LANDING = "Welcome to GameChanger! Which game would you like to play today?";
    public static final String INVALID_GAME_CHOICE = "I'm sorry, %d is not a valid game choice. Please enter either 1 or 2. \n";
    public static final String HOW_MANY_PLAYERS = "You've chosen to play %s!, how many players - 1 (play against computer) or 2? \n";
    public static final String CREATING_GAME = "Creating game of %s for %d player(s)... \n";
    public static final String NON_DIGIT_CHAR_ERROR = "You entered an incorrect character: %s";
    public static final String NOT_VALID_COLUMN_VAL_ERROR = "Please ensure you enter a digit less than or equal to %d. You entered: %d";
    public static final String NOT_VALID_ROW_VAL_ERROR = "Please ensure you enter a digit less than or equal to %d. You entered: %d";
    public static final String TOO_MANY_INPUTS = "Too many characters input. Please enter ONE numbers seperated by a comma in format 'C' where C = column number and R = row number. For instance, '1,3'";
    public static final String TOO_FEW_INPUTS = "Too few characters input. Please enter TWO numbers seperated by a comma in format 'C,R' where C = column number and R = row number. For instance, '1,3'";
    public static final String PLACE_TAKEN = "That place has been taken. Please try again";

    protected abstract String WELCOME_MESSAGE();
    protected abstract String PLAYERS_INTRO();
    protected abstract String HOW_TO_PLAY();
    protected abstract String HOW_TO_PLAY_EXAMPLE();
    protected abstract String HOW_TO_WIN();
    protected abstract int COLUMN_SIZE();
    protected abstract int ROW_SIZE();
    protected abstract String WINNER_MESSAGE();
    protected abstract String EXIT_MESSAGE();
    protected abstract String RESULT_MESSAGE(String winner, String loser);
}
