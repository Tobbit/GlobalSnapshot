package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Tobi on 16.12.2016.
 */
public class Panel extends JPanel {

    private int radius = 50;

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.red);


        g2d.drawLine(360, 120, 150,390);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Bank 1",360,120);

        g2d.drawString("Bank 2",150, 390);

        g2d.drawString("Bank 3",350, 660);

        g2d.drawString("Bank 4",570,450);

        g2d.drawString("Bank 5",750,600);


    }

    private void drawNode(int id, double mx,double my, Graphics2D graphics){
        Shape kreis = new Ellipse2D.Double(mx-radius, my-radius, 2*radius, 2*radius);
        graphics.fill(kreis);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Bank "+id,360,120);
    }
}
