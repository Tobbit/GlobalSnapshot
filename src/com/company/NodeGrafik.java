package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Tobi on 17.12.2016.
 */
public class NodeGrafik extends JPanel {


    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int r = 50;
        g2d.setColor(Color.red);
        g2d.draw(getKreis(360,120,r));//1
    }
    private Ellipse2D.Double getKreis(double mx, double my, double r){
        return new Ellipse2D.Double(mx-r, my-r, 2*r, 2*r);
    }
}
