package com.company;

import com.sun.javafx.geom.Point2D;

import java.util.*;

/**
 * Created by Tobi on 16.12.2016.
 */
public class Node extends Thread {
    private static int countID = 0;
    private int number;
    private ArrayList<Node> neighbors;
    private int value = 100;
    private LinkedList<Message> channel;
    private boolean isRunning = true;

    public Node() {
        neighbors = new ArrayList<>();
        number = ++countID;
        channel = new LinkedList<>();
    }

    public boolean linkNode(Node node){
        if (neighbors.contains(node)){
            return false;
        }else {
            neighbors.add(node);
            return true;
        }
    }

    public int getNumber() {
        return number;
    }

    public void sendMessage(Message message){
        channel.addLast(message);
    }

    private Message readMessage(){
        try {
            return channel.removeLast();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public void run() {
        Random rnd = new Random();
        Message message;
        while (isRunning){
            try {

                //Warten
                sleep(rnd.nextInt(10000));

                //Nachrichten lesen
                if(rnd.nextBoolean()) {
                    message = readMessage();
                    if (message != null) {
                        System.out.println("Tread: " + getNumber() + " " + message.toString());
                        setValue(getValue()+message.getValue());
                        System.out.println("Tread: " + getNumber() + " Kontostand: " + getValue());
                    }
                }

                //Warten
                sleep(rnd.nextInt(10000));

                if(rnd.nextBoolean()){
                    int sendValue = rnd.nextInt(getValue()/2+1);
                    Node receiver = neighbors.get(rnd.nextInt(neighbors.size()));
                    message = new Message(sendValue,this, receiver);
                    setValue(getValue()-sendValue);
                    receiver.sendMessage(message);
                    System.out.println("Tread: " + getNumber() + " Kontostand: " + getValue());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    public Point2D getPosition(){
        return getMyPosition(getNumber());
    }

    private static Point2D getMyPosition(int number){
        float[] xPositions = {360,150,350,570,750};
        float[] yPositions = {120,390,660,450,600};
        if(number > xPositions.length || number <= 0){
            return new Point2D(0,0);
        }else {
            return new Point2D(xPositions[number-1], yPositions[number-1]);
        }

    }
}
