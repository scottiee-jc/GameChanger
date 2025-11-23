package org.gamechanger;

import org.gamechanger.boardGameCommons.ComputerPlayerInterface;
import org.gamechanger.boardGameCommons.Move;
import org.gamechanger.connect4.constants.ConnectFourConstants;
import org.gamechanger.connect4.ConnectFourPlayerImpl;
import org.gamechanger.connect4.ConnectFourImpl;
import org.gamechanger.ticTacToe.TicTacToeImpl;
import org.gamechanger.utility.ConnectFourUtility;
import org.gamechanger.utility.TicTacToeUtility;

import java.io.BufferedReader;
import java.io.IOException;

import static org.gamechanger.connect4.constants.ConnectFourConstants.*;

public class GameImplementation implements GameInterface{
    ConnectFourConstants connectFourConstants;

    @Override
    public void playTicTacToe(BufferedReader reader, int players, TicTacToeImpl myTicTacToe) throws IOException {
        TicTacToeUtility.loadStartingMessages();

    }

    @Override
    public void playConnectFour(BufferedReader reader, int players, ConnectFourImpl myConnectFour) throws IOException {
        ConnectFourUtility.loadStartingMessages();
        System.out.println(myConnectFour.getBoard());

        if (players != 2){
            ComputerPlayerInterface computerPlayerInterface = new ConnectFourPlayerImpl(myConnectFour);
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
                        myConnectFour.getBoard().editBoard(move, "r");
                        isUser = false;
                        System.out.println(myConnectFour.getBoard());
                    }
                    if (myConnectFour.isConnect4("r")){
                        System.out.println(WINNER_MESSAGE);
                        System.exit(0);
                    }
                }
                while (!isUser){
                    computerPlayerInterface.generateComputerMove();
                    System.out.println(myConnectFour.getBoard());
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
