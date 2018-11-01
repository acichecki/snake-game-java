package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Spawner {
    public Spawner(String objectToSpawn, double time, int number) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), event -> {
            Globals.getInstance().game.spawn(objectToSpawn, number);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
