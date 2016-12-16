package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Tobi on 16.12.2016.
 */
public class Panel extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.BLACK);

        int r = 50;

        //g2d.draw(getKreis(360,120,r));//1
        g2d.draw(getKreis(150, 390,r));//2
        g2d.draw(getKreis(350, 660,r));//3
        g2d.draw(getKreis(570,450,r));//4
        g2d.draw(getKreis(750,600, r));//5
    }

    private Ellipse2D.Double getKreis(double mx,double my,double r){
        return new Ellipse2D.Double(mx-r, my-r, 2*r, 2*r);
    }
}
