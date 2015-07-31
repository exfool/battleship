package com.company;

import com.company.Players.Bot;
import com.company.Players.Player;
import com.company.Players.User;

import java.io.*;
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

    private GameController() throws IOException, ClassNotFoundException {
        this.player1 = null;
        this.player2 = null;
        this.who = null;
        this.steps = 0;
        Scanner in = new Scanner(System.in);
        Log.set("1 - new game");
        Log.set("2 - exit");
        switch (in.nextInt()) {
            case 1:
                Log.set("");
                Log.set("1 - bot vs bot");
                Log.set("2 - bot vs you");
                if (in.nextInt() == 1) {
                    this.player1 = new Bot();
                    this.player2 = new Bot();
                    this.who = this.player1;
                    this.player1.setShips();
                    this.player2.setShips();
                } else {
                    this.player1 = new Bot();
                    this.player2 = new User();
                    this.player1.setShips();
                    Log.set("Do you want automaticly set ship (1 - yes, 2 - no) ?");
                    if (in.nextInt() == 1) {
                        this.player2.setRandomShips();
                    } else {
                        this.player2.setShips();
                    }
                }
                who = this.player1;
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

    public static GameController getInstance() throws IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.printf("Do you want continue game ? ( 1 - yes, 2 - no )");
        if (in.nextInt() == 1) {
            recovery();
        }else {
            if (GameController.instance == null) {
                GameController.instance = new GameController();
            }
        }

        return GameController.instance;
    }

    public static GameController recovery() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("save.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        GameController.instance = (GameController) oin.readObject();
        fis.close();
        oin.close();
        return GameController.instance;
    }

    public static void save() throws IOException, ClassNotFoundException {
        if (GameController.instance == null) {
            Log.set("You don't have instance.");
        } else {
            FileOutputStream fos = new FileOutputStream("save.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(GameController.instance);
            oos.flush();
            fos.close();
            oos.close();
            Log.set("Game is saved");
            System.exit(0);
        }
    }

    public void start() throws IOException, ClassNotFoundException {
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

    private void finish() throws IOException, ClassNotFoundException {
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
                start();
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
