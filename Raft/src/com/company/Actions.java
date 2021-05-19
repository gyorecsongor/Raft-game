package com.company;

import java.util.Random;

import static com.company.Datas.*;
import static com.company.GamePanel.*;

public class Actions {
    /**
     * Éhség-Szomjúság folyamatábra
     * Lépésenként csökkenő éhség, és szomjúság, ameddig valamelyik nem éri el a 0-át.
     */
    public static void fill(){
        if (hungerCounter >= 0 || thirstCounter >= 0) {
            hunger.setValue(hungerCounter);
            thirst.setValue(thirstCounter);
            thirstCounter -= 1;
            hungerCounter -= 1;
        }
    }
    /**
     * Csónak növelése
     * Elegendő anyag(2 fa,2 levél) és megfelelő pozícióban(1 távolság a hajótól) egy kockával lehet növelni a csónakot.
     */
    public static void makeBiggerShip(){
        if (woodCount > 1 && leafCount > 1){
            int tempPlayerX = playerx / UNIT_SIZE;
            int tempPlayerY = playery / UNIT_SIZE;
            if (shipCoordinates[tempPlayerX - 1][tempPlayerY] == 1 || shipCoordinates[tempPlayerX + 1][tempPlayerY] == 1 || shipCoordinates[tempPlayerX][tempPlayerY - 1] == 1 || shipCoordinates[tempPlayerX][tempPlayerY + 1] == 1){
                woodCount -= 2;
                leafCount -= 2;
                shipCoordinates[playerx / UNIT_SIZE][playery / UNIT_SIZE] = 1;
            }
        }
    }
    /**
     * Burgonya sütési idő
     * 25 cselekvést számol, majd burgonyát süt.
     */
    public static void potatoBaking(){
        potatoBakeProgressCounter++;
        if(potatoBakeProgressCounter == 25){
            bakePotato();
        }
    }
    /**
     * Burgonya sütése
     * Elegendő anyag(1 burgonya) és megfelelő eszközzel(tűzhely) egy burgonyát lehet sütni 25 cselekvés alatt.
     */
    public static void bakePotato(){
        if (haveFirePlace){
            if (potatoCount > 0){
                potatoCount--;
                if (potatoBakeProgressCounter == 25){
                    bakedPotato++;
                }
            }
        }
    }
    /**
     * Hal sütési idő
     * 25 cselekvést számol, majd halat süt.
     */
    public static void fishBaking(){
       fishBakeProgressCounter++;
        if(fishBakeProgressCounter == 25){
            bakeFish();
        }
    }
    /**
     * Hal sütése
     * Elegendő anyag(1 hal) és megfelelő eszközzel(tűzhely) egy halat lehet sütni 25 cselekvés alatt.
     */
    public static void bakeFish(){
        if (haveFirePlace){
            if (fishCount > 0){
                fishCount--;
                if (fishBakeProgressCounter == 25){
                    bakedFish++;
                }
            }
        }
    }
    /**
     * Víztiszítási idő
     * 40 cselekvést számol, majd vizet tisztít.
     */
    public static void waterCleaning(){
        waterCleanerProgressCounter++;
        if(waterCleanerProgressCounter == 40){
            cleanWater();
        }
    }
    /**
     * Víz tisztítása
     * Megfelelő eszközzel(víztiszító) vizet lehet tisztítani 40 cselekvés alatt.
     */
    public static void cleanWater(){
        if (haveWaterCleaner){
            if (waterCleanerProgressCounter == 25){
                waterCount++;
            }
        }
    }
    /**
     * Burgonya evés
     * Elegendő anyagal(1 sült burgonya) egy burgonyát lehet enni,
     * ami 60-al növeli éhségünk.
     */
    public static void eatPotato(){
        if (bakedPotato > 0){
            bakedPotato--;
            hungerCounter += 60;
            if (hungerCounter > 100){
                hungerCounter = 100;
            }
        }
    }
    /**
     * Halászat
     * A vízben állva lehet halászni, 25% esély van a sikeres fogásra, amivel egy darab halat kapunk.
     */
    public static void fishing(){
        int tempPlayerX = playerx / UNIT_SIZE;
        int tempPlayerY = playery / UNIT_SIZE;
        if (!(shipCoordinates[tempPlayerX][tempPlayerY] == 1)){
        Random random = new Random();
        int chance = random.nextInt(4);
        if (chance == 1) {
            fishCount++;
            }
        }
    }
    /**
     * Hal evés
     * Elegendő anyagal(1 sült hal) egy halat lehet enni,
     * ami 60-al növeli éhségünk.
     */
    public static void eatFish(){
        if (fishCount > 0){
            fishCount--;
            hungerCounter += 60;
            if (hungerCounter > 100){
                hungerCounter = 100;
            }
        }
    }
    /**
     * Ivás
     * Elegendő anyagal(1 tiszta víz) tudunk inni,
     * ami 40-el növeli a szomjúságunk.
     */
    public static void drink(){
        if (waterCount > 0){
            waterCount--;
            thirstCounter += 40;
            if (thirstCounter > 100){
                thirstCounter = 100;
            }
        }
    }
    /**
     * Lándzsa készítés
     * Elegendő anyagal(4 fa,4 levél, 4 hulladék) tudunk lándzsát készíteni,
     * amivel tudunk harcolni a cápa ellen.
     */
    public static void makeSpear(){
        if (!haveSpear){
            if (woodCount > 3 && leafCount > 3 && garbageCount > 3){
                woodCount -= 4;
                leafCount -= 4;
                garbageCount -= 4;
                spearCount = "van";
                haveSpear = true;
            }
        }
    }
    /**
     * Háló készítés
     * Elegendő anyagal(2 fa, 6 levél), és egy kocka távolságra a hajótól tudunk hálót készíteni,
     * amivel tudunk úszó anyagokat gyűjteni.
     */
    public static void makeNet(){
        if (woodCount > 1 && leafCount > 5){
            int tempPlayerX = playerx / UNIT_SIZE;
            int tempPlayerY = playery / UNIT_SIZE;
            if(shipCoordinates[tempPlayerX - 1][tempPlayerY] == 1 || shipCoordinates[tempPlayerX + 1][tempPlayerY] == 1 || shipCoordinates[tempPlayerX][tempPlayerY - 1] == 1 || shipCoordinates[tempPlayerX][tempPlayerY + 1] == 1) {
                netCount++;
                woodCount -= 2;
                leafCount -= 6;
                thingCoordinates[playerx / UNIT_SIZE][playery / UNIT_SIZE] = 1;
            }
        }
    }
    /**
     * Tűzhely készítés
     * Elegendő anyagal(2 fa,4 levél, 3 hulladék) tudunk tűzhelyet készíteni,
     * amivel burgonyát és halat tudunk sütni.
     */
    public static void makeFirePlace(){
        if (!haveFirePlace){
            if (woodCount > 1 && leafCount > 3 && garbageCount > 2){
                woodCount -= 2;
                leafCount -= 4;
                garbageCount -= 3;
                firePlaceCount = "van";
                haveFirePlace = true;
            }
        }
    }
    /**
     * Víztisztító készítés
     * Elegendő anyagal(2 fa, 4 hulladék) tudunk víztisztítót készíteni,
     * amivel vizet tudunk tisztítani.
     */
    public static void makeWaterCleaner(){
        if (!haveWaterCleaner){
            if (woodCount > 1 && garbageCount > 3){
             woodCount -= 2;
             garbageCount -= 4;
             waterCleanerCount = "van";
             haveWaterCleaner = true;
            }
        }
    }
    /**
     * Cselekvés számláló
     * Minden cselekvéssel 1-el nő a száma.
     */
    public static void progress(){
        progressCounter++;
    }
    /**
     * Cápa mozgása
     * Ha a játékos a hajón van, akkor a cápa véletlenszerűen mozog fel-le, jobbra-balra.
     * Ha a játékos a vízben van, akkor a cápa a lehető legrövidebb úton közelít a játékos felé.
     */
    public static void sharkMovement(){
        int tempPlayerX = playerx / UNIT_SIZE;
        int tempPlayerY = playery / UNIT_SIZE;
        if ((shipCoordinates[tempPlayerX][tempPlayerY] == 1)) {
            Random random = new Random();
            int randomSharkMovement = random.nextInt(4);
            switch (randomSharkMovement) {
                case 0 -> {
                    if (sharkx > SCREEN_WIDTH - UNIT_SIZE){
                        sharkx = SCREEN_WIDTH - UNIT_SIZE;
                    }else {
                        sharkx += UNIT_SIZE;
                    }
                }
                case 1 -> {
                    if (sharkx < 0){
                        sharkx = 0;
                    }else {
                        sharkx -= UNIT_SIZE;
                    }
                }
                case 2 -> {
                    if (sharky > SCREEN_HEIGHT - 100 - UNIT_SIZE){
                        sharky = SCREEN_HEIGHT - 100 - UNIT_SIZE;
                    }else {
                        sharky += UNIT_SIZE;
                    }
                }
                case 3 -> {
                    if (sharky < 0){
                        sharky = 0;
                    }else {
                        sharky -= UNIT_SIZE;
                    }
                }
            }
        }else {
            if (sharkx < playerx && sharky < playery){
                sharkx += UNIT_SIZE;
                sharky += UNIT_SIZE;
            }else if (sharkx > playerx && sharky > playery){
                sharkx -= UNIT_SIZE;
                sharky -= UNIT_SIZE;
            }else if (sharkx < playerx && sharky > playery){
                sharkx += UNIT_SIZE;
                sharky -= UNIT_SIZE;
            }else if (sharkx > playerx && sharky < playery) {
                sharkx -= UNIT_SIZE;
                sharky += UNIT_SIZE;
            }else if (sharkx > playerx){
                sharkx -= UNIT_SIZE;
            }else if (sharkx < playerx){
                sharkx += UNIT_SIZE;
            }else if (sharky > playery){
                sharky -= UNIT_SIZE;
            }else if (sharky < playery){
                sharky += UNIT_SIZE;
            }
        }
    }
    /**
     * Cápa támadása
     * Ha a játékos a hajón van, akkora  cápa nem ártalmas rá nézve.
     * Ha a játékos a vízben van,és találkozik a cápával, akkor a cápa megöli, és vége a játéknak.
     * Az előző esettel ellentétben, ha a játékos birtokában van lándzsa,
     * akkor találkozáskor a cápával, nem hal meg a játékos, hanem a cápa elmenekül egy adott távra,
     * de utána ugyanúgy közelít a játékos felé. A lándzsa eltörik használatkor, újjat kell létrehozni.
     */
    public static void sharkAttack(){
        int tempPlayerX = playerx / UNIT_SIZE;
        int tempPlayerY = playery / UNIT_SIZE;
        if (!(shipCoordinates[tempPlayerX][tempPlayerY] == 1)){
            if (Math.abs(sharkx - playerx) <= UNIT_SIZE/2 && Math.abs(sharky - playery ) <= UNIT_SIZE/2){
                if (haveSpear){
                    Random random = new Random();
                    int randomSharkMovement = random.nextInt(4);
                    switch (randomSharkMovement){
                        case 0 -> {
                            sharkx += 5*UNIT_SIZE;
                            sharky += 5*UNIT_SIZE;
                            haveSpear = false;
                            spearCount = "nincs";
                        }
                        case 1 -> {
                            sharkx -= 5*UNIT_SIZE;
                            sharky -= 5*UNIT_SIZE;
                            haveSpear = false;
                            spearCount = "nincs";
                        }
                        case 2 -> {
                            sharkx += 5*UNIT_SIZE;
                            sharky -= 5*UNIT_SIZE;
                            haveSpear = false;
                            spearCount = "nincs";
                        }
                        case 3 -> {
                            sharkx -= 5*UNIT_SIZE;
                            sharky += 5*UNIT_SIZE;
                            haveSpear = false;
                            spearCount = "nincs";
                        }
                    }
                }else {
                    hungerCounter = 0;
                    thirstCounter = 0;
                }
            }
        }
    }
    /**
     * Hálóval anyaggyűjtés
     * Ugyanúgy mint a játékos, ha van lehejezve háló a hajó mellé,
     * az össze tudja gyűjteni a felé úszó anyagokat.
     */
    public static void net(){
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
                if(thingCoordinates[i][j]== 1){
                    items.netAcquireResource(i,j);
                }
            }
        }
    }
}
