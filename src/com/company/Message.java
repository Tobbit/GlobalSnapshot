package com.company;

import com.sun.javafx.geom.Point2D;

/**
 * Created by Tobi on 16.12.2016.
 */
public class Message extends Thread {
    private int value;
    private ChannelFIFO channel;
    public Message(int value, ChannelFIFO channel) {
        this.value = value;
        this.channel = channel;
    }

    @Override
    public void run() {
        Point2D source = channel.getSource().getPosition();
        Point2D receiver = channel.getDestination().getPosition();
        Point2D relative = new Point2D(source.x-receiver.x,source.y-receiver.y);
        Point2D position = new Point2D(0,0);
        long startTime = System.currentTimeMillis();
        long prozessTime = 0;
        double process = 0;
        while (prozessTime < channel.getChannelDelay()){
            process = prozessTime / (double) channel.getChannelDelay();

            position.setLocation((float)(source.x+(relative.x*process)),(float) (source.y+(relative.y*process)));
            channel.getController().updateMessage(this,position);
            prozessTime = System.currentTimeMillis()-startTime;
        }

        channel.getDestination().sendMessage(this);
        channel.getDestination().interrupt();
        channel.getController().deregisterMessage(this);

    }

    @Override
    public String toString() {
        return "S: "+channel.getSource().getNumber()+"| E: "+channel.getDestination().getNumber()+"| V: "+getValue();
    }

    public int getValue() {
        return value;
    }

    public ChannelFIFO getChannel() {
        return channel;
    }
}
