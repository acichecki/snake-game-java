package com.codecool.snake.entities.enemies;

import com.codecool.snake.Utils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Move {
    public Move(BasiaEnemy object, double time, double direction, int speed, int step) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), event -> {
            object.setHeading(Utils.directionToVector(direction, speed));
            object.setStep(step);
            object.run(direction);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
