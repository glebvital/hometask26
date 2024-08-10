package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Model {

    private ArrayList<Ball> balls = new ArrayList<>();
    private JLabel totalBalls = new JLabel();
    private JLabel totalBounces = new JLabel();
    private int ballCount = 0, bounceCount = 0;
    private Game view;

    public void startGame(){
        new GameThread().start();
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public JLabel getTotalBalls() {
        return totalBalls;
    }

    public JLabel getTotalBounces() {
        return totalBounces;
    }

    public int getBallCount() {
        return ballCount;
    }

    public int getBounceCount() {
        return bounceCount;
    }

    public void cleanAllExeption(){
        balls.removeAll(balls);
    }

    public void resetCheck(){
        if (bounceCount>=50000){
            UpdateBounceCount(bounceCount=0);
            UpdateBallCount(ballCount=0);
            cleanAllExeption();
        }
    }

    private void wall() {
        int x = 0, y = 0, width = view.getWidth(), height = view.getHeight();
        balls.forEach(ball -> {
            if (ball.getX() < x || ball.getX() > width - ball.getSize()) {
                bounceCount++;
                UpdateBounceCount(bounceCount);
                ball.setxStep(-ball.getxStep());
            }
            if (ball.getY() < y || ball.getY() > height - ball.getSize()) {
                bounceCount++;
                UpdateBounceCount(bounceCount);
                ball.setyStep(-ball.getyStep());
            }
        });
    }

    private void collision(){

        for (int i = 0; i < balls.size(); i++) {
            for (int j = i+1; j < balls.size(); j++) {
                int a = Math.abs(balls.get(i).getX()-balls.get(j).getX());
                int b = Math.abs(balls.get(i).getY()-balls.get(j).getY());
                int c = (int) Math.sqrt(a*a + b*b);
                if (c <= balls.get(i).getSize()+balls.get(j).getSize()){
                    bounceCount++;
                    UpdateBounceCount(bounceCount);
                    balls.get(i).setxStep(-balls.get(i).getxStep());
                    balls.get(i).setyStep(-balls.get(i).getyStep());
                    balls.get(j).setxStep(-balls.get(j).getxStep());
                    balls.get(j).setyStep(-balls.get(j).getyStep());
                    //мячи побольше "эдят" мячи поменше и в итоге ростут в розмере
//                    if (balls.get(i).getSize()>balls.get(j).getSize() && balls.get(i).getSize()!=50){
//                        balls.remove(j);
//                        balls.get(i).setSize(balls.get(i).getSize()+10);
//                    } else if (balls.get(j).getSize()>balls.get(i).getSize() && balls.get(j).getSize()!=50) {
//                        balls.remove(i);
//                        balls.get(j).setSize(balls.get(j).getSize()+10);
//                    }
                }
            }
        }
    }

    public void UpdateBallCount(int count){
        //отображает количество созданых мячов за всё время игры
        totalBalls.setText("Balls spawned: "+ count);
    }

    public void UpdateBounceCount(int count){
        resetCheck();
        totalBounces.setText("Bounces happen: "+ count);
    }

    public void click(MouseEvent e) {
        ballCount++;
        UpdateBallCount(ballCount);
        int x = e.getX();
        int y = e.getY();
        Random random = new Random();
        int xStep = random.nextInt(20) - 15;
        int yStep = random.nextInt(20) - 15;
        Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        int size = random.nextInt(20)+10;
        Ball ball = new Ball(x, y, xStep, yStep, color, size);
        balls.add(ball);
    }

    public void setView(Game view) {
        this.view = view;
    }

    class GameThread extends Thread {
        @Override
        public void run() {
            while (true) {
                view.repaint();
                wall();
                collision();
                balls.forEach(ball -> ball.draw(view.getGraphics()));
                totalBalls.repaint();
                totalBounces.repaint();
                try {
                    Thread.sleep(1000/60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
