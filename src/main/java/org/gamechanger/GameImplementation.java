package org.gamechanger;

import org.gamechanger.boardGameCommons.ComputerPlayerInterface;
import org.gamechanger.boardGameCommons.Move;
import org.gamechanger.connect4.constants.ConnectFourConstants;
import org.gamechanger.connect4.ConnectFourPlayerInterfaceImpl;
import org.gamechanger.connect4.ConnectFourImpl;
import org.gamechanger.ticTacToe.TicTacToeImpl;

import java.io.BufferedReader;
import java.io.IOException;

import static org.gamechanger.utility.ConnectFourUtility.loadStartingMessages;
import static org.gamechanger.connect4.constants.ConnectFourConstants.*;

public class GameImplementation implements GameInterface{
    ConnectFourConstants connectFourConstants;

    @Override
    public void playTicTacToe(BufferedReader reader, int players, TicTacToeImpl myTicTacToe) throws IOException {

    }

    @Override
    public void playConnectFour(BufferedReader reader, int players, ConnectFourImpl myConnectFour) throws IOException {
        loadStartingMessages();
        myConnectFour.printBoard();

        if (players != 2){
            ComputerPlayerInterface computerPlayerInterface = new ConnectFourPlayerInterfaceImpl(myConnectFour);
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

    }


}
