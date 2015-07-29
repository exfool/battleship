package com.company;

import com.company.Points.Piece;

/**
 * Created by exfool on 28.07.15.
 */
public class Ships {
    private int decks;
    public Piece[] pieces;
    private int health;

    public Ships(int decks) {
        this.decks = decks;
        // init pieces of ship
        pieces = new Piece[decks];
        for (int i = 0; i < decks; i++) {
            pieces[i] = new Piece(GameController.generateId(), this, i);
        }
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

    public Piece getPiece(int number) {
        return pieces[number];
    }

    public int getDecks() {
        return this.decks;
    }
}
