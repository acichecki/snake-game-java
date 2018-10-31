package com.codecool.snake.resources;

import com.codecool.snake.entities.powerups.SimplePowerUp;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Random;
import java.util.Date;

public class SpawnTimer extends TimerTask {

    static Timer timer = new Timer();

    @Override
    public void run() {
        int delay = (5 + new Random().nextInt(5)) * 1000;
        timer.schedule(new SpawnTimer(), delay);
        System.out.println(new Date());
        getChildren().add(new SimplePowerUp());
    }

}
