package com.company;

import com.sun.javafx.geom.Point2D;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leno on 17.12.16.
 */
public class Controller {
    private HashMap<Integer,Label> bankLabels = new HashMap<>();
    private HashMap<ChannelFIFO,Label> channelLabels = new HashMap<>();
    private HashMap<Message,Label> messageLabels = new HashMap<>();
    private Group root;


    public Controller(Group root) {
        this.root = root;
    }

    public void addBankLabel(Node node, Label label){
        bankLabels.put(node.getNumber(),label);
    }

    public void addChannelLabel(ChannelFIFO channel, Label label){
        channelLabels.put(channel,label);
    }

    public void registerMessage(Message message){
        Label label = new Label(message.toString());
        messageLabels.put(message,label);

        label.setTranslateX(message.getChannel().getSource().getPosition().x);
        label.setTranslateY(message.getChannel().getSource().getPosition().y);

        root.getChildren().add(label);
    }

    public void updateMessage(Message message, Point2D point){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label label = messageLabels.get(message);
                label.setTranslateX(point.x);
                label.setTranslateY(point.y);
            }
        });
    }

    public void deregisterMessage(Message message){
        Label label = messageLabels.get(message);
        messageLabels.remove(message);
        root.getChildren().remove(label);
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
