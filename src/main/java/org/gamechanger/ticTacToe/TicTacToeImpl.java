package org.gamechanger.ticTacToe;

import org.gamechanger.ComputerPlayerInterface;
import org.gamechanger.GameValidatorService;
import org.gamechanger.boardGameCommons.GameUtility;
import org.gamechanger.boardGameCommons.Move;
import org.gamechanger.connect4.Directions;
import org.gamechanger.connect4.BoardElement;

import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;

public record TicTacToeImpl(TicTacToeBoard board) implements ComputerPlayerInterface, GameValidatorService {
    /**
     * generateComputerMove iterates over different directional values to identify the next move
     * iterates firstly starting at highest priority, being 3 spaces in a row, and passes this value in the attemptToPlaceToken method
     * iterates over every directional property in the Directions Enum to ensure robust checking of all angles of the board
     * returns once a token has been placed to prevent the chain from continuing
     * if the game has just started or there are no identifiable chains, a random move will be placed
     */
    @Override
    public void generateComputerMove() {
        List<BoardElement> computerMoves = board.getPlayerSpaces("y");
        List<BoardElement> playerMoves = board.getPlayerSpaces("r");
        Set<String> computerPositionSet = board.createPlayerPositionSet("y");
        Set<String> playerPositionSet = board.createPlayerPositionSet("r");

        if (!computerMoves.isEmpty()) {
            for (int i = 3; i > 1; i--) {
                for (Directions dir : Directions.values()) {
                    if (hasConsecutivePlayerMovesInDirection(computerMoves, dir, i)){
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

    @Override
    public boolean isValidInput(String input) throws DataFormatException {
        return false;
    }

    @Override
    public boolean isVictorious(String token) {
        return false;
    }

    @Override
    public boolean hasConsecutivePlayerMovesInDirection(List<BoardElement> playerSpaces, Directions directions, int count) {
        return false;
    }
}
