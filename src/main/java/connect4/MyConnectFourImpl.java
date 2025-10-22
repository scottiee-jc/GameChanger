package connect4;

import java.util.*;
import java.util.stream.Collectors;

import static connect4.ConnectFourConstants.*;

public class MyConnectFourImpl implements MyConnectFour {

    private Board board;

    @Override
    public Board createBoard() {
        Row row;
        List<Row> rows = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            Deque<BoardPlace> boardPlaces = new ArrayDeque<>();
            for (int j = 0; j < COLUMN_SIZE; j++) {
                BoardPlace boardPlace;
                if (j != COLUMN_SIZE - 1) {
                    boardPlace = new BoardPlace(j + 1, BOARD_PIECE);
                    boardPlaces.add(boardPlace);
                } else {
                    boardPlace = new BoardPlace(j + 1, END_PIECE);
                    boardPlaces.add(boardPlace);
                }
            }
            row = new Row(i+1, boardPlaces);
            rows.add(row);
        }
        board = new Board(rows);
        return board;
    }

    @Override
    public void printBoard(){
        if (board == null){
            return;
        }
        System.out.println(board.toString());
        System.out.println("     1   2   3   4   5   6   7");
    }

    @Override
    public void editBoard(String input, String playerMove) {
        String[] columnAndRow = formatInput(input);
        int column = Integer.parseInt(columnAndRow[0]);
        int row = Integer.parseInt(columnAndRow[1]);
        List<Row> rows = board.getRows();
        for (Row r: rows) {
            if (r.getPosition() == row){
                Deque<BoardPlace> boardPlaces = rows.get(row-1).getBoardPlaces();
                for (BoardPlace bp : boardPlaces){
                    if (bp.getColumnPosition() == column){
                        bp.placeMove(playerMove);
                    }
                }
            }
        }
    }


    @Override
    public void generateComputerMove() {
        // Idea could be to generate a computer move that either:
        // Looks to place

        // It can do this by:
        // Checking if there where the last move was
    }

    @Override
    public boolean hasWon(String player) {
        if (isHorizontalConnectFour(player)){
            return true;
        }
        // Logic:
        // If 4 horizontally, diagonally, or vertically contain "r" (or y for comp), player wins
        // Horizontally - check each column in one row
        //
        return false;
    }

    private boolean isHorizontalConnectFour(String player){
        List<Row> rows = board.getRows();
        for (Row r: rows) {
            Deque<BoardPlace> boardPlaces = r.getBoardPlaces();
            List<BoardPlace> playerBoardSpaces = boardPlaces.stream().filter(bp -> bp.getBoardPiece().contains(player)).toList();
            if (playerBoardSpaces.size() >= 4){
                for (int i = 0; i <= playerBoardSpaces.size() - 4; i++) {
                    int current = playerBoardSpaces.get(i).getColumnPosition();
                    if (playerBoardSpaces.get(i + 1).getColumnPosition() == current + 1 &&
                            playerBoardSpaces.get(i + 2).getColumnPosition() == current + 2 &&
                            playerBoardSpaces.get(i + 3).getColumnPosition() == current + 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean recurse(){

        for (int i = 1; i <= 7; i++) {
                int finalI = i;
        }
        if
    }

    @Override
    public boolean isValidInput(String input) {
        String[] inputs = formatInput(input);
        if (inputs.length > 2){
            System.out.println(TOO_MANY_INPUTS);
            return false;
        } else if (inputs.length < 2){
            System.out.println(TOO_FEW_INPUTS);
            return false;
        }
        if (validateColumnInput(inputs[0]) && validateRowInput(inputs[1])){
            int column = Integer.parseInt(inputs[0]);
            int row = Integer.parseInt(inputs[1]);
            return !isPlaceTaken(column, row);
        } else {
            return false;
        }
    }

    private boolean isPlaceTaken(int column, int row){
        List<Row> rows = board.getRows();
        for (Row r: rows) {
            if (r.getPosition() == row){
                Deque<BoardPlace> boardPlaces = rows.get(row-1).getBoardPlaces();
                for (BoardPlace bp : boardPlaces){
                    if (bp.getColumnPosition() == column){
                        if (bp.getBoardPiece().contains("r") || bp.getBoardPiece().contains("y")){
                            System.out.println(PLACE_TAKEN);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private String[] formatInput(String input){
        input = input.replaceAll("\\s+","").trim();
        return input.split(",");
    }

    private boolean validateRowInput(String row) {
        try {
            int rowValue = Integer.parseInt(row);
            if (rowValue < 7){
                return true;
            } else {
                System.out.printf((NOT_VALID_ROW_VAL_ERROR), rowValue);
                return false;
            }
        } catch (NumberFormatException n){
            System.out.printf((NON_DIGIT_CHAR_ERROR) + "%n", n.getMessage());
            return false;
        }
    }

    private boolean validateColumnInput(String column) {
        try {
            int columnValue = Integer.parseInt(column);
            if (columnValue <= 7) {
                return true;
            } else {
                System.out.printf((NOT_VALID_COLUMN_VAL_ERROR), columnValue);
                return false;
            }
        } catch (NumberFormatException n) {
            System.out.printf((NON_DIGIT_CHAR_ERROR) + "%n", n.getMessage());
            return false;
        }
    }

}
