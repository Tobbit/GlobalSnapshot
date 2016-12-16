package com.company;

/**
 * Created by Tobi on 16.12.2016.
 */
public class Message {
    private int value;
    private Node sender;
    private Node reseiver;

    public Message(int value, Node sender, Node reseiver) {
        this.value = value;
        this.sender = sender;
        this.reseiver = reseiver;
    }

    @Override
    public String toString() {
        return "Sender: "+getSender().getNumber()+"| Empfänger: "+getReseiver().getNumber()+"| Value: "+getValue();
    }

    public int getValue() {
        return value;
    }

    public Node getSender() {
        return sender;
    }

    public Node getReseiver() {
        return reseiver;
    }
}
