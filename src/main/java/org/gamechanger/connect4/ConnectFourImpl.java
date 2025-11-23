package org.gamechanger.connect4;

import org.gamechanger.GameValidatorService;
import org.gamechanger.ComputerPlayerInterface;
import org.gamechanger.boardGameCommons.GameUtility;
import org.gamechanger.boardGameCommons.Move;

import java.util.*;
import java.util.stream.Collectors;

import static org.gamechanger.boardGameCommons.CommonConstants.*;

/**
 * Class implements MyConnectFour interface, which standardises the behaviours for board validation and shared player/computer operations
 */
public record ConnectFourImpl(ConnectFourBoard board) implements GameValidatorService, ComputerPlayerInterface {

    @Override
    public void generateComputerMove() {
        List<BoardElement> computerMoves = board.getPlayerSpaces("y");
        List<BoardElement> playerMoves = board.getPlayerSpaces("r");
        Set<String> computerPositionSet = board.createPlayerPositionSet("y");
        Set<String> playerPositionSet = board.createPlayerPositionSet("r");

        if (!computerMoves.isEmpty()) {
            for (int i = 3; i > 1; i--) {
                for (Directions dir : Directions.values()) {
                    if (hasConsecutivePlayerMovesInDirection(computerMoves, dir, i)) {
                        attemptToPlaceToken(computerPositionSet, computerMoves, dir, i);
                        return;
                    } else if (hasConsecutivePlayerMovesInDirection(playerMoves, dir, i)) {
                        attemptToPlaceToken(playerPositionSet, playerMoves, dir, i);
                        return;
                    }
                }
            }
            board.selectRandom();
        } else {
            board.selectRandom();
        }
    }

