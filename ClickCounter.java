package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ClickCounter extends Application {

    private int counter = 0;

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("Clicks: 0");
        label.setFont(Font.font("System", FontWeight.BOLD, 24));

        Button button = new Button("Click me!");

        button.setOnAction(event -> {
            counter++;
            label.setText("Clicks: " + counter);
        });

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, button);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("ClickCounter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
