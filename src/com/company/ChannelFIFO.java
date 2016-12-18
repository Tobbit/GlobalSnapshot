package com.company;

import com.company.Config;
import com.company.Message;
import com.company.Node;

import java.nio.channels.Channel;
import java.util.Iterator;
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
    private Controller controller;

    public ChannelFIFO(Node source, Node destination, Controller controller) {
        this.controller = controller;
        this.source = source;
        this.destination = destination;
        source.linkNode(this);
        queue = new LinkedList<>();
        channelDelay = new Random().nextInt(Config.MAX_CHANNEL_DELAY);

    }

    public void addMessage(Message message){
        queue.addFirst(message);
        controller.updateChannelLabel(this);
        controller.registerMessage(message);
        message.start();
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public int getChannelDelay() {
        return channelDelay;
    }

    private void deliverMessage(Message message){
        destination.sendMessage(message);
    }

    @Override
    public String toString() {
        return "Channel "+getSource().getNumber()+"->"+getDestination().getNumber();
    }

    public String toStringExtendet() {
        String text = toString();
        if(!queue.isEmpty()){
            Iterator<Message> iter = queue.iterator();
            while(iter.hasNext()){
                text += "\n"+iter.next().toString();
            }
        }
        return text;
    }

    public Controller getController() {
        return controller;
    }
}
