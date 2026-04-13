package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class KeyboardMove extends Application {

    private double x = 220;
    private double y = 180;
    private static final double REC_WIDTH = 60;
    private static final double REC_HEIGHT = 40;
    private static final double PANE_WIDTH = 500;
    private static final double PANE_HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);

        Rectangle rect = new Rectangle(x, y, REC_WIDTH, REC_HEIGHT);
        rect.setFill(Color.BLUE);
        gamePane.getChildren().add(rect);

        TextField posField = new TextField(String.format("X: %.0f  Y: %.0f", x, y));
        posField.setEditable(false);
        posField.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(gamePane);
        root.setBottom(posField);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> y -= 10;
                case DOWN -> y += 10;
                case LEFT -> x -= 10;
                case RIGHT -> x += 10;
            }

            x = Math.max(0, Math.min(PANE_WIDTH - REC_WIDTH, x));
            y = Math.max(0, Math.min(PANE_HEIGHT - REC_HEIGHT, y));

            rect.setX(x);
            rect.setY(y);
            posField.setText(String.format("X: %.0f  Y: %.0f", x, y));
        });

        primaryStage.setTitle("Keyboard Move Rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}