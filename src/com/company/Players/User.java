package com.company.Players;

import com.company.Players.Player;
import com.company.Ships;

import java.util.Scanner;

/**
 * Created by exfool on 28.07.15.
 */
public class User extends Player {

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
                switch (in.nextInt()){
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
    }

    public boolean move(){
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("Enter X:");
            int x = in.nextInt();
            System.out.println("Enter Y:");
            int y = in.nextInt();

            int status = super.sea.hit(x, y);
            if (status == 1) {
                return true;
            }
            if(status != -1){
                return false;
            }
        }while (true);
    }
}
