package com.company;

import com.company.Players.Player;

/**
 * Created by exfool on 28.07.15.
 */
public class GameController {
    private int score1;
    private int score2;
    private Player player1;
    private Player player2;
    private Player who = player1;
    private static GameController instance;
    private static int uniqId = 0;

    private GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = 0;
        this.score2 = 0;
    }

    public static int generateId(){
        return uniqId++;
    }

    public static GameController getInstance(Player player1, Player player2) {
        if (instance == null) {
            instance = new GameController(player1, player2);
        }
        return instance;
    }

    public void next() {
        if (who == player1) {
            who = player2;
        } else {
            who = player1;
        }
        who.move();
    }

    public void again() {
        who.move();
    }

    public int getScore1() {
        return score1;
    }

    private void addScore1(int score1) {
        this.score1 += score1;
    }

    public int getScore2() {
        return score2;
    }

    private void addScore2(int score2) {
        this.score2 += score2;
    }
}
