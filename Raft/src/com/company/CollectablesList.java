package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.company.Datas.*;

public class CollectablesList {
    static List<Collectables> items;
    /**
     * Anyag lista
     * Itt tárolódnak el az anyagok.
     */
    public CollectablesList() {
        items = new ArrayList<>();
    }
    /**
     * Anyag hozzáadása a listához.
     */
    public void addCollectables(int x, int y, char a) {
        Collectables in = new Collectables(x, y, a);
        items.add(in);
    }
    /**
     * Az anyagok pozícióját állítja át.
     */
    public void nextCollectables() {
        for (Collectables collectables : items) {
            collectables.setY();
        }
    }

    public int itemssize() {
        return items.size();
    }

    public List<Collectables> getList() {
        return items;
    }

    public int getX(int i) {
        return items.get(i).getX();
    }

    public int getY(int i) {
        return items.get(i).getY();
    }

    public char getResource(int i) {
        return items.get(i).getResource();
    }
    /**
     * Anyag megszerzése
     * Ha egy anyag 1 mező távolságra van a játékostól, azt automatikusan összegyűjti,
     * és hozzáírja az eddig megszerzett anyagokhoz.
     * A hordóban található 5 darab fa, levél, hulladék és burgonya,
     * véletlenszerűen, mindből egy darab, vagy akár egy darabból öt.
     * Megszerzés után eltűnik a képről a megszerzett anyag.
     */
    public static void acquireResource() {
        for (int i = 0; i < items.size(); i++) {
            int canCollectX = Math.abs(items.get(i).getX() - playerx);
            int canCollectY = Math.abs(items.get(i).getY() - playery);
            if (canCollectX <= UNIT_SIZE && canCollectY <= UNIT_SIZE) {
                if (items.get(i).getResource() == 'W') {
                    woodCount++;
                } else if (items.get(i).getResource() == 'G') {
                    garbageCount++;
                } else if (items.get(i).getResource() == 'L') {
                    leafCount++;
                } else if (items.get(i).getResource() == 'B') {
                    barrelCount++;
                    Random random = new Random();
                    for (int j = 0; j < 5; j++) {
                        int chanche = random.nextInt(4);
                        switch (chanche) {
                            case 0 -> woodCount++;
                            case 1 -> leafCount++;
                            case 2 -> garbageCount++;
                            case 3 -> potatoCount++;
                        }
                    }
                }
                items.remove(i);
            }
        }
    }
    /**
     * Anyag megszerzése hálóval
     * Ha egy anyag 1 mező távolságra van a hálótól, azt automatikusan összegyűjti,
     * és hozzáírja az eddig megszerzett anyagokhoz.
     * A hordóban található 5 darab fa, levél, hulladék és burgonya,
     * véletlenszerűen, mindből egy darab, vagy akár egy darabból öt.
     * Megszerzés után eltűnik a képről a megszerzett anyag.
     */
    public void netAcquireResource(int x, int y) {
        for (int i = 0; i < items.size(); i++) {
            int canNetCollectX = Math.abs(items.get(i).getX() - (x * UNIT_SIZE));
            int canNetCollectY = Math.abs(items.get(i).getY() - (y * UNIT_SIZE));
            if (canNetCollectX <= UNIT_SIZE && canNetCollectY <= UNIT_SIZE) {
                if (items.get(i).getResource() == 'W') {
                    woodCount++;
                } else if (items.get(i).getResource() == 'G') {
                    garbageCount++;
                } else if (items.get(i).getResource() == 'L') {
                    leafCount++;
                } else if (items.get(i).getResource() == 'B') {
                    barrelCount++;
                    Random random = new Random();
                    for (int j = 0; j < 5; j++) {
                        int chanche = random.nextInt(4);
                        switch (chanche) {
                            case 0 -> woodCount++;
                            case 1 -> leafCount++;
                            case 2 -> garbageCount++;
                            case 3 -> potatoCount++;
                        }
                    }
                }
                items.remove(i);
            }
        }
    }
}

