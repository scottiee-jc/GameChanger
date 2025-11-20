package org.gamechanger.boardGameCommons;

public class CommonConstants {

    public static final String BOARD_PIECE = "|   ";
    public static final String END_PIECE = "|   |";
    public static final String NON_DIGIT_CHAR_ERROR = "You entered an incorrect character: %s";
    public static final String NOT_VALID_COLUMN_VAL_ERROR = "Please ensure you enter a digit less than or equal to %d. You entered: %d";
    public static final String NOT_VALID_ROW_VAL_ERROR = "Please ensure you enter a digit less than or equal to %d. You entered: %d";
    public static final String TOO_MANY_INPUTS = "Too many characters input. Please enter TWO numbers seperated by a comma in format 'C,R' where C = column number and R = row number. For instance, '1,3'";
    public static final String TOO_FEW_INPUTS = "Too few characters input. Please enter TWO numbers seperated by a comma in format 'C,R' where C = column number and R = row number. For instance, '1,3'";
    public static final String PLACE_TAKEN = "That place has been taken. Please try again";
}
