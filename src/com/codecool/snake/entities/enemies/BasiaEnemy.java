package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public class BasiaEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();

    public BasiaEnemy() {
        super(15);

        setImage(Globals.getInstance().getImage("BasiaEnemy"));
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

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
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
        return (getDamage() + " damage");
    }
}
