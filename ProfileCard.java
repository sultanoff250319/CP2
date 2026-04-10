package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProfileCard extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox topBox = new HBox();
        topBox.setPadding(new Insets(12));
        topBox.setStyle("-fx-background-color: #2C3E50;");

        Label nameLabel = new Label("John Doe");
        nameLabel.setStyle("-fx-text-fill: white;");
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        topBox.getChildren().add(nameLabel);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Department:"), 0, 0);
        grid.add(new Label("Computer Science"), 1, 0);

        grid.add(new Label("Year:"), 0, 1);
        grid.add(new Label("Junior"), 1, 1);

        grid.add(new Label("GPA:"), 0, 2);
        grid.add(new Label("3.85"), 1, 2);

        Label footerLabel = new Label("New Uzbekistan University");
        footerLabel.setPadding(new Insets(8));
        footerLabel.setMaxWidth(Double.MAX_VALUE);
        footerLabel.setAlignment(Pos.CENTER);
        footerLabel.setStyle("-fx-background-color: #ECF0F1; -fx-font-size: 13;");

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(grid);
        root.setBottom(footerLabel);

        Scene scene = new Scene(root, 400, 250);
        primaryStage.setTitle("Student Profile Card");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
