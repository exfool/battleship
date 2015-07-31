package com.company.Players;

import com.company.Sea;
import com.company.Ships;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by exfool on 28.07.15.
 */
public abstract class Player implements Serializable {
    protected Ships[] ships;
    public Sea sea;

    public Player() {
        this.sea = new Sea();
        generateShips();
    }
    public void setShips(){};
        public void setRandomShips() {
        for (Ships ship : ships) {
            boolean flag;
            Random rnd = new Random();
            do {
                int x = rnd.nextInt(10) + 1;
                int y = rnd.nextInt(10) + 1;
                boolean isVertical = rnd.nextBoolean();
                flag = sea.setShip(ship, y, x, isVertical);
            } while (!flag);
        }
        sea.initPoints();
        sea.showForOwner();
    }
    /**
     * Step in game
     */
    public abstract boolean move(Player who);


    public Ships[] getShips() {
        return this.ships;
    }

    /**
     * Generation new ships for player
     */
    protected void generateShips() {
        ships = new Ships[10];

        ships[0] = new Ships(sea, 4);

        ships[1] = new Ships(sea, 3);
        ships[2] = new Ships(sea, 3);

        ships[3] = new Ships(sea, 2);
        ships[4] = new Ships(sea, 2);
        ships[5] = new Ships(sea, 2);

        ships[6] = new Ships(sea, 1);
        ships[7] = new Ships(sea, 1);
        ships[8] = new Ships(sea, 1);
        ships[9] = new Ships(sea, 1);

    }

}
