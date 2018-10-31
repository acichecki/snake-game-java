package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpBerry"));
        double xCoord = 0;
        double yCoord = 0;

        int safeDistanceToCentralPoint = 40;
        double xStartHead = Globals.getInstance().snakeHead.getX() - safeDistanceToCentralPoint;
        double xEndHead = Globals.getInstance().snakeHead.getX() + safeDistanceToCentralPoint;
        double yStartHead = Globals.getInstance().snakeHead.getY() - safeDistanceToCentralPoint;
        double yEndHead = Globals.getInstance().snakeHead.getY() + safeDistanceToCentralPoint;

        while (xCoord == 0 && yCoord == 0) {
            xCoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            yCoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
            if (xCoord > xStartHead && xCoord < xEndHead &&
                yCoord > yStartHead && yCoord < yEndHead) {
                xCoord = 0;
                yCoord = 0;
            }
        }

        setX(xCoord);
        setY(yCoord);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
