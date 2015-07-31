package com.company.Players;

import java.io.Serializable;
import java.util.Random;
import java.util.Stack;


public class Bot extends Player implements Serializable {
    private Stack success = new Stack();
    private Stack theory = new Stack();

    /**
     * Special local data class
     * for store XY coords in stack
     */
    class xy {
        public int x;
        public int y;

        xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Set ships by random method
     */
    public void setShips() {
        setRandomShips();
    }

    @Override
    public boolean move(Player who) {
        Random rnd = new Random();
        do {
            // if sea have not
            if (!who.sea.checkHealth()) {
                return false;
            }


            /**
             * if we have success attack and have not theory(idea) where is next point
             * and switched for count success attack
             */
            if (this.success.size() > 0 && this.theory.size() == 0) {
                xy xy1;
                xy xy2;
                switch (this.success.size()) {
                    case 1:
                        /**
                         * We find one point of ship
                         * need theory point for find them all
                         */
                        xy sxy = (xy) this.success.peek();
                        this.theory.push(new xy(sxy.x + 1, sxy.y));
                        this.theory.push(new xy(sxy.x - 1, sxy.y));
                        this.theory.push(new xy(sxy.x, sxy.y + 1));
                        this.theory.push(new xy(sxy.x, sxy.y - 1));
                        break;

                    case 2:
                        //get 2 last success attack
                        xy1 = (xy) this.success.pop();
                        xy2 = (xy) this.success.pop();
                        /**
                         * Find maximum of Y if x==x and add one
                         * find maximum of X if y==y and add one
                         */
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
                        //get back success attack
                        this.success.push(xy1);
                        this.success.push(xy2);
                        break;

                    case 3:
                        /**
                         *  Get 3 last success attack and find attack line for them
                         */
                        xy1 = (xy) this.success.pop();
                        xy2 = (xy) this.success.pop();
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
                            if (xy1.x > xy2.x && xy1.x > xy3.x) {
                                this.theory.push(new xy(xy1.x + 1, xy1.y));
                                if (xy2.x > xy3.x) {
                                    this.theory.push(new xy(xy3.x - 1, xy1.y));
                                } else {
                                    this.theory.push(new xy(xy2.x - 1, xy1.y));
                                }
                            } else {
                                if (xy2.x > xy1.x && xy2.x > xy3.x) {
                                    this.theory.push(new xy(xy2.x + 1, xy1.y));
                                    if (xy1.x > xy3.x) {
                                        this.theory.push(new xy(xy3.x - 1, xy1.y));
                                    } else {
                                        this.theory.push(new xy(xy1.x - 1, xy1.y));
                                    }
                                } else {
                                    this.theory.push(new xy(xy3.x + 1, xy1.y));
                                    if (xy1.x > xy2.x) {
                                        this.theory.push(new xy(xy2.x - 1, xy1.y));
                                    } else {
                                        this.theory.push(new xy(xy1.x - 1, xy1.y));
                                    }

                                }
                            }
                        }
                        //get back all success attack
                        this.success.push(xy1);
                        this.success.push(xy2);
                        this.success.push(xy3);
                        break;
                    case 4:
                        // if we have 4 success attack, we need clear all success and theory
                        this.success.clear();
                        this.theory.clear();
                        break;
                }
            }


            /**
             * Generation new xy coords, and check point
             * sea.check return equals sea.hit
             */
            int x;
            int y;
            do {
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
            } while (who.sea.check(x, y) == 2 || who.sea.check(x, y) == 4 || who.sea.check(x, y) == 5);

            //destroy point by coords
            int status = who.sea.hit(x, y);

            /**
             * what will be returned
             * true = retry
             * false = next player
             *
             * 1 - full health and it piece of ship
             * 2 - destroyed (health=false) and it piece of ship
             * 3 - full heal and it's simple point
             * 4 - it's destroyed a simple point
             * 5 - statement if ship is dead
             */
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

        }

        while (true);
    }
}


