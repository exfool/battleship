package com.company;

import com.company.Players.Bot;
import com.company.Players.Player;
import com.company.Players.User;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by exfool on 28.07.15.
 */
public class GameController implements Serializable {
    private Player player1;
    private Player player2;
    private Player who = player1;
    private static GameController instance;
    private static int uniqId = 0;
    int steps = 0;

    public static int generateId() {
        return uniqId++;
    }

    private GameController() {
        this.player1 = null;
        this.player2 = null;
        this.who = null;
        this.steps = 0;
        Scanner in = new Scanner(System.in);
        Log.set("1 - new game");
        Log.set("2 - continue game");
        switch (in.nextInt()) {
            case 1:
                Log.set("");
                Log.set("1 - bot vs bot");
                Log.set("1 - bot vs you");
                switch (in.nextInt()) {
                    case 1:
                        this.player1 = new Bot();
                        this.player2 = new Bot();
                        this.who = this.player1;
                        this.player1.setShips();
                        this.player2.setShips();
                        break;
                    case 2:
                        this.player1 = new Bot();
                        this.player2 = new User();
                        this.player1.setShips();
                        Log.set("Do you want automaticly set ship (1 - yes, 2 - no) ?");
                        if (in.nextInt() == 1) {
                            this.player2.setRandomShips();
                        } else {
                            this.player2.setShips();
                        }
                        break;
                }
                who = this.player1;
                break;
            case 2:
                break;
        }
        start();
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void start() {
        while (true) {
            if (!chagnePlayer().sea.checkHealth() || !this.who.sea.checkHealth()) {
                finish();
                break;
            }

            boolean flag = who.move(chagnePlayer());
            if (!flag) {
                who = chagnePlayer();
                steps++;
                Log.set("Sea of 1 player");
                player1.sea.showForEnemy();
                Log.set("Sea of 2 player");
                player2.sea.showForOwner();
                System.out.println("Count of interaction: " + steps);
            }

        }
    }

    private void finish() {
        Log.set("");
        Log.set("Sea of first player");
        player1.sea.showForOwner();
        Log.set("Sea of second player");
        player2.sea.showForOwner();
        System.out.println("Count of interaction: " + steps);
        if (player1.sea.checkHealth() && !player2.sea.checkHealth()) {
            Log.set("Winner is Player1");
        }
        if (!player1.sea.checkHealth() && player2.sea.checkHealth()) {
            Log.set("Winner is Player2");
        }
        if (player1.sea.checkHealth() && player2.sea.checkHealth()) {
            Log.set("Friendship won, joke, nobody no won.");
        }

        Log.set("Do you want create new game? ( 1 - yes, 2 - no )");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 1:
                GameController.instance = new GameController();
                break;
            case 2:
                Log.set("Thank you");
                break;
        }
    }

    public Player chagnePlayer() {
        if (who != player1) {
            return player1;
        } else {
            return player2;
        }
    }


}
