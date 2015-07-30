package com.company;

import com.company.Players.Bot;
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

    public static int generateId() {
        return uniqId++;
    }

    private GameController() {
        this.player1 = new Bot();
        this.player2 = new Bot();

        this.player1.setShips();
        this.player2.setShips();
        who = this.player1;

        this.score1 = 0;
        this.score2 = 0;
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void start() {
        int c = 0;
        while (true) {
            if (!chagnePlayer().sea.checkHealth() || !this.who.sea.checkHealth()) {
                finish();
                break;
            }

            boolean flag = who.move( chagnePlayer());
            if (!flag) {
                who = chagnePlayer();
                c++;
                Log.set("Sea of 1 player");
                player1.sea.showForOwner();
                Log.set("Sea of 2 player");
                player2.sea.showForOwner();
                System.out.println("Count of interaction: " + c);
            }

        }
    }

    private void finish() {
        if (player1.sea.checkHealth() && !player2.sea.checkHealth()) {
            Log.set("Winner is Player1");
        }
        if (!player1.sea.checkHealth() && player2.sea.checkHealth()) {
            Log.set("Winner is Player2");
        }
        if (player1.sea.checkHealth() && player2.sea.checkHealth()) {
            Log.set("Friendship won, joke, nobody no won.");
        }
    }

    public Player chagnePlayer() {
        if (who != player1) {
            return player1;
        } else {
            return player2;
        }
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
