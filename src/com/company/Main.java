package com.company;


import com.company.Players.Bot;
import com.company.Players.User;

import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) {
        GameController game = GameController.getInstance();
        game.start();
    }
}
