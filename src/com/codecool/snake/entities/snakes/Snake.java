package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {
    private static final float speed = 2;
    private int health = 100;
    private KeyCode left;
    private KeyCode right;
    private String name;
    private SnakeHead currentHead;

    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position, KeyCode left, KeyCode right, String name) {
        this.left = left;
        this.right = right;
        this.name = name;
        currentHead = new SnakeHead(this, position);
        if (this.name.equals("First")) {
            Globals.getInstance().snakeHead = currentHead;
        }
        if (this.name.equals("Second")) {
            Globals.getInstance().snakeHeadTwo = currentHead;
        }
        this.body = new DelayedModificationList<>();
        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        currentHead.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();

        this.body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(this.left)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(this.right)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            this.body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(Globals.getInstance().snakeHead);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForGameOverConditions() {
        if (Globals.getInstance().snakeHead.isOutOfBounds() && Globals.getInstance().snakeHeadTwo.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
        }
        if (Globals.getInstance().snakeHead.isOutOfBounds()) {
            Globals.getInstance().snakeHead.destroy();
            for (GameEntity currentPart : this.body.getList()){
                if (currentHead == Globals.getInstance().snakeHead) {
                    Globals.getInstance().display.remove(currentPart);
                }
            }
        }
        if (Globals.getInstance().snakeHeadTwo.isOutOfBounds()) {
            Globals.getInstance().snakeHeadTwo.destroy();
            for (GameEntity currentPart : this.body.getList()){
                if (currentHead == Globals.getInstance().snakeHeadTwo) {
                    Globals.getInstance().display.remove(currentPart);
                }
            }
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = currentHead;
        for (GameEntity currentPart : this.body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = this.body.getLast();

        if(result != null) return result;
        return currentHead;
    }
}
