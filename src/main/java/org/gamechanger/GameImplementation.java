package org.gamechanger;

import org.gamechanger.boardGameCommons.Move;
import org.gamechanger.connect4.ConnectFourConstants;
import org.gamechanger.connect4.ConnectFourImpl;
import org.gamechanger.ticTacToe.TicTacToeImpl;
import org.gamechanger.boardGameCommons.GameUtility;

import java.io.BufferedReader;
import java.io.IOException;

import static org.gamechanger.connect4.ConnectFourConstants.*;

public class GameImplementation implements GameRunnerService {
    ConnectFourConstants connectFourConstants;

    @Override
    public void playTicTacToe(BufferedReader reader, int players, TicTacToeImpl myTicTacToe) throws IOException {
        GameUtility.loadTicTacToeStartingMessages();

    }

    @Override
    public void playConnectFour(BufferedReader reader, int players, ConnectFourImpl myConnectFour) throws IOException {
        GameUtility.loadConnectFourStartingMessages();
        System.out.println(myConnectFour.board());

        if (players != 2){
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
                        myConnectFour.board().editBoard(move, "r");
                        isUser = false;
                        System.out.println(myConnectFour.board());
                    }
                    if (myConnectFour.isVictorious("r")){
                        System.out.println(WINNER_MESSAGE);
                        System.exit(0);
                    }
                }
                while (!isUser){
                    myConnectFour.generateComputerMove();
                    System.out.println(myConnectFour.board());
                    if (myConnectFour.isVictorious("y")){
                        System.out.println(LOSER_MESSAGE);
                        System.exit(0);
                    }
                    isUser = true;
                }
            }
        }
    }

}
