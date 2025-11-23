package org.gamechanger.connect4;

import org.gamechanger.boardGameCommons.GameBoard;
import org.gamechanger.connect4.constants.Directions;
import org.gamechanger.boardGameCommons.Move;
import org.gamechanger.connect4.model.BoardElement;
import org.gamechanger.boardGameCommons.ComputerPlayerInterface;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectFourPlayerImpl implements ComputerPlayerInterface {
    private final MyConnectFour myConnectFour;
    private final GameBoard board;

    public ConnectFourPlayerImpl(ConnectFourImpl myConnectFour) {
        this.myConnectFour = myConnectFour;
        this.board = myConnectFour.getBoard();
    }

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
        Set<String> computerPositionSet = createPlayerPositionSet("y");
        Set<String> playerPositionSet = createPlayerPositionSet("r");

        if (!computerMoves.isEmpty()){
            for (int i = 3; i > 1; i--){
                for (Directions dir : Directions.values()) {
                    if (myConnectFour.hasConsecutivePlayerMovesInDirection(computerMoves, dir, i)) {
                        attemptToPlaceToken(computerPositionSet, computerMoves, dir, i);
                        return;
                    } else if (myConnectFour.hasConsecutivePlayerMovesInDirection(playerMoves, dir, i)) {
                        attemptToPlaceToken(playerPositionSet, playerMoves, dir, i);
                        return;
                    }
                }
            }
            selectRandom();
        } else {
            selectRandom();
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
    private void attemptToPlaceToken(Set<String> positionSet, List<BoardElement> userPlaces, Directions direction, int count){
        // converting to a string allows for much easier and less verbose checking of positions
        Set<String> emptySpacesSet = createEmptyPositionSet();

        for (BoardElement place : userPlaces) {
            int row = place.getRowPosition();
            int col = place.getColumnPosition();

            int[] start = checkPositions(row, col, positionSet, direction);
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

    /**
     * checkPositions scans for negative positions i.e. in the opposite direction to that specified
        * It checks whether the previous cell in the given direction is also occupied:
            * If so, it moves backward (subtracts the direction offset - for instance DOWN is 0,1 so would look for 3,3 if the previous pos was 3,4) and repeats.
            * Once it reaches a cell that isn't occupied, it stops
        * This way it is able to find the actual first position of the chain, allowing the computer to identify the best spot to block
     */
    private int[] checkPositions(int row, int col, Set<String> positionSet, Directions direction) {
        while (positionSet.contains((row - direction.getRowPos()) + "," + (col - direction.getColumnPos()))) {
            row -= direction.getRowPos();
            col -= direction.getColumnPos();
        }
        return new int[]{row, col};
    }

    /**
     * createPlayerPositionSet Utilises user token in order to search and map elements by row and column
        * Using a set avoids the need for iterating over Lists whilst allowing quick access to elements
        *  Crucially allows access to "contains" method of String type, which can quickly identify an object in a collection
     */

    private Set<String> createPlayerPositionSet(String token){
        return board.getPlayerSpaces(token).stream()
                .map(place -> place.getRowPosition() + "," + place.getColumnPosition()) // Maps place and column separated by comma
                .collect(Collectors.toSet()); // Using a set avoids the need for iterating over Lists whilst allowing quick access to elements
    }

    /**
     * createEmptyPositionSet also utilises this logic but for empty spaces - this helps to locate the next empty space
        * Allows for quick identification of whether the column and row chosen can be occupied by the computer user
     */
    private Set<String> createEmptyPositionSet(){
        return board.getEmptySpaces().stream()
                .map(emptyPlace -> emptyPlace.getRowPosition() + "," + emptyPlace.getColumnPosition())
                .collect(Collectors.toSet());
    }

    /**
     * Simple method to select a random space on the board to put a board piece
     */
    private void selectRandom() {
        board.getEmptySpaces().stream().findAny().ifPresent(boardElement -> boardElement.setBoardPlace("y"));
    }
}
