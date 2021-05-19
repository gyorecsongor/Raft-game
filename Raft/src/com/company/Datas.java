package com.company;
/**
 * Alap adatok.
 */
public class Datas {

    protected static final int SCREEN_WIDTH = 1000;
    protected static final int SCREEN_HEIGHT = 800;
    protected static final int UNIT_SIZE  = 25;
    protected static int[][] shipCoordinates = new int[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];
    protected static int[][] thingCoordinates = new int[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];
    protected static int barrelCount = 0;
    protected static int woodCount = 10;
    protected static int leafCount = 10;
    protected static int garbageCount = 10;
    protected static int potatoCount = 0;
    protected static int fishCount = 0;
    protected static int waterCount = 0;
    protected static int netCount = 0;
    protected static String spearCount = "nincs";
    protected static String firePlaceCount = "nincs";
    protected static String waterCleanerCount = "nincs";
    protected static boolean haveSpear = false;
    protected static boolean haveFirePlace = false;
    protected static boolean haveWaterCleaner = false;
    protected static int hungerCounter = 100;
    protected static int thirstCounter = 100;
    protected static int progressCounter = 0;
    protected static int potatoBakeProgressCounter = 0;
    protected static int fishBakeProgressCounter = 0;
    protected static int waterCleanerProgressCounter = 0;
    protected static int playerx = 500;
    protected static int playery = 350;
    protected static int sharkx = 100;
    protected static int sharky = 100;
    protected static int bakedPotato = 0;
    protected static int bakedFish = 0;
}
