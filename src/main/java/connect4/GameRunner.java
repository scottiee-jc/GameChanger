package connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static connect4.ConnectFourConstants.EXIT_MESSAGE;

public class GameRunner {

    static MyConnectFourImpl myConnectFour = new MyConnectFourImpl();


    public static void main(String[] args){
        try {
            playGame();
        } catch (Exception e){
            System.out.println("Error ");
        }
    }

    public static void playGame() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        loadStartingMessages();
        myConnectFour.createBoard();
        myConnectFour.printBoard();

        boolean win = false;

        while(!win){
//            boolean isUser = true;
//            while(isUser){
                String command = reader.readLine();
                if(command.contains("quit")){
                    //Exit code 0 for a graceful exit
                    System.out.println(EXIT_MESSAGE);
                    System.exit(0);
                }
                boolean isValid = myConnectFour.isValidInput(command);
                if (isValid){
                    myConnectFour.editBoard(command, "r");
                }
                myConnectFour.printBoard();
//                isUser = false;
//            }
//            while (!isUser){
//
//            }

        }
    }


    private static void loadStartingMessages(){
        System.out.println(ConnectFourConstants.WELCOME_MESSAGE);
        System.out.println(ConnectFourConstants.PLAYERS_INTRO);
        System.out.println(ConnectFourConstants.HOW_TO_PLAY);
        System.out.println(ConnectFourConstants.HOW_TO_PLAY_EXAMPLE);
        System.out.println(ConnectFourConstants.HOW_TO_WIN);
    }
}
