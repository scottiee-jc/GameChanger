package connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static connect4.ConnectFourConstants.*;

/**
 * GameRunner classes utilises the playGame() method to control logic flow during application runtime.
 * Allows for quick injection of the game dependencies, along with controlling access to the board
 */

public class GameRunner {

    public static void main(String[] args){
        try {
            playGame();
        } catch (Exception e){
            System.out.println("Error occured during connect4: " + e.getMessage());
        }
    }

    /**
     * playGame is the method that runs the game. It utilises two interfaces:
        * 1: MyConnectFourImpl - an interface for validation and utility operations but also to perform editing of the game board
        * 2: ComputerPlayerInterface - an interface that implements the computer player to simulate a real game.
     * Method cycles through a while loop, only exiting if the game is one or the player quit.
     * Also has a nested while loop that changes state based on who's turn it is - only changes if move is valid, otherwise prints error message and gives another chance.
     * @throws IOException
     */
    public static void playGame() throws IOException {
        Board board = createBoard();
        MyConnectFourImpl myConnectFour = new MyConnectFourImpl(board);
        ComputerPlayerInterface computerPlayerInterface = new ComputerPlayerInterfaceImpl(board, myConnectFour);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        loadStartingMessages();
        myConnectFour.printBoard();
        while(true){
            boolean isUser = true;
            while(isUser){
                String command = reader.readLine();
                if(command.contains("quit")){
                    //Exit code 0 for a graceful exit
                    System.out.println(EXIT_MESSAGE);
                    System.exit(0);
                }
                boolean isValid = myConnectFour.isValidInput(command);
                if (isValid){
                    String[] userMove = command.split(",");
                    Move move = new Move(Integer.parseInt(userMove[0]), Integer.parseInt(userMove[1]));
                    myConnectFour.editBoard(move, "r");
                    isUser = false;
                    myConnectFour.printBoard();
                }
                if (myConnectFour.isConnect4("r")){
                    System.out.println(WINNER_MESSAGE);
                    System.exit(0);
                }
            }
            while (!isUser){
                computerPlayerInterface.generateComputerMove();
                myConnectFour.printBoard();
                if (myConnectFour.isConnect4("y")){
                    System.out.println(LOSER_MESSAGE);
                    System.exit(0);
                }
                isUser = true;
            }
        }
    }

    /**
     * createBoard method iterates over the total number of rows and columns to create 42 elements.
     * For each element, a unique and unmodifiable position is allocated for row and column to form coordinates.
     * Once j has reached the max end of the column iteration loop, it assigns a double ended line space to close the board horizontally.
     * The board only needs to be created once but needs to be accessed by both interfaces used in this API, so created when playGame() is called then passed into their constructors
     */
    private static Board createBoard() {
        List<BoardElement> elements = new ArrayList<>();
        for (int i = 1; i <= ROW_SIZE ; i++) {
            for (int j = 1; j <= COLUMN_SIZE; j++) {
                BoardElement boardElement;
                if (j == 7){
                    boardElement = new BoardElement(j,i,END_PIECE);
                } else {
                    boardElement = new BoardElement(j,i,BOARD_PIECE);
                }
                elements.add(boardElement);
            }
        }
        return new Board(elements);
    }

    /**
     * Loads starting messages from the constants file for good readability and succinctness
     */
    private static void loadStartingMessages(){
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PLAYERS_INTRO);
        System.out.println(HOW_TO_PLAY);
        System.out.println(HOW_TO_PLAY_EXAMPLE);
        System.out.println(HOW_TO_WIN);
    }
}
