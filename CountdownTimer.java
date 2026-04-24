package org.example.Week13;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimer extends Application {

    private int remainingSeconds = 0;
    private Timeline timeline;
    private FadeTransition fadeTransition;

    private Label timeLabel;
    private Label errorLabel;
    private TextField minutesInput;
    private Button pauseResumeButton;

    @Override
    public void start(Stage primaryStage) {
        timeLabel = new Label("00:00");
        timeLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 52));

        minutesInput = new TextField();
        minutesInput.setPromptText("Enter minutes");
        minutesInput.setPrefWidth(100);

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startTimer());

        pauseResumeButton = new Button("Pause/Resume");
        pauseResumeButton.setOnAction(e -> togglePauseResume());

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> resetTimer());

        HBox inputBus = new HBox(10, minutesInput, startButton);
        inputBus.setAlignment(Pos.CENTER);

        HBox controlBox = new HBox(10, pauseResumeButton, resetButton);
        controlBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(16, inputBus, errorLabel, timeLabel, controlBox);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            remainingSeconds--;
            updateDisplay();
            if (remainingSeconds <= 0) {
                timeUp();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        fadeTransition = new FadeTransition(Duration.seconds(0.5), timeLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(Animation.INDEFINITE);

        Scene scene = new Scene(root, 340, 260);
        primaryStage.setTitle("Countdown Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTimer() {
        try {
            String input = minutesInput.getText().trim();
            int minutes = Integer.parseInt(input);
            if (minutes <= 0) {
                throw new NumberFormatException();
            }
            errorLabel.setText("");
            remainingSeconds = minutes * 60;
            updateDisplay();
            stopEffects();
            timeline.playFromStart();
        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid minutes! Please enter a positive integer.");
        }
    }

    private void togglePauseResume() {
        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
        } else if (remainingSeconds > 0) {
            timeline.play();
        }
    }

    private void resetTimer() {
        timeline.stop();
        remainingSeconds = 0;
        updateDisplay();
        stopEffects();
        errorLabel.setText("");
    }

    private void timeUp() {
        timeline.stop();
        timeLabel.setTextFill(Color.RED);
        fadeTransition.play();
    }

    private void stopEffects() {
        fadeTransition.stop();
        timeLabel.setOpacity(1.0);
        timeLabel.setTextFill(Color.BLACK);
    }

    private void updateDisplay() {
        int mins = remainingSeconds / 60;
        int secs = remainingSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d", mins, secs));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
