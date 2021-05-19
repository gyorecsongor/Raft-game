package com.company;

import static com.company.Datas.*;

public class Collectables {
    protected int x;
    protected int y;
    protected char resource;
    /**
     * Anyagok tulajdonságai.
     */
    public Collectables(int x, int y, char resource) {
        this.x = x;
        this.y = y;
        this.resource = resource;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    /**
     * Az anyag haladása lefelé a vízben.
     */
    public void setY() {
        this.y += UNIT_SIZE;
    }

    public char getResource() {
        return resource;
    }
}