    /**
     * Method handles many potential unchecked exception cases e.g. DataFormatErrors or issues parsing the string
     * Returns true or false and handles the logging with a dedicated message, but doesn't throw errors so as to preserve game state.
     *
     * @param input
     * @return
     */
    @Override
    public boolean isValidInput(String input) {
        if (!input.contains(",")) {
            System.out.println(TOO_FEW_INPUTS);
            return false;
        }
        String[] inputs = GameUtility.formatInput(input);
        if (inputs.length > 2) {
            System.out.println(TOO_MANY_INPUTS);
            return false;
        } else if (inputs.length < 2) {
            System.out.println(TOO_FEW_INPUTS);
            return false;
        }
        int column;
        int row;
        if (inputs[0].matches("\\d+")) {
            column = Integer.parseInt(inputs[0]);
        } else {
            System.out.printf(NON_DIGIT_CHAR_ERROR, inputs[0] + "\n");
            return false;
        }
        if (inputs[1].matches("\\d+")) {
            row = Integer.parseInt(inputs[1]);
        } else {
            System.out.printf(NON_DIGIT_CHAR_ERROR, inputs[1] + "\n");
            return false;
        }
        if (board.validateColumnInput(column) && board.validateRowInput(row)) {
            if (board.isPlaceEmpty(new Move(column, row))) {
                return true;
            } else {
                System.out.println(PLACE_TAKEN);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * isConnect4 checks for a connect4 winning instance across 4 different planes - diagonal down right (\), diagonal up right (/), down and right.
     * Takes in a String token to keep it flexible for computer user and human user and filters board elements based on this token.
     * Returned Player GameBoard spaces must be a size greater than or equal to 4 for the check to take place, else it returns false.
     * Logic is abstracted to a method hasConsecutivePlayerMovesInDirection which handles these cases.
     *
     * @param token
     * @return
     */
    @Override
    public boolean isVictorious(String token) {
        List<BoardElement> playerBoardSpaces = board.getBoardElements().stream().filter(bp -> bp.getBoardPlace().contains(token)).toList();
        boolean horizontalWin = hasConsecutivePlayerMovesInDirection(playerBoardSpaces, Directions.RIGHT, 4);
        boolean verticalWin = hasConsecutivePlayerMovesInDirection(playerBoardSpaces, Directions.DOWN, 4);
        boolean diagonalWin = hasConsecutivePlayerMovesInDirection(playerBoardSpaces, Directions.DOWN_RIGHT, 4) || hasConsecutivePlayerMovesInDirection(playerBoardSpaces, Directions.UP_RIGHT, 4);

        if (playerBoardSpaces.size() >= 4) {
            return horizontalWin || verticalWin || diagonalWin;
        }
        return false;
    }

    /**
     * hasConsecutivePlayerMovesInDirection generalises and abstracts the logic needed to either find the next move or find the winning move for a computer player or human player.
     * First, it utilises the map feature of java's stream() api in order to create a Set<String> object containing the mapped values.
     * This allows for quicker searching of mapped column and row values by utilising "contains()" method of the String.
     * The board elements containing player spaces is then iterated through sequentially, and row / column values are compared against the set.
     * If found, the loop will iterate onto the next value in the list and compare again with the set.
     * An instance of the direction enum is used so that individual values can be passed through and checked according to direction.
     * The count is also generalised, allowing the method to be widely applicable to many cases.
     * The loop is only broken if there is not another consecutive item in the chain, whilst it returns true if the required number of elements is found / the next element can be taken by the user or blocked by the computer.
     */

    @Override
    public boolean hasConsecutivePlayerMovesInDirection(List<BoardElement> playerSpaces, Directions direction, int count) {
        // converting to a string allows for much easier and less verbose checking of positions
        Set<String> positionSet = playerSpaces.stream()
                .map(place -> place.getRowPosition() + "," + place.getColumnPosition())// maps place and column comma separated
                .collect(Collectors.toSet());

        for (BoardElement place : playerSpaces) { // loops through every element in the set
            int row = place.getRowPosition();
            int col = place.getColumnPosition();

            boolean isConsecutive = true;
            for (int i = 1; i < count; i++) { // checks for consecutive positions starting from 1 with a max limit defined by calling method
                // current row value + value of i * the row positions of the direction to find next row val
                // for instance if going horiztonally, row will stay at 0 but check for next columns; vice versa for vertical.
                // diagonally it will work like so:
                // if column,row at 1 = '5,4' and it is applying UP_RIGHT logic,
                // nextRow = 5 + 1 * (-1) -> 5 + (-1) = 4
                // nextCol = 4 + 1 * (1) -> 4 + 1 = 5
                // nextPos = "4,5"
                String nextPos = (row + i * direction.getRowPos())
                        + "," + (col + i * direction.getColumnPos());

                if (!positionSet.contains(nextPos)) { // breaks the loop if the next val in the set is not consecutive
                    isConsecutive = false;
                    break;
                }
            }
            int winningRow = row + count * direction.getRowPos();
            int winningCol = col + count * direction.getColumnPos();
            Move move = new Move(winningCol, winningRow);
            if (count == 4) {
                if (isConsecutive) { // if count is 4 it is checking for an already won scenario
                    return true;
                }
            } else if (isConsecutive && board.isPlaceEmpty(move)) { // if count is less then it needs to validate the position is empty before returning true
                return true;
            }
        }
        return false;
    }

    /**
     * attemptToPlaceToken is a method designed to iterate through each board element of recorded player places to determine to determine whether the user can win on the next move
     * Using the same offset logic as attemptToPlaceToken (checkPositions to find start of chain using a directional offset value), it determines if there are 3 recorded player moves in a chain
     * If there are 3 recorded, it checks against an emptySpacesSet object in order to validate the potential winning slot
     * The method will then place a computer counter in that slot in order to stop the user winning the game or instead allow the computer to win
     * This method abstracts and modularises the logic by generalising the implementation:
     * 1: using a count value - which can change depending on how many spaces the program needs to check
     * 2: using a userPlaces list that can apply to either the computer player or human player
     * 3: the position set is also applicable to either computer or human sets of occupied positions
     * 4: using a Direction enum to allow full iteration over values to allow for robust checking of mutliple directions
     */
    private void attemptToPlaceToken(Set<String> positionSet, List<BoardElement> userPlaces, Directions direction, int count) {
        // converting to a string allows for much easier and less verbose checking of positions
        Set<String> emptySpacesSet = board.createEmptyPositionSet();

        for (BoardElement place : userPlaces) {
            int row = place.getRowPosition();
            int col = place.getColumnPosition();

            int[] start = GameUtility.checkPositions(row, col, positionSet, direction);
            row = start[0];
            col = start[1];

            // Because it starts from an offset position, only iterates to values up to but not including 3. Essentially 0,1,2 - so three pos
            boolean isThree = true;
            for (int i = 1; i < count; i++) {
                String nextPos = (row + i * direction.getRowPos()) + "," + (col + i * direction.getColumnPos());
                if (!positionSet.contains(nextPos)) {
                    isThree = false;
                    break;
                }
            }

            // 3rd (4th in the modified chain) pos is the potential winning spot
            // refer to 'hasConsecutivePlayerMovesInDirection' for more info on this logic
            int winningRow = row + count * direction.getRowPos();
            int winningCol = col + count * direction.getColumnPos();
            String winningPos = winningRow + "," + winningCol;

            if (isThree && emptySpacesSet.contains(winningPos)) { // as long as it is empty, it will make the winning move
                board.editBoard(new Move(winningCol, winningRow), "y");
                return;
            }
        }
    }

}
