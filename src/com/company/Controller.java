package com.company;

import com.sun.javafx.geom.Point2D;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by leno on 17.12.16.
 */
public class Controller {
    private HashMap<Integer,Label> bankLabels = new HashMap<>();
    private HashMap<ChannelFIFO,Label> channelLabels = new HashMap<>();
    private HashMap<Message,Label> messageLabels = new HashMap<>();
    private LinkedList<Label> labelStorage;


    public Controller(LinkedList<Label> labels) {
        this.labelStorage = labels;
    }

    public void addBankLabel(Node node, Label label){
        bankLabels.put(node.getNumber(),label);
    }

    public void addChannelLabel(ChannelFIFO channel, Label label){
        channelLabels.put(channel,label);
    }

    public void registerMessage(Message message){
        Label label;
        while (labelStorage.isEmpty()){
            try {
                message.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        label = labelStorage.removeFirst();
        label.setVisible(true);
        messageLabels.put(message,label);

        label.setTranslateX(message.getChannel().getSource().getPosition().x);
        label.setTranslateY(message.getChannel().getSource().getPosition().y);
    }

    public void updateMessage(Message message, Point2D point){
        Platform.runLater(() -> {
            Label label = messageLabels.get(message);
            label.setTranslateX(point.x);
            label.setTranslateY(point.y);
        });
    }

    public void deregisterMessage(Message message){
        Label label = messageLabels.get(message);
        messageLabels.remove(message);
        labelStorage.addLast(label);
        label.setVisible(false);
    }

    public void updateBankLabel(Node node){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                bankLabels.get(node.getNumber()).setText("Bank "+node.getNumber()+"\n"+"Konto: "+node.getValue());
            }
        });
    }

    public void updateChannelLabel(ChannelFIFO channel){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                channelLabels.get(channel).setText(channel.toStringExtendet());
            }
        });
    }
}
