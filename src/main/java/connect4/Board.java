package connect4;

import java.util.List;

import static connect4.ConnectFourConstants.*;

public class Board {
    private final List<BoardElement> boardElements;
    public Board(List<BoardElement> boardElements) {
        this.boardElements = boardElements;
    }

    public List<BoardElement> getBoardElements() {
        return boardElements;
    }

    public List<BoardElement> getByRow(int row){
        return boardElements.stream().filter(boardElement -> boardElement.getRowPosition() == row).toList();
    }

    public List<BoardElement> getByColumn(int column){
        return boardElements.stream().filter(boardElement -> boardElement.getRowPosition() == column).toList();
    }
    public List<BoardElement> getPlayerSpaces(String token){
        return boardElements.stream().filter(boardElement -> boardElement.getBoardPlace().contains(token)).toList();
    }

    public List<BoardElement> getEmptySpaces(){
        return boardElements.stream().filter(boardElement -> !boardElement.getBoardPlace().contains("r") && !boardElement.getBoardPlace().contains("y")).toList();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= ROW_SIZE ; i++) {
            if (i != 0){
                stringBuilder.append(i).append("  ");
            }
            for (BoardElement row: getByRow(i)) {
                stringBuilder.append(row.toString());
            }
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1); // remove the last empty line
        return stringBuilder.toString();
    }

    public boolean validateRowInput(int row) {
        try {
            if (row < 7){
                return true;
            } else {
                System.out.printf((NOT_VALID_ROW_VAL_ERROR), row);
                return false;
            }
        } catch (NumberFormatException n){
            System.out.printf((NON_DIGIT_CHAR_ERROR) + "%n", n.getMessage());
            return false;
        }
    }

    public boolean validateColumnInput(int column) {
        try {
            if (column <= 7) {
                return true;
            } else {
                System.out.printf((NOT_VALID_COLUMN_VAL_ERROR), column);
                return false;
            }
        } catch (NumberFormatException n) {
            System.out.printf((NON_DIGIT_CHAR_ERROR) + "%n", n.getMessage());
            return false;
        }
    }

}
