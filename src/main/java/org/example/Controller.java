package org.example;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Controller implements MouseListener {

    private Model model;
    private Game view;

    Controller(Model model, Game view){
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
        view.setModel(model);
        model.setView(view);
        model.startGame();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        model.click(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
