package connect4;

import java.util.zip.DataFormatException;

public interface MyConnectFour {

    Board createBoard();
    void editBoard(Move move, String playerMove);
    void generateComputerMove(Computer computer);
    boolean hasWon(String player);
    void printBoard();
    boolean isValidInput(String input) throws DataFormatException;

}
