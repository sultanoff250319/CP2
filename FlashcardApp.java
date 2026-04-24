package org.example.Week13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FlashcardApp extends Application {

    private ArrayList<String[]> cards = new ArrayList<>();
    private int currentIndex = 0;
    private boolean isShowingQuestion = true;

    private Label indexLabel;
    private Label cardLabel;
    private Button flipButton;

    @Override
    public void start(Stage primaryStage) {
        loadCards();

        if (cards.isEmpty()) {
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            root.getChildren().add(new Label("No cards found. Add cards to cards.txt and restart."));
            Scene scene = new Scene(root, 480, 300);
            primaryStage.setTitle("Flashcard Study App");
            primaryStage.setScene(scene);
            primaryStage.show();
            return;
        }

        indexLabel = new Label();
        indexLabel.setFont(Font.font("System", 12));

        cardLabel = new Label();
        cardLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        cardLabel.setTextAlignment(TextAlignment.CENTER);
        cardLabel.setWrapText(true);
        cardLabel.setMaxWidth(400);
        cardLabel.setMinHeight(100);
        cardLabel.setAlignment(Pos.CENTER);
        cardLabel.setPadding(new Insets(10));

        flipButton = new Button("Flip");
        flipButton.setOnAction(e -> toggleFlip());

        Button prevButton = new Button("Previous");
        prevButton.setOnAction(e -> previousCard());

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> nextCard());

        HBox navBox = new HBox(10, prevButton, flipButton, nextButton);
        navBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(16, indexLabel, cardLabel, navBox);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        updateCard();

        Scene scene = new Scene(root, 480, 300);
        primaryStage.setTitle("Flashcard Study App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCards() {
        File file = new File("cards.txt");
        if (!file.exists())
            return;

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines) {
                if (line.contains("|")) {
                    String[] parts = line.split("\\|", 2);
                    if (parts.length == 2) {
                        cards.add(parts);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCard() {
        String[] currentCard = cards.get(currentIndex);
        indexLabel.setText("Card " + (currentIndex + 1) + " / " + cards.size());

        if (isShowingQuestion) {
            cardLabel.setText(currentCard[0]);
            cardLabel.setStyle(
                    "-fx-background-color: lightblue; -fx-border-color: darkblue; -fx-border-radius: 5; -fx-background-radius: 5;");
        } else {
            cardLabel.setText(currentCard[1]);
            cardLabel.setStyle(
                    "-fx-background-color: lightgreen; -fx-border-color: darkgreen; -fx-border-radius: 5; -fx-background-radius: 5;");
        }
    }

    private void toggleFlip() {
        isShowingQuestion = !isShowingQuestion;
        updateCard();
    }

    private void nextCard() {
        currentIndex = (currentIndex + 1) % cards.size();
        isShowingQuestion = true;
        updateCard();
    }

    private void previousCard() {
        currentIndex = (currentIndex - 1 + cards.size()) % cards.size();
        isShowingQuestion = true;
        updateCard();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
