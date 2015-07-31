package com.company;

import java.io.Serializable;

/**
 * Created by exfool on 28.07.15.
 */
public class Sea implements Serializable {
    private Point[][] xy = new Point[12][12];
    private int health = 20;

    public Sea() {
    }

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
            //Log.set("Out of range");
            return false;
        }

        // check lenght of ship and size sea
        if (isVertical) {
            if ((ship.getDecks() + x) > 11) {
                return false;
            }
        } else {
            if ((ship.getDecks() + y) > 11) {
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
        Point around;
        for (int i = -1; i < ship.getDecks() + 1; i++) {
            if (isVertical) {
                if (xy[x + i][y + 1] == null) {
                    around = new Point();
                    ship.addAround(around);
                    xy[x + i][y + 1] = around;
                } else {
                    ship.addAround(xy[x + i][y + 1]);
                }

                if (xy[x + i][y - 1] == null) {
                    around = new Point();
                    ship.addAround(around);
                    xy[x + i][y - 1] = around;
                } else {
                    ship.addAround(xy[x + i][y - 1]);
                }
            } else {
                if (xy[x - 1][y + i] == null) {
                    around = new Point();
                    ship.addAround(around);
                    xy[x - 1][y + i] = around;
                } else {
                    ship.addAround(xy[x - 1][y + i]);
                }

                if (xy[x + 1][y + i] == null) {
                    around = new Point();
                    ship.addAround(around);
                    xy[x + 1][y + i] = around;
                } else {
                    ship.addAround(xy[x + 1][y + i]);
                }
            }
        }
        if (isVertical) {
            if (xy[x - 1][y] == null) {
                around = new Point();
                ship.addAround(around);
                xy[x - 1][y] = around;
            } else {
                ship.addAround(xy[x - 1][y]);
            }

            if (xy[x + ship.getDecks()][y] == null) {
                around = new Point();
                ship.addAround(around);
                xy[x + ship.getDecks()][y] = around;
            } else {
                ship.addAround(xy[x + ship.getDecks()][y]);
            }
        } else {
            if (xy[x][y - 1] == null) {
                around = new Point();
                ship.addAround(around);
                xy[x][y - 1] = around;
            } else {
                ship.addAround(xy[x][y - 1]);
            }

            if (xy[x][y + ship.getDecks()] == null) {
                around = new Point();
                ship.addAround(around);
                xy[x][y + ship.getDecks()] = around;
            } else {
                ship.addAround(xy[x][y + ship.getDecks()]);
            }
        }

        return true;
    }

    public void initPoints() {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (xy[i][j] == null) {
                    xy[i][j] = new Point();
                }
            }
        }
    }

    public int hit(int x, int y) {
        // out of range sea
        if (x > 11 || y > 11 || x < 1 || y < 1) {
            return -1;
        }

        return xy[x][y].hit(this);
    }

    public int check(int x, int y) {
        // out of range sea
        if (x > 11 || y > 11 || x < 1 || y < 1) {
            return -1;
        }

        return xy[x][y].check();
    }

    public void showForOwner() {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for (int i = 1; i < 11; i++) {
            if (i != 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
            for (int j = 1; j < 11; j++) {
                if (xy[i][j] != null) {
                    xy[i][j].printForOwner();
                } else {
                    System.out.print("/ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showForEnemy() {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for (int i = 1; i < 11; i++) {
            if (i != 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
            ;
            for (int j = 1; j < 11; j++) {
                if (xy[i][j] != null) {
                    xy[i][j].printForEnemy();
                } else {
                    System.out.print("/ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean checkHealth() {
        if (this.health > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void mHealth() {
        this.health -= 1;
    }
}
