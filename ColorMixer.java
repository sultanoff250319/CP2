package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorMixer extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Declare three double variables for R, G, B (values between 0.0 and 1.0)
        double r = 0.10; 
        double g = 0.90;
        double b = 0.40;

        // 2. Display a Rectangle (200x200) filled with new Color(r, g, b, 1)
        Rectangle preview = new Rectangle(200, 200);
        preview.setFill(new Color(r, g, b, 1.0));

        // 3. Below the rectangle show three Text labels: "R: 0.xx", "G: 0.xx", "B: 0.xx"
        Text rLabel = new Text(String.format("R: %.2f", r));
        Text gLabel = new Text(String.format("G: %.2f", g));
        Text bLabel = new Text(String.format("B: %.2f", b));

        // 4. Below the labels show the hex code as a Text node in bold (e.g. "#3A7FCC")
        String hex = String.format("#%02X%02X%02X", 
                (int) (r * 255), 
                (int) (g * 255), 
                (int) (b * 255));
        Text hexText = new Text(hex);
        hexText.setFont(Font.font("System", FontWeight.BOLD, 16));

        // 5. Arrange everything in a VBox (spacing 8, padding 20, centered)
        VBox root = new VBox(8);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(preview, rLabel, gLabel, bLabel, hexText);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Color Mixer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
