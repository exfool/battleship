package com.company.Points;

import java.util.Map;

/**
 * Created by exfool on 28.07.15.
 */
public interface Points{
    /**
     * Checking point, it can be live or destroyed
     * @return true or false
     */
    public boolean check();

    /**
     * Destroy the point
     * @return false if it destroyed already
     */
    public boolean kick();

}
