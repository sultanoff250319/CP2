package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CreateMouseCoords extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("Move the mouse over the pane");

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(400, 300);
        stackPane.setStyle("-fx-background-color: lightblue;");

        stackPane.setOnMouseMoved(e -> {
            label.setText(String.format("X: %.1f Y: %.1f", e.getX(), e.getY()));
        });

        stackPane.setOnMouseClicked(e -> {
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            stackPane.setStyle(String.format("-fx-background-color: rgb(%d,%d,%d);", r, g, b));
        });

        stackPane.setOnMouseExited(e -> {
            label.setText("Move the mouse over the pane");
        });

        BorderPane root = new BorderPane();
        root.setCenter(stackPane);
        root.setBottom(label);

        BorderPane.setAlignment(label, Pos.CENTER);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Mouse Coordinates Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
