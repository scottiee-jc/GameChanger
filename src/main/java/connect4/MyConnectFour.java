package connect4;

import java.util.List;
import java.util.zip.DataFormatException;

public interface MyConnectFour {
    void editBoard(Move move, String playerMove);
    void printBoard();
    boolean isValidInput(String input) throws DataFormatException;
    boolean isConnect4(String token);
    boolean hasConsecutivePlayerMovesInDirection(List<BoardElement> playerSpaces, Directions directions, int count);
    boolean isPlaceEmpty(Move move);
}
