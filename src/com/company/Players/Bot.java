package com.company.Players;

import com.company.Ships;

import java.util.Random;
import java.util.Stack;


/**
 * * Created by exfool on 28.07.15.
 */


public class Bot extends Player {
    private Stack success = new Stack();
    private Stack theory = new Stack();

    class xy {
        public int x;
        public int y;

        xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void setShips() {
        for (Ships ship : ships) {
            boolean flag;
            Random rnd = new Random();
            do {
                int x = rnd.nextInt(10) + 1;
                int y = rnd.nextInt(10) + 1;
                boolean isVertical = rnd.nextBoolean();
                flag = sea.setShip(ship, y, x, isVertical);
            } while (!flag);
        }
        sea.initPoints();
        sea.showForOwner();
    }

    @Override
    public boolean move(Player who) {
        Random rnd = new Random();
        do {
            if (!who.sea.checkHealth()) {
                return false;
            }

            if (this.success.size() > 0 && this.theory.size() == 0) {
                switch (this.success.size()) {
                    case 1:
                        xy sxy = (xy) this.success.peek();
                        this.theory.push(new xy(sxy.x + 1, sxy.y));
                        this.theory.push(new xy(sxy.x - 1, sxy.y));
                        this.theory.push(new xy(sxy.x, sxy.y + 1));
                        this.theory.push(new xy(sxy.x, sxy.y - 1));
                        break;

                    case 2:
                        xy xy1 = (xy) this.success.pop();
                        xy xy2 = (xy) this.success.pop();
                        if (xy1.x == xy2.x) {
                            if (xy1.y > xy2.y) {
                                this.theory.push(new xy(xy1.x, xy2.y - 1));
                                this.theory.push(new xy(xy1.x, xy1.y + 1));
                            } else {
                                this.theory.push(new xy(xy1.x, xy2.y + 1));
                                this.theory.push(new xy(xy1.x, xy1.y - 1));
                            }
                        } else {
                            if (xy1.x > xy2.x) {
                                this.theory.push(new xy(xy1.x + 1, xy1.y));
                                this.theory.push(new xy(xy2.x - 1, xy1.y));
                            } else {
                                this.theory.push(new xy(xy1.x - 1, xy1.y));
                                this.theory.push(new xy(xy2.x + 1, xy1.y));
                            }
                        }
                        this.success.push(xy1);
                        this.success.push(xy2);
                        break;

                    case 3:
                        xy xy1 = (xy) this.success.pop();
                        xy xy2 = (xy) this.success.pop();
                        xy xy3 = (xy) this.success.pop();

                        if (xy1.x == xy2.x && xy1.x == xy3.x) {
                            if (xy1.y > xy2.y && xy1.y > xy3.y) {
                                this.theory.push(new xy(xy1.x, xy1.y + 1));
                                if (xy2.y > xy3.y) {
                                    this.theory.push(new xy(xy1.x, xy3.y - 1));
                                } else {
                                    this.theory.push(new xy(xy1.x, xy2.y - 1));
                                }
                            } else {
                                if (xy2.y > xy1.y && xy2.y > xy3.y) {
                                    this.theory.push(new xy(xy1.x, xy2.y + 1));
                                    if (xy1.y > xy3.y) {
                                        this.theory.push(new xy(xy1.x, xy3.y - 1));
                                    } else {
                                        this.theory.push(new xy(xy1.x, xy1.y - 1));
                                    }
                                } else {
                                    this.theory.push(new xy(xy1.x, xy3.y + 1));
                                    if (xy1.y > xy2.y) {
                                        this.theory.push(new xy(xy1.x, xy2.y - 1));
                                    } else {
                                        this.theory.push(new xy(xy1.x, xy1.y - 1));
                                    }
                                }
                            }
                        } else {
                            if (xy1.x > xy2.x && xy1.x > xy3.x){
                                this.theory.push(new xy(xy1.x +1, xy1.y));
                                if(xy2.x>xy3.x){
                                    this.theory.push(new xy(xy1.x +1, xy1.y));
                                }else{

                                }

                            }else{

                            }

                        }

                        this.success.push(xy1);
                        this.success.push(xy2);
                        this.success.push(xy3);
                        break;
                    case 4:
                        this.success.clear();
                        this.theory.clear();
                        break;
                }
            }

            int x;
            int y;
            if (this.success.size() == 0) {
                x = rnd.nextInt(10) + 1;
                y = rnd.nextInt(10) + 1;
            } else {
                if (this.theory.size() > 0) {
                    xy sxy = (xy) this.theory.pop();
                    x = sxy.x;
                    y = sxy.y;
                } else {
                    this.success.clear();
                    x = rnd.nextInt(10) + 1;
                    y = rnd.nextInt(10) + 1;
                }
            }
            int status = who.sea.hit(x, y);

/**
 * 1 - full health and it piece of ship
 * 2 - destroyed (health=false) and it piece of ship
 * 3 - full heal and it's simple point
 * 4 - it's destroyed a simple point
 * 5 - statement if ship is dead
 **/
            switch (status) {
                case -1:
                case 2:
                case 4:
                    return true;
                case 5:
                    this.success.clear();
                    this.theory.clear();
                    return true;
                case 1:
                    this.success.push(new xy(x, y));
                    this.theory.clear();
                    return true;
                case 3:
                    return false;
            }

        } while (true);
    }
}


