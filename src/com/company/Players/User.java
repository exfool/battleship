package com.company.Players;

import com.company.GameController;
import com.company.Log;
import com.company.Ships;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by exfool on 28.07.15.
 */
public class User extends Player implements Serializable {

    public User() {
        super();
    }

    @Override
    public void setShips() {
        Scanner in = new Scanner(System.in);
        for (Ships ship : ships) {
            boolean flag;
            do {
                sea.showForOwner();
                System.out.println("Set ship of " + ship.getDecks() + " desk ");
                System.out.println("Enter x:");
                int x = in.nextInt();
                System.out.println("Enter y:");
                int y = in.nextInt();
                System.out.println("Enter is position ship (1-vertical, 2- horizontal):");
                boolean isVertical;
                switch (in.nextInt()) {
                    case 1:
                        isVertical = true;
                        break;
                    default:
                        isVertical = false;
                        break;
                }
                flag = sea.setShip(ship, y, x, isVertical);
            } while (!flag);
        }
        sea.initPoints();
    }

    private int inputWithSave() throws IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        String so = in.next();
        if ( so.equals("save")) {
            GameController.save();
        }
        return Integer.valueOf(so);
    }

    public boolean move(Player who) throws IOException, ClassNotFoundException {
        do {
            Log.set("Sea of 1 player");
            who.sea.showForOwner();
            System.out.println("Enter X:");
            int x = inputWithSave();
            System.out.println("Enter Y:");
            int y = inputWithSave();

            int status = who.sea.hit(y, x);
            switch (status) {
                case -1:
                case 2:
                case 4:
                case 5:
                case 1:
                    return true;
                case 3:
                    return false;
            }
        } while (true);
    }
}
