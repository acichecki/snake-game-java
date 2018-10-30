package com.codecool.snake.gameResult;

import com.codecool.snake.entities.snakes.SnakeHead;

public class GameOverMonitor {
    public boolean isSnakeDead(int health, SnakeHead head) {

        if (head.isOutOfBounds() || health <= 0) {
            return true;
        } else return false;
    }
}
