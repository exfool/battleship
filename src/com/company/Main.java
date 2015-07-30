package com.company;


import com.company.Players.Bot;
import com.company.Players.User;

public class Main {

    public static void main(String[] args) {
        GameController game = GameController.getInstance();
        game.start();
    }
}
