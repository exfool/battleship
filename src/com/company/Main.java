package com.company;

import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameController game = GameController.getInstance();
        game.start();
    }
}
