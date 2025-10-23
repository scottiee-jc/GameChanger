package connect4;

import java.util.*;

import static connect4.ConnectFourConstants.*;

public class MyConnectFourImpl implements MyConnectFour {

    private Board board;

    @Override
    public Board createBoard() {
        Row row;
        List<BoardElement> rows = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            List<BoardPlace> boardPlaces = new ArrayList<>();
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
        System.out.println(board);
        System.out.println("     1   2   3   4   5   6   7");
    }

    @Override
    public void editBoard(Move move, String playerMove) {
        List<BoardElement> rows = board.getBoardElements();

        for (BoardElement r: rows) {
            if (r.getPosition() == move.getRow()){
                List<BoardPlace> boardPlaces = rows.get(move.getRow()-1).getBoardPlaces();
                for (BoardPlace bp : boardPlaces){
                    if (bp.getPosition() == move.getColumn()){
                        bp.placeMove(playerMove, move.getColumn());
                    }
                }
            }
        }
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
        int column = Integer.parseInt(inputs[0]);
        int row = Integer.parseInt(inputs[1]);
        if (validateColumnInput(column) && validateRowInput(row)){
            return !isPlaceTaken(new Move(column,row));
        } else {
            return false;
        }
    }


    @Override
    public void generateComputerMove(Computer computer) {
        Move playerLastMove = computer.getPlayerLastMove();
        Move move = findBestSpace();
        if (findBestSpace() != null){
            editBoard(move, "y");
        } else {
            blockUserMove();
        }

        // Idea could be to generate a computer move that either:
        // Scans the board for four empty spaces up or down - Looks to place its own tack
        // Scans the board for user move - if about to complete 3, look to place tack to block
    }

    private Move findBestSpace(){
        List<BoardElement> rows = board.getBoardElements();
        for (BoardElement r: rows) {
            int rowPos = r.getPosition();
            List<Integer> emptyRowSegments = r.getBoardPlaces().stream().filter(bp -> !bp.getBoardPiece().contains("r") || !bp.getBoardPiece().contains("y")).map(BoardPlace::getPosition).toList();
            for (int segment : emptyRowSegments) {
                if (segment < 6 && segment > 0){
                    if (r.getBoardPlaces().get(segment + 1).getBoardPiece().contains("y")) {
                        return new Move(r.getBoardPlaces().get(segment).getPosition(), rowPos);
                    } else if (r.getBoardPlaces().get(segment-1).getBoardPiece().contains("y")){
                        return new Move(r.getBoardPlaces().get(segment).getPosition(), rowPos);
                    }
                }
            }
            int column = emptyRowSegments.stream().findAny().get();
            return new Move(column, rowPos);
        }
        return null;
    }

    private void blockUserMove(){
        if (findThree(board.getListOfColumns()) != null){
            Move nextMove = findThree(board.getListOfColumns());
            if (validateColumnInput(nextMove.getColumn()) && !isPlaceTaken(nextMove)){
                editBoard(nextMove, "y");
            }
        } else if (findThree(board.getBoardElements()) != null){
            Move nextMove = findThree(board.getBoardElements());
            if (validateRowInput(nextMove.getRow()) && !isPlaceTaken(nextMove)){
                editBoard(nextMove, "y");
            }
        } else {
            // logic for diagonal?
        }
    }

    @Override
    public boolean hasWon(String token) {
        return isHorizontalConnectFour(token) || isVerticalConnectFour(token) || isDiagonalConnectFour(token);
    }

    private boolean isHorizontalConnectFour(String token){
        List<BoardElement> rows = board.getBoardElements();
        return findFour(rows, token);
    }

    private boolean isVerticalConnectFour(String token) {
        List<BoardElement> columns = board.getListOfColumns();
        return findFour(columns, token);
    }

