package org.gamechanger;

import org.gamechanger.connect4.ConnectFourImpl;
import org.gamechanger.ticTacToe.TicTacToeImpl;

import java.io.BufferedReader;
import java.io.IOException;

public interface GameInterface {

    void playTicTacToe(BufferedReader reader, int players, TicTacToeImpl myTicTacToe) throws IOException;
    void playConnectFour(BufferedReader reader, int players, ConnectFourImpl myConnectFour) throws IOException;

}
