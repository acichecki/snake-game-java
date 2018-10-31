package com.codecool.snake.gameResult;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameOverHandler {
    public boolean gameOverCheckCondition(int health, SnakeHead head) {

        if (head.isOutOfBounds() || health <= 0) {
            return true;
        } else return false;
    }

    public void displayGameOverMessage(){
        Text message = new Text("Game over!");
        message.setStyle("-fx-font: 24 arial;");
        message.setTextAlignment(TextAlignment.CENTER);
        Globals.getInstance().display.displayText(message);
    }
}
