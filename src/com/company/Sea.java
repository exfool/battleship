package com.company;

import com.company.Points.Piece;
import com.company.Points.Points;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by exfool on 28.07.15.
 */
public class Sea {
    private Points[][] xy = new Points[10][10];
    private int generalHealthl = 20;

    public void showForOwner() {
        for(int i= 0; i<10; i++){
            for (int j=0; j<10; j++){
                System.out.print("- ");
            }
            System.out.println();
        }
    }

    public void showForEnemy() {
    }

    /**
     * Set sheep on X, Y coordinate
     * @param ship
     * @param x coordinate
     * @param y coordinate
     * @param isVertical it set Vertical ?
     */
    public void setShips(Ships ship, int x, int y, boolean isVertical) {
        for (int i = 0; i < ship.getDecks(); i++) {
            if (isVertical) {
                xy[x+i][y] = ship.getPiece(i);
            } else {
                xy[x][y+i] = ship.getPiece(i);
            }
        }
    }
}
