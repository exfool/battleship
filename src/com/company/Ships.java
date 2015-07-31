package com.company;

import java.io.Serializable;

/**
 * Created by exfool on 28.07.15.
 */
public class Ships implements Serializable {
    private int decks;
    private int health;
    private Point[] around;
    private int countAround = 0;
    private Sea sea;

    public Ships(Sea sea, int decks) {
        this.decks = decks;
        this.health = decks;
        this.sea = sea;
        around = new Point[6 + 2 * decks];
    }

    public boolean hit() {
        this.health -= 1;
        if (this.health <= 0) {
            for (int i = 0; i < countAround; i++)
                around[i].hit(this.sea);
            return false;
        }
        return true;
    }

    public boolean check() {
        if (this.health > 0) {
            return true;
        }
        return false;
    }

    public int getDecks() {
        return this.decks;
    }

    public void addAround(Point point) {
        around[countAround++] = point;
    }
}
