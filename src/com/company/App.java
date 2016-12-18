package com.company;

import com.sun.javafx.geom.Point2D;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Tobi on 17.12.2016.
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing Operations Test");

        Group root = new Group();
        LinkedList<Label> labels = new LinkedList<Label>();
        for (int i = 0; i < 50; i++){
            labels.add(new Label());
        }
        Controller controller = new Controller(labels);

        Node[] nodes = new Node[5];
        for(int i = 0; i < nodes.length; i++){
            Canvas canvas = new Canvas(1000,1000);
            Node node = new Node(controller);
            Label label = new Label();
            controller.addBankLabel(node,label);
            label.setText("Bank "+node.getNumber()+"\n"+"Konto: "+node.getValue());
            label.setTranslateX(node.getPosition().x+Config.RADIUS/2);
            label.setTranslateY(node.getPosition().y+Config.RADIUS/2);
            drawNode(canvas,node);
            root.getChildren().add(canvas);
            root.getChildren().add(label);
            node.start();
            nodes[i] = node;
        }

        ArrayList<ChannelFIFO> channels = new ArrayList<>();
        channels.add(new ChannelFIFO(nodes[0],nodes[1],controller));
        channels.add(new ChannelFIFO(nodes[0],nodes[3],controller));
        channels.add(new ChannelFIFO(nodes[1],nodes[2],controller));
        channels.add(new ChannelFIFO(nodes[1],nodes[0],controller));
        channels.add(new ChannelFIFO(nodes[1],nodes[3],controller));
        channels.add(new ChannelFIFO(nodes[2],nodes[4],controller));
        channels.add(new ChannelFIFO(nodes[2],nodes[1],controller));
        channels.add(new ChannelFIFO(nodes[2],nodes[3],controller));
        channels.add(new ChannelFIFO(nodes[3],nodes[1],controller));
        channels.add(new ChannelFIFO(nodes[3],nodes[2],controller));
        channels.add(new ChannelFIFO(nodes[3],nodes[0],controller));
        channels.add(new ChannelFIFO(nodes[3],nodes[4],controller));
        channels.add(new ChannelFIFO(nodes[4],nodes[2],controller));
        channels.add(new ChannelFIFO(nodes[4],nodes[3],controller));

        for (ChannelFIFO channel:channels) {
            Label label = new Label();
            ScrollPane scrollPane = new ScrollPane(label);
            controller.addChannelLabel(channel,label);
            Point2D source = channel.getSource().getPosition();
            Point2D receiver = channel.getDestination().getPosition();
            scrollPane.setTranslateX((source.x+receiver.x)/2);
            scrollPane.setTranslateY((source.y+receiver.y)/2);
            root.getChildren().add(scrollPane);
        }


        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    private void drawNode(Canvas canvas, Node node){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Point2D point = node.getPosition();
        gc.setFill(Config.FILL_COLOR);
        gc.fillOval(point.x,point.y,Config.RADIUS*2,Config.RADIUS*2);
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }
}
