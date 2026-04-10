package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TrafficLight extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Use a VBox (spacing 5, padding 20, centered)
        VBox root = new VBox(5);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // 2. Color them red, yellow, and green respectively; give each a dark gray stroke of width 2
        // 3. Hard-code the red light as “active” (full opacity) and the other two as dim (opacity 0.3)
        Circle redLight = new Circle(30);
        redLight.setFill(Color.RED);
        redLight.setStroke(Color.DARKGRAY);
        redLight.setStrokeWidth(2);
        redLight.setOpacity(1.0);

        Circle yellowLight = new Circle(30);
        yellowLight.setFill(Color.YELLOW);
        yellowLight.setStroke(Color.DARKGRAY);
        yellowLight.setStrokeWidth(2);
        yellowLight.setOpacity(0.3);

        Circle greenLight = new Circle(30);
        greenLight.setFill(Color.GREEN);
        greenLight.setStroke(Color.DARKGRAY);
        greenLight.setStrokeWidth(2);
        greenLight.setOpacity(0.3);

        // 4. Add a Text node below the circles reading "Stop" in bold, font size 18
        Text stopText = new Text("Stop");
        stopText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Add children to the VBox
        root.getChildren().addAll(redLight, yellowLight, greenLight, stopText);

        // 5. Set the scene background to dark gray using scene.setFill(Color.DARKGRAY)
        Scene scene = new Scene(root, 150, 300);
        scene.setFill(Color.DARKGRAY);

        primaryStage.setTitle("Traffic Light");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
