package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Node testNode1 = new Node();
        Node testNode2 = new Node();
        Node testNode3 = new Node();
        Node testNode4 = new Node();
        Node testNode5 = new Node();

        testNode1.linkNode(testNode2);
        testNode1.linkNode(testNode4);
        testNode2.linkNode(testNode3);
        testNode2.linkNode(testNode1);
        testNode2.linkNode(testNode4);
        testNode3.linkNode(testNode5);
        testNode3.linkNode(testNode2);
        testNode3.linkNode(testNode4);
        testNode4.linkNode(testNode2);
        testNode4.linkNode(testNode3);
        testNode4.linkNode(testNode1);
        testNode4.linkNode(testNode5);
        testNode5.linkNode(testNode3);
        testNode5.linkNode(testNode4);

        testNode1.start();
        testNode2.start();
        testNode3.start();
        testNode4.start();
        testNode5.start();

        startGui();
    }

    private static void startGui(){
        JFrame f = new JFrame();
        Panel panel = new Panel();
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setSize( 1000, 1000);
        f.add( panel );
        f.add(new NodeGrafik());
        f.setVisible( true );
    }
}
