package com.company.Points;

import com.company.Ships;

import java.util.Objects;

/**
 * This a pieces of ship
 */
public class Piece implements Points {
    private int id;
    private Ships parent;
    private boolean status = true;
    private int numberPiece;

    public Piece(int id, Ships parent, int numberPiece) {
        setId(id);
        setParent(parent);
        setNumberPiece(numberPiece);
    }

    @Override
    public boolean check() {
        return status;
    }

    @Override
    public boolean kick() {
        if (status) {
            this.parent.hit();
            this.status = false;
            return true;
        }
        return false;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setParent(Ships parent) {
        this.parent = parent;
    }

    private void setNumberPiece(int numberPiece) {
        this.numberPiece = numberPiece;
    }
}
