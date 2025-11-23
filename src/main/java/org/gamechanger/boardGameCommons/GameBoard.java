package org.gamechanger.boardGameCommons;

import org.gamechanger.connect4.BoardElement;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GameBoard {

    private final List<BoardElement> boardElements;

    public GameBoard(List<BoardElement> boardElements) {
        this.boardElements = boardElements;
    }

    public List<BoardElement> getBoardElements() {
        return boardElements;
    }

    public List<BoardElement> getByRow(int row){
        return getBoardElements().stream().filter(boardElement -> boardElement.getRowPosition() == row).toList();
    }
    public List<BoardElement> getByColumn(int column){
        return getBoardElements().stream().filter(boardElement -> boardElement.getRowPosition() == column).toList();
    }

    /**
     * createPlayerPositionSet Utilises user token in order to search and map elements by row and column
     * Using a set avoids the need for iterating over Lists whilst allowing quick access to elements
     * Crucially allows access to "contains" method of String type, which can quickly identify an object in a collection
     */
    public Set<String> createPlayerPositionSet(String token) {
        return getPlayerSpaces(token).stream()
                .map(place -> place.getRowPosition() + "," + place.getColumnPosition()) // Maps place and column separated by comma
                .collect(Collectors.toSet()); // Using a set avoids the need for iterating over Lists whilst allowing quick access to elements
    }

    /**
     * createEmptyPositionSet also utilises this logic but for empty spaces - this helps to locate the next empty space
     * Allows for quick identification of whether the column and row chosen can be occupied by the computer user
     */
    public Set<String> createEmptyPositionSet() {
        return getEmptySpaces().stream()
                .map(emptyPlace -> emptyPlace.getRowPosition() + "," + emptyPlace.getColumnPosition())
                .collect(Collectors.toSet());
    }

    /**
     * Simple method to select a random space on the board to put a board piece
     */
    public void selectRandom() {
        getEmptySpaces().stream().findAny().ifPresent(boardElement -> boardElement.setBoardPlace("y"));
    }

    public boolean isPlaceEmpty(Move move) {
        for (BoardElement be : getBoardElements()) {
            if (be.getRowPosition() == move.getRow()
                    && be.getColumnPosition() == move.getColumn()
                    && !be.getBoardPlace().contains("r") &&
                    !be.getBoardPlace().contains("y")) {
                return true;
            }
        }
        return false;
    }

    public List<BoardElement> getPlayerSpaces(String token){
        return getBoardElements().stream().filter(boardElement -> boardElement.getBoardPlace().contains(token)).toList();
    }

    public List<BoardElement> getEmptySpaces(){
        return getBoardElements().stream().filter(boardElement -> !boardElement.getBoardPlace().contains("r") && !boardElement.getBoardPlace().contains("y")).toList();
    }

    public abstract boolean validateRowInput(int row);
    public abstract void editBoard(Move move, String playerMove);
}
