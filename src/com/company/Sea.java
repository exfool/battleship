package com.company;

/**
 * Created by exfool on 28.07.15.
 */
public class Sea {
    private Point[][] xy = new Point[12][12];


    /**
     * Set sheep on X, Y coordinate, if success return true
     * <p/>
     * first check for out of range X and Y
     * second check for lenght of ship and size sea
     * third ckeck for other ship on the way
     * <p/>
     * then set ship on the way and set point around them
     *
     * @param ship       placed in this coords
     * @param x          coordinate
     * @param y          coordinate
     * @param isVertical it set Vertical ?
     */
    public boolean setShip(Ships ship, int x, int y, boolean isVertical) {

        // Checking before set the ships

        // out of range
        if (x > 10 || y > 10 || x < 1 || y < 1) {
            Log.set("Out of range");
            return false;
        }

        // check lenght of ship and size sea
        if (isVertical) {
            if ((ship.getDecks() + x) > 10) {
                return false;
            }
        } else {
            if ((ship.getDecks() + y) > 10) {
                return false;
            }
        }

        // checking for other ship on the way
        for (int i = 0; i < ship.getDecks(); i++) {
            if (isVertical) {
                if (xy[x + i][y] != null) {
                    return false;
                }
            } else {
                if (xy[x][y + i] != null) {
                    return false;
                }
            }
        }

        //set the ship in this place
        for (int i = 0; i < ship.getDecks(); i++) {
            if (isVertical) {
                xy[x + i][y] = new Point(ship, i + 1);
            } else {
                xy[x][y + i] = new Point(ship, i + 1);
            }
        }

        //set points around the ship
        for (int i = -1; i < ship.getDecks() + 1; i++) {
            if (isVertical) {
                xy[x + i][y + 1] = new Point();
                xy[x + i][y - 1] = new Point();
            } else {
                xy[x - 1][y + i] = new Point();
                xy[x + 1][y + i] = new Point();
            }
        }
        if (isVertical) {
            xy[x - 1][y] = new Point();
            xy[x + ship.getDecks() ][y] = new Point();
        } else {
            xy[x][y - 1] = new Point();
            xy[x][y + ship.getDecks()+1] = new Point();
        }

        return true;
    }

    public int hit(int x, int y) {
        // out of range sea
        if (x > 11 || y > 11 || x < 1 || y < 1) {
            return -1;
        }

        return xy[x][y].hit();
    }

    public void showForOwner() {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if(xy[i][j] != null){xy[i][j].printForOwner();}
            }
            System.out.println();
        }
    }

    public void showForEnemy() {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if(xy[i][j] != null){xy[i][j].printForOwner();}
            }
            System.out.println();
        }
    }
}
