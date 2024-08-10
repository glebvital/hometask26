package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;


public class Game extends JFrame{

    private Model model = new Model();
    private JLabel totalBalls;
    private JLabel totalBounces;

    public void setModel(Model model){
        this.model = model;
    }

    public Game() {
        setTitle("Bouncy balls");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        setLayout(null);
        totalBalls = model.getTotalBalls();
        totalBounces = model.getTotalBounces();
        totalBalls.setBounds(600,10,150,50);
        totalBalls.setText("Balls spawned: "+model.getBallCount());
        totalBounces.setBounds(600,70,150,50);
        totalBounces.setText("Bounces happen: "+ model.getBounceCount());
        add(totalBalls);
        add(totalBounces);
        setVisible(true);
    }



    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
