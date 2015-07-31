package com.company;

import java.io.Serializable;

/**
 * Created by exfool on 28.07.15.
 */
public class Log implements Serializable {
    public static void set(String msg) {
        System.out.println(msg);
    }
}
