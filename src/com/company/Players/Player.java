package com.company.Players;

import com.company.Points.Piece;
import com.company.Sea;
import com.company.Ships;

/**
 * Created by exfool on 28.07.15.
 */
public abstract class Player {
    private Ships[] listShips;
    private Piece pieces;
    public Sea sea;

    public Player() {
        this.sea = new Sea();
        generateShips();
    }

    public void move() {
        /**
         * Say to user or to bot thay he need to make step
         */
    }

    public boolean ckeck() {
        /**
         * This method checked owner sea, then you getting attack
         */
        return true;
    }

    public Ships[] getListShips(){
        return this.listShips;
    }

    /**
     * Generation new ships for player
     */
    private void generateShips() {
        listShips = new Ships[10];

        listShips[0] = new Ships(4);

        listShips[1] = new Ships(3);
        listShips[2] = new Ships(3);

        listShips[3] = new Ships(2);
        listShips[4] = new Ships(2);
        listShips[5] = new Ships(2);

        listShips[6] = new Ships(1);
        listShips[7] = new Ships(1);
        listShips[8] = new Ships(1);
        listShips[9] = new Ships(1);

    }

}
