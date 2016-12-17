package com.company;

import com.company.Config;
import com.company.Message;
import com.company.Node;

import java.nio.channels.Channel;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by leno on 17.12.16.
 */
public class ChannelFIFO extends Thread{
    private Node source;
    private Node destination;
    private final int channelDelay;
    private LinkedList<Message> queue;

    public ChannelFIFO(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
        source.linkNode(this);
        queue = new LinkedList<>();
        channelDelay = new Random().nextInt(Config.MAX_CHANNEL_DELAY);

    }

    public void addMessage(Message message){
        queue.addFirst(message);
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    private void deliverMessage(Message message){
        destination.sendMessage(message);
    }

    @Override
    public void run() {
        while (isAlive()){
            try {
                sleep(channelDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!queue.isEmpty()){
                Message message = queue.removeLast();
                System.out.println(toString()+" "+message.toString());
                destination.sendMessage(message);
                destination.interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return "Channel "+getSource().getNumber()+"->"+getDestination().getNumber();
    }
}
