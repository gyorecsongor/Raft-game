package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static com.company.CollectablesList.*;
import static com.company.Datas.*;
import static com.company.NewResource.*;
import static com.company.Actions.*;

public class GamePanel extends JPanel  implements ActionListener{
    static CollectablesList items = new CollectablesList();
    static JProgressBar hunger = new JProgressBar(0,100);
    static JProgressBar thirst = new JProgressBar(0,100);

    Image player, shark, leaf, wood,  garbage, barrel, firePlace, waterCleaner, net, ship;
    /**
     * A panelba elemek berakása.
     * Játék indítása.
     */
    GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.blue);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.add(hunger);
        this.add(thirst);

        ship = new ImageIcon("ship.jpg").getImage();
        shark = new ImageIcon("shark.jpg").getImage();
        leaf = new ImageIcon("leaf.jpg").getImage();
        wood = new ImageIcon("wood.jpg").getImage();
        garbage = new ImageIcon("garbage.jpg").getImage();
        barrel = new ImageIcon("barrel.jpg").getImage();
        firePlace = new ImageIcon("fireplace.jpg").getImage();
        waterCleaner = new ImageIcon("watercleaner.jpg").getImage();
        net = new ImageIcon("net.jpg").getImage();
        player = new ImageIcon("player.jpg").getImage();

        startGame();
    }
    /**
     * Játék indítás
     * Éhség, szomjúság beállitása 100-ra.
     * Hajó kezdő kordinátáinak beállitása.
     */
    public void startGame(){
        hunger.setValue(100);
        hunger.setBounds(800,700,170,40);
        hunger.setStringPainted(true);
        hunger.setForeground(Color.red);
        hunger.setBackground(Color.black);
        hunger.setString("Éhség");

        thirst.setValue(100);
        thirst.setBounds(800,760,170,40);
        thirst.setStringPainted(true);
        thirst.setForeground(Color.red);
        thirst.setBackground(Color.black);
        thirst.setString("Szomjúság");

        shipCoordinates[19][13] = 1;
        shipCoordinates[19][14] = 1;
        shipCoordinates[20][14] = 1;
        shipCoordinates[20][13] = 1;
    }

    /**
     * Grafikus elemek készítése.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    /**
     * Grafikus elemek készítése.
     * Az összes anyagnak, játékosnak, cápának kép adása.
     * A játék nyerése grafika beilleszése, abban az esetben,
     * ha a cselekvésszámláló nagyobb mint 1000.
     * A játék veresége grafika beilleszése, abban az esetben,
     * ha az éhség, vagy a szomjúság értéke eléri a 0-át.
     * Megszerzett anyagok, megépített eszközök kiiratása.
     */
    public void draw(Graphics g){
        sharkAttack();
        if (hungerCounter < 1 || thirstCounter < 1){
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 80));
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            g.drawString("YOU DIED", (SCREEN_WIDTH - metrics.stringWidth("YOU DIED"))/2, SCREEN_HEIGHT/2);
            setBackground(Color.black);
            hunger.setVisible(false);
            thirst.setVisible(false);
        }else if (progressCounter > 999){
                g.setColor(Color.red);
                g.setFont(new Font("Ink Free", Font.BOLD, 80));
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                g.drawString("YOU WON", (SCREEN_WIDTH - metrics.stringWidth("YOU WON"))/2, SCREEN_HEIGHT/2);
                setBackground(Color.black);
                hunger.setVisible(false);
                thirst.setVisible(false);
            } else{
            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                g.setColor(Color.BLACK);
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            }
            for (int i = 0; i < SCREEN_HEIGHT/ UNIT_SIZE; i++){
                g.setColor(Color.BLACK);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            for (int i = 0; i < items.itemssize(); i++){
                if (items.getResource(i) == 'W'){
                    g.drawImage(wood, items.getX(i), items.getY(i), UNIT_SIZE, UNIT_SIZE, this);
                }else if (items.getResource(i) == 'L'){
                    g.drawImage(leaf, items.getX(i), items.getY(i), UNIT_SIZE, UNIT_SIZE, this);
                }else if (items.getResource(i) == 'G'){
                    g.drawImage(garbage, items.getX(i), items.getY(i), UNIT_SIZE, UNIT_SIZE, this);
                }else if (items.getResource(i) == 'B'){
                    g.drawImage(barrel, items.getX(i), items.getY(i), UNIT_SIZE, UNIT_SIZE, this);
                }
            }

            g.setColor(Color.white);
            g.fillRect(0, 700, SCREEN_WIDTH, 100);

            g.setColor(Color.black);
            g.setFont(new Font("MV Boli", Font.PLAIN, 20));
            g.drawString("Fa:" + woodCount , 0, 720);
            g.drawString("Levél:" + leafCount , 0, 780);
            g.drawString("Hulladék:" + garbageCount , 150, 720);
            g.drawString("Hordó:" + barrelCount , 150, 780);
            g.drawString("Burgonya:" + bakedPotato + " (" + potatoCount + ")" , 300, 720);
            g.drawString("Hal:" + + bakedFish + " (" + fishCount + ")" , 300, 780);
            g.drawString("Lándzsa:" + spearCount , 450, 720);
            g.drawString("Háló:" + netCount , 450, 780);
            g.drawString("Cselekvések száma:" + progressCounter, 750, 780);
            g.drawString("Víz:" + waterCount, 600, 780);
            g.drawString("Tüzhely:" + firePlaceCount , 600, 720);
            g.drawString("Víztisztító:" + waterCleanerCount , 750, 720);

            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++){
                for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++){
                    if (shipCoordinates[i][j] == 1)
                        g.drawImage(ship, i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, this);
                }
            }

            if (haveFirePlace){
                g.drawImage(firePlace, 19 * UNIT_SIZE, 13 * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, this);
            }

            if (haveWaterCleaner){
                g.drawImage(waterCleaner, 19 * UNIT_SIZE, 14 * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, this);
            }

            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++){
                for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++){
                    if (thingCoordinates[i][j] == 1)
                        g.drawImage(net, i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, this);
                }
            }

            g.drawImage(shark, sharkx, sharky, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(player, playerx, playery, UNIT_SIZE, UNIT_SIZE, this);
        }
    }


    public class MyKeyAdapter extends KeyAdapter {
        /**
         * Cselekvések
         * -a : mozgás a játékossal balra
         * -d : mozgás a játékossal jobbra
         * -w : mozgás a játékossal fel
         * -s : mozgás a játékossal le
         * -q : mozgás a játékossal átlósan fel balra
         * -e : mozgás a játékossal átlósan fel jobbra
         * -c : mozgás a játékossal átlósan le jobbra
         * -y : mozgás a játékossal átlósan le balra
         * -u : burgonya evés
         * -i : hal evés
         * -o : ivás
         * -p : tűzhely készítés
         * -j : víztisztító készítés
         * -k : háló készítés
         * -l : lándzsa készítés
         * -b : burgonya sütés
         * -n : hal sütés
         * -m : víztisztítás
         * -g : halászás
         * -f : hajó bővítés
         *
         * Minden gombnyomással nő a cselekvések száma.
         */
        @Override
        public void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'a' -> {
                    if (playerx < 0){
                        playerx = 0;
                    }else {
                        playerx -= UNIT_SIZE;
                    }
                }
                case 'w' -> {
                    if (playery < 0){
                        playery = 0;
                    }else {
                        playery -= UNIT_SIZE;
                    }
                }
                case 's' -> {
                    if (playery > SCREEN_HEIGHT - (100 + UNIT_SIZE)){
                        playery = SCREEN_HEIGHT - (100 + UNIT_SIZE);
                    }else {
                        playery += UNIT_SIZE;
                    }
                }
                case 'd' -> {
                    if (playerx > SCREEN_WIDTH - UNIT_SIZE){
                        playerx = SCREEN_WIDTH - UNIT_SIZE;
                    }else {
                        playerx += UNIT_SIZE;
                    }
                }
                case 'e' -> {
                    playerx += UNIT_SIZE;
                    playery -= UNIT_SIZE;
                }
                case 'y' -> {
                    playerx -= UNIT_SIZE;
                    playery += UNIT_SIZE;
                }
                case 'q' -> {
                    playerx -= UNIT_SIZE;
                    playery -= UNIT_SIZE;
                }
                case 'c' -> {
                    playerx += UNIT_SIZE;
                    playery += UNIT_SIZE;
                }
                case 'u' -> eatPotato();
                case 'i' -> eatFish();
                case 'o' -> drink();
                case 'p' -> makeFirePlace();
                case 'j' -> makeWaterCleaner();
                case 'k' -> makeNet();
                case 'l' -> makeSpear();
                case 'b' -> {
                    potatoBakeProgressCounter = 0;
                    bakePotato();
                }
                case 'n' -> {
                    fishBakeProgressCounter = 0;
                    bakeFish();
                }
                case 'm' -> {
                    waterCleanerProgressCounter = 0;
                    cleanWater();
                }
                case 'g' -> fishing();
                case 'f' -> makeBiggerShip();
            }
            potatoBaking();
            fishBaking();
            waterCleaning();
            progress();
            fill();
            sharkMovement();
            newResource();
            items.nextCollectables();
            acquireResource();
            net();
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
