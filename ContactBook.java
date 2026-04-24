package org.example.Week13;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactBook extends Application {

    private Stage stage;
    private Scene scene1, scene2;
    private ListView<String> contactListView;
    private Label errorLabel1, errorLabel2;
    private final String FILE_PATH = "contacts.txt";

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        createScene1();
        createScene2();

        primaryStage.setTitle("Mini Contact Book");
        primaryStage.setScene(scene1);
        primaryStage.show();

        loadContacts();
    }

    private void createScene1() {
        contactListView = new ListView<>();
        errorLabel1 = new Label();
        errorLabel1.setStyle("-fx-text-fill: red;");

        Button addNewBtn = new Button("Add New");
        addNewBtn.setOnAction(e -> stage.setScene(scene2));

        Button deleteBtn = new Button("Delete Selected");
        deleteBtn.setOnAction(e -> deleteSelectedContact());

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> loadContacts());

        HBox buttonBox = new HBox(10, addNewBtn, deleteBtn, refreshBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(16, new Label("Contact List"), contactListView, buttonBox, errorLabel1);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        scene1 = new Scene(root, 480, 380);
    }

    private void createScene2() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Phone:"), 0, 1);
        grid.add(phoneField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);

        errorLabel2 = new Label();
        errorLabel2.setStyle("-fx-text-fill: red;");
        grid.add(errorLabel2, 1, 3);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                errorLabel2.setText("All fields are required!");
            } else {
                saveContact(name, phone, email);
                nameField.clear();
                phoneField.clear();
                emailField.clear();
                errorLabel2.setText("");
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            nameField.clear();
            phoneField.clear();
            emailField.clear();
            errorLabel2.setText("");
            stage.setScene(scene1);
        });

        HBox btnBox = new HBox(10, saveBtn, cancelBtn);
        btnBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(16, new Label("Add New Contact"), grid, btnBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        scene2 = new Scene(root, 480, 380);
    }

    private void loadContacts() {
        ObservableList<String> items = FXCollections.observableArrayList();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            contactListView.setItems(items);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    items.add(parts[0] + " --- " + parts[1] + "@" + parts[2]);
                }
            }
            contactListView.setItems(items);
            errorLabel1.setText("");
        } catch (IOException e) {
            errorLabel1.setText("Error reading contacts: " + e.getMessage());
        }
    }

    private void saveContact(String name, String phone, String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(name + "|" + phone + "|" + email);
            writer.newLine();
            loadContacts(); // Refresh list
            stage.setScene(scene1);
        } catch (IOException e) {
            errorLabel2.setText("Error saving contact: " + e.getMessage());
        }
    }

    private void deleteSelectedContact() {
        int selectedIdx = contactListView.getSelectionModel().getSelectedIndex();
        if (selectedIdx < 0) {
            errorLabel1.setText("Please select a contact to delete.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(new File(FILE_PATH).toPath());
            if (selectedIdx < lines.size()) {
                lines.remove(selectedIdx);
                Files.write(new File(FILE_PATH).toPath(), lines);
                loadContacts();
            }
        } catch (IOException e) {
            errorLabel1.setText("Error deleting contact: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
