package com.company;

/**
 * Created by exfool on 28.07.15.
 */
public class Ships {
    private int decks;
    private int health;

    public Ships(int decks) {
        this.decks = decks;
        this.health = decks;
    }

    public boolean hit() {
        if (this.health > 0) {
            this.health -= 1;
            return true;
        }
        return false;
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
}
