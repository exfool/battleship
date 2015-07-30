package com.company.Players;

import com.company.Point;
import com.company.Sea;
import com.company.Ships;

/**
 * Created by exfool on 28.07.15.
 */
public abstract class Player {
    protected Ships[] ships;
    protected Point pieces;
    public Sea sea;

    public Player() {
        this.sea = new Sea();
        generateShips();
    }

    public abstract void setShips();

    /**
     * Step in game
     */
    public abstract boolean move();


    public Ships[] getShips() {
        return this.ships;
    }

    /**
     * Generation new ships for player
     */
    protected void generateShips() {
        ships = new Ships[10];

        ships[0] = new Ships(4);

        ships[1] = new Ships(3);
        ships[2] = new Ships(3);

        ships[3] = new Ships(2);
        ships[4] = new Ships(2);
        ships[5] = new Ships(2);

        ships[6] = new Ships(1);
        ships[7] = new Ships(1);
        ships[8] = new Ships(1);
        ships[9] = new Ships(1);

    }

}
