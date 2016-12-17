package com.company;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leno on 17.12.16.
 */
public class Controller {
    private HashMap<Integer,Label> bankLabels = new HashMap<>();
    private HashMap<ChannelFIFO,Label> channelLabels = new HashMap<>();

    public Controller() {
    }

    public void addBankLabel(Node node, Label label){
        bankLabels.put(node.getNumber(),label);
    }

    public void addChannelLabel(ChannelFIFO channel, Label label){
        channelLabels.put(channel,label);
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

                bankLabels.get(channel).setText(channel.toStringExtendet());
            }
        });
    }
}