    private boolean isDiagonalConnectFour(String token){
        List<BoardElement> columns = board.getListOfColumns();
        List<Move> moves = new ArrayList<>(); // combines the row and column position of the user moves
        for (BoardElement column : columns) {
            List<Integer> rowPos = column.getBoardPlaces().stream()
                    .filter(bp -> bp.getBoardPiece().contains(token))
                    .map(BoardPlace::getPosition)
                    .toList();

            for (Integer row : rowPos) {
                moves.add(new Move(column.getPosition(), row));
            }
        }
        for (Move m: moves) {
            System.out.println("column =" + m.getColumn() + " row =" +m.getRow());
        }
        // loop will read from top left to bottom right
        for (int i = 0; i <= moves.size() - 4; i++) {
            int currentRow = moves.get(i).getRow();
            int currentColumn = moves.get(i).getColumn();
            if (moves.get(i+1).getColumn() == currentColumn + 1 // the next column has to be after the current column, e.g 1 -> 2 -> 3 -> 4
                    && moves.get(i+1).getRow() == currentRow +1 // the same applies for the row, 1 -> 2 -> 3 -> 4
                && moves.get(i+2).getColumn() == currentColumn + 2 && moves.get(i+2).getRow() == currentRow +2
                && moves.get(i+3).getColumn() == currentColumn + 3 && moves.get(i+3).getRow() == currentRow +3){
                return true; // e.g. 1,1 -> 2,2 -> 3,3 -> 4,4 = true
            }
        }
        // loop will read from bottom left to top right
        for (int i = 0; i <= moves.size() - 4; i++) {
            int currentRow = moves.get(i).getRow();
            int currentColumn = moves.get(i).getColumn();
            if (moves.get(i + 1).getRow() == currentRow - 1  // the next row has to be before the current row, e.g. 6 -> 5 -> 4 -> 3
                    && moves.get(i + 1).getColumn() == currentColumn + 1 && // the next column has to be AFTER the current, e.g. 4 -> 5 -> 6 -> 7
                    moves.get(i + 2).getRow() == currentRow - 2 && moves.get(i + 2).getColumn() == currentColumn + 2 &&
                    moves.get(i + 3).getRow() == currentRow - 3 && moves.get(i + 3).getColumn() == currentColumn + 3) {
                return true; // e.g. 4,6 -> 5,5 -> 6,4 -> 7,3
            }
        }
        return false;
    }

    private boolean findFour(List<BoardElement> boardElements, String token){
        for (BoardElement be: boardElements) {
            List<BoardPlace> playerBoardSpaces = be.getBoardPlaces().stream().filter(bp -> bp.getBoardPiece().contains(token)).toList();
            if (playerBoardSpaces.size() >= 4){
                for (int i = 0; i <= playerBoardSpaces.size() - 4; i++) {
                    int current = playerBoardSpaces.get(i).getPosition();
                    if (playerBoardSpaces.get(i + 1).getPosition() == current + 1 &&
                            playerBoardSpaces.get(i + 2).getPosition() == current + 2 &&
                            playerBoardSpaces.get(i + 3).getPosition() == current + 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Move findThree(List<BoardElement> boardElements){
        for (BoardElement be: boardElements) {
            List<Integer> playerTakenSlots = be.getBoardPlaces().stream().filter(bp -> bp.getBoardPiece().contains("r")).map(BoardPlace::getPosition).toList();
            if (playerTakenSlots.size() >= 3){
                for (int i = 0; i <= playerTakenSlots.size() - 3; i++) {
                    int current = playerTakenSlots.get(i);
                    if (playerTakenSlots.get(i + 1) == current + 1 && playerTakenSlots.get(i + 2) == current + 2) {
                        if (be instanceof Column) {
                            return new Move(be.getPosition(), playerTakenSlots.get(i + 2) + 1); // returns the row position of next empty space
                        } else {
                            return new Move(playerTakenSlots.get(i + 2) + 1, be.getPosition()); // returns the column position of next empty space
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean isPlaceTaken(Move move){
        List<BoardElement> rows = board.getBoardElements();
        for (BoardElement r: rows) {
            if (r.getPosition() == move.getRow()){
                List<BoardPlace> boardPlaces = rows.get(move.getRow()-1).getBoardPlaces();
                for (BoardPlace bp : boardPlaces){
                    if (bp.getPosition() == move.getColumn()){
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

    private boolean validateRowInput(int row) {
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

    private boolean validateColumnInput(int column) {
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
