package org.example.Week13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class Problem1 extends Application {

    private TextField tfCategory;
    private TextField tfAmount;
    private TextField tfNote;
    private Label lblStatus;
    private TextArea taSummary;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        tfCategory = new TextField();
        tfAmount = new TextField();
        tfNote = new TextField();

        grid.add(new Label("Category:"), 0, 0);
        grid.add(tfCategory, 1, 0);
        grid.add(new Label("Amount:"), 0, 1);
        grid.add(tfAmount, 1, 1);
        grid.add(new Label("Note:"), 0, 2);
        grid.add(tfNote, 1, 2);

        Button btnAdd = new Button("Add Expense");
        Button btnShowSummary = new Button("Show Summary");
        Button btnClear = new Button("Clear Fields");

        lblStatus = new Label("");

        taSummary = new TextArea();
        taSummary.setEditable(false);
        taSummary.setPrefHeight(150);

        btnAdd.setOnAction(e -> addExpense());

        btnShowSummary.setOnAction(e -> showSummary());

        btnClear.setOnAction(e -> {
            tfCategory.clear();
            tfAmount.clear();
            tfNote.clear();
            lblStatus.setText("");
        });

        VBox root = new VBox(12);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(grid, btnAdd, btnShowSummary, btnClear, lblStatus, taSummary);

        Scene scene = new Scene(root, 460, 420);
        primaryStage.setTitle("ExpenseTracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addExpense() {
        String category = tfCategory.getText().trim();
        String amountStr = tfAmount.getText().trim();
        String note = tfNote.getText().trim();

        if (category.isEmpty() || amountStr.isEmpty()) {
            lblStatus.setText("Error: Category and Amount are required.");
            lblStatus.setTextFill(Color.RED);
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt", true))) {
                writer.write(category + "|" + amount + "|" + note);
                writer.newLine();
                lblStatus.setText("Saved!");
                lblStatus.setTextFill(Color.GREEN);
            } catch (IOException ex) {
                lblStatus.setText("Error: Could not save to file.");
                lblStatus.setTextFill(Color.RED);
            }

        } catch (NumberFormatException ex) {
            lblStatus.setText("Error: Amount must be a valid number.");
            lblStatus.setTextFill(Color.RED);
        }
    }

    private void showSummary() {
        File file = new File("expenses.txt");
        if (!file.exists()) {
            taSummary.setText("No expenses recorded yet.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        double total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    String category = parts[0];
                    String amountStr = parts[1];
                    String note = parts.length > 2 ? parts[2] : "";

                    try {
                        double amount = Double.parseDouble(amountStr);
                        total += amount;
                        sb.append(String.format("%s---$%.2f(%s)\n", category, amount, note));
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            sb.append(String.format("Total: $%.2f", total));
            taSummary.setText(sb.toString());
        } catch (IOException ex) {
            lblStatus.setText("Error: Could not read expenses.");
            lblStatus.setTextFill(Color.RED);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
