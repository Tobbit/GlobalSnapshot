package com.company;

import com.sun.javafx.geom.Point2D;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * Created by Tobi on 17.12.2016.
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Node[] nodes = new Node[5];
        for(int i = 0; i < nodes.length; i++){
            Canvas canvas = new Canvas(1000,1000);
            Label label = new Label();
            Node node = new Node(label);
            label.setText("Bank "+node.getNumber()+"\n"+"Guthaben: "+node.getValue());
            label.setTranslateX(node.getPosition().x+Config.RADIUS/2);
            label.setTranslateY(node.getPosition().y+Config.RADIUS/2);
            drawNode(canvas,node);
            root.getChildren().add(canvas);
            root.getChildren().add(label);
            node.start();
            nodes[i] = node;
        }

        nodes[0].linkNode(nodes[1]);
        nodes[0].linkNode(nodes[3]);
        nodes[1].linkNode(nodes[2]);
        nodes[1].linkNode(nodes[0]);
        nodes[1].linkNode(nodes[3]);
        nodes[2].linkNode(nodes[4]);
        nodes[2].linkNode(nodes[1]);
        nodes[2].linkNode(nodes[3]);
        nodes[3].linkNode(nodes[1]);
        nodes[3].linkNode(nodes[2]);
        nodes[3].linkNode(nodes[0]);
        nodes[3].linkNode(nodes[4]);
        nodes[4].linkNode(nodes[2]);
        nodes[4].linkNode(nodes[3]);

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
