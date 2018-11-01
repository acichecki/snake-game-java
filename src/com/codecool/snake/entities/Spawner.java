package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class Spawner {
    public Spawner(Object object, double time, int number) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), event -> {
            Globals.getInstance().game.spawn(object, number);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
