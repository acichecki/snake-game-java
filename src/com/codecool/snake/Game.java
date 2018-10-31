package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.codecool.snake.resources.SpawnTimer;
import com.sun.javafx.geom.Vec2d;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();



    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(1);
        spawnPowerUps(0);
        getChildren().add(createMenu());
        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
        new SpawnTimer().run();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    public void restart() {
        Globals.getInstance().display.clear();
        Globals.getInstance().getGameLoop().stop();
        init();
        start();
    }

    public void setBackground(Image backgroundImage) {

        setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Menu");

        MenuItem newItem = new MenuItem("Restart", null);
        newItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                restart();
            }
        });

        menu.getItems().add(newItem);
        menu.getItems().add(new SeparatorMenuItem());

        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void sendMessage() { System.out.println("Message");}

}
