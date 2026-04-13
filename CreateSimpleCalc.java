package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateSimpleCalc extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label lblNum1 = new Label("Number 1");
        Label lblNum2 = new Label("Number 2");
        TextField tfNum1 = new TextField();
        TextField tfNum2 = new TextField();

        TextField tfResult = new TextField();
        tfResult.setEditable(false);

        Button btnCalculate = new Button("Calculate");
        Button btnClear = new Button("Clear");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        grid.add(lblNum1, 0, 0);
        grid.add(tfNum1, 1, 0);
        grid.add(lblNum2, 0, 1);
        grid.add(tfNum2, 1, 1);
        grid.add(tfResult, 1, 2);
        grid.add(btnCalculate, 0, 3);
        grid.add(btnClear, 1, 3);

        btnCalculate.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(tfNum1.getText());
                double num2 = Double.parseDouble(tfNum2.getText());
                double sum = num1 + num2;

                tfResult.setText(String.format("Sum: %.2f", sum));
            } catch (NumberFormatException ex) {
                tfResult.setText("Invalid input!");
            }
        });

        btnClear.setOnAction(e -> {
            tfNum1.clear();
            tfNum2.clear();
            tfResult.clear();
        });

        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
