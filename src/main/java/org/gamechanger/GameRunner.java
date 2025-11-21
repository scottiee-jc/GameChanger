package org.gamechanger;

import org.gamechanger.boardGameCommons.GameChoice;
import org.gamechanger.utility.ConnectFourUtility;
import org.gamechanger.connect4.ConnectFourImpl;
import org.gamechanger.ticTacToe.TicTacToeImpl;
import org.gamechanger.utility.TicTacToeUtility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.gamechanger.boardGameCommons.CommonConstants.*;

public class GameRunner {

    public static void main(String[] args){
        try {
            GameInterface gameInterface = new GameImplementation();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(WELCOME_TO_LANDING);
            System.out.println("1 = Connect Four");
            System.out.println("2 = Tic Tac Toe");
            int gameChoice = Integer.parseInt(reader.readLine());
            while (gameChoice > 2 || gameChoice < 1){
                System.out.printf(INVALID_GAME_CHOICE, gameChoice);
                gameChoice = Integer.parseInt(reader.readLine());
            }
            int players;
            if (gameChoice == GameChoice.CONNECT_FOUR.getValue()){
                System.out.printf(HOW_MANY_PLAYERS, GameChoice.CONNECT_FOUR.getName());
                players = Integer.parseInt(reader.readLine());
                System.out.printf(CREATING_GAME, GameChoice.CONNECT_FOUR.getName(), players);
                gameInterface.playConnectFour(reader, players, new ConnectFourImpl(ConnectFourUtility.createBoard()));
            } else {
                System.out.printf(HOW_MANY_PLAYERS, GameChoice.TIC_TAC_TOE.getName());
                players = Integer.parseInt(reader.readLine());
                System.out.printf(CREATING_GAME, GameChoice.TIC_TAC_TOE.getName(), players);
                gameInterface.playTicTacToe(reader, players, new TicTacToeImpl(TicTacToeUtility.createBoard()));
            }
        } catch (Exception e){
            System.out.println("Error occurred during game: " + e.getMessage());
        }
    }

}
