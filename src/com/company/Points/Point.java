package com.company.Points;

/**
 * Created by exfool on 28.07.15.
 */

import com.company.Points.Points;

/**
 * It's usual point in sea
 */
public class Point implements Points {
    private boolean status;

    public Point() {
        setStatus(true);
    }

    @Override
    public boolean check() {
        return status;
    }

    @Override
    public boolean kick() {
        if (status) {
            this.status = false;
            return true;
        }
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
