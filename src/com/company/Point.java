package com.company;

/**
 * Conrain two statement
 * 1 - simple point
 * 2 - piece of ship
 */
public class Point {
    private boolean health = true;
    public Ships parent;
    private int numberPiece;

    public Point() {
    }

    public Point(Ships ship, int numberPiece) {
        this.health = true;
        this.parent = ship;
        this.numberPiece = numberPiece;
    }

    /**
     * The contain 4 statement
     * Return statement was before action
     *
     * 1 - full health and it piece of ship
     * 2 - destroyed (health=false) and it piece of ship
     * <p/>
     * 3 - full heal and it's simple point
     * 4 - it's destroyed a simple point
     * 5 - statement if ship is dead
     **/
    public int hit() {
        if (parent != null) {
            if (health) {
                parent.hit();
                health = false;
                return 1;
            } else {
                if (parent.check()) {
                    return 2;
                }else {
                    return 5;
                }
            }
        } else {
            if (health) {
                health = false;
                return 3;
            } else {
                return 4;
            }
        }
    }

    /**
     * Return value equals hit, but it only check them
     *
     * @return equals method hit
     */
    public int check() {
        if (parent != null) {

            if (health) {
                return 1;
            } else {
                if (parent.check()) {
                    return 2;
                }else {
                    return 5;
                }
            }

        } else {

            if (health) {
                return 3;
            } else {
                return 4;
            }

        }
    }

    public void printForOwner() {
        switch (this.check()) {
            case 1:
                System.out.print("0 ");
                break;
            case 2:
                System.out.print("x ");
                break;
            case 3:
                System.out.print("- ");
                break;
            case 4:
                System.out.print("* ");
                break;
            case 5:
                System.out.print("o ");
                break;
        }
    }

    public void printForEnemy() {
        switch (this.check()) {
            case 1:
                System.out.print("- ");
                break;
            case 2:
                System.out.print("x ");
                break;
            case 3:
                System.out.print("- ");
                break;
            case 4:
                System.out.print("* ");
                break;
            case 5:
                System.out.print("o ");
                break;
        }
    }
}
