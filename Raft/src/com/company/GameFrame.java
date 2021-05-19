package com.company;

import javax.swing.*;

public class GameFrame extends JFrame{
    /**
     * Játék ablaka
     * Itt jelennek meg a panelba rakott elemek.
     */
    GameFrame(){
        this.add(new GamePanel());
        this.setTitle("Raft");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
