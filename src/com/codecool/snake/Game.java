package com.codecool.snake;

import com.codecool.snake.entities.enemies.BasiaEnemy;
import com.codecool.snake.entities.enemies.TadeuszEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class Game extends Pane {
    private Snake snake = null;
    private Snake snakeTwo = null;
    private GameTimer gameTimer = new GameTimer();
    private boolean isSinglePlayerMode;
    private Text text = new Text();

    public Game(boolean isSinglePlayerMode) {
        this.isSinglePlayerMode = isSinglePlayerMode;
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(5);
        spawnPowerUps(20);
        spawnBasias(5);
        spawnTadeuszes(5);
        getChildren().add(createMenu());
        if (isSinglePlayerMode) {
            GameLoop gameLoop = new GameLoop(snake);
            Globals.getInstance().setGameLoop(gameLoop);
            gameTimer.setup(gameLoop::step);
        } else {
            spawnSnakeTwo();
            GameLoop gameLoop = new GameLoop(snake, snakeTwo);
            Globals.getInstance().setGameLoop(gameLoop);
            gameTimer.setup(gameLoop::step);
        }
        gameTimer.play();
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

    private void spawnSnake() {
        snake = new Snake(new Vec2d(850, 500), KeyCode.A, KeyCode.D, "First");
    }

    private void spawnSnakeTwo() {
        snakeTwo = new Snake(new Vec2d(150, 500), KeyCode.LEFT, KeyCode.RIGHT, "Second");
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void spawnBasias(int numberOfBasias) {
        for(int i = 0; i < numberOfBasias; ++i) new BasiaEnemy();
    }

    private void spawnTadeuszes(int numberOfTadeuszes) {
        for(int i = 0; i < numberOfTadeuszes; ++i) new TadeuszEnemy();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Menu");

        MenuItem restart = new MenuItem("Restart", null);
        restart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                restart();
            }
        });
        MenuItem singleMode = new MenuItem("Single Player", null);
        singleMode.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                isSinglePlayerMode = true;
                restart();
            }
        });
        MenuItem twoPlayersMode = new MenuItem("Two Players", null);
        twoPlayersMode.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                isSinglePlayerMode = false;
                restart();
            }
        });
        menu.getItems().add(restart);
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(singleMode);
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(twoPlayersMode);
        menu.getItems().add(new SeparatorMenuItem());

        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void createHealthBar() {

        text.setText("Health: " + snake.getHealth() + '%');
        text.setX(0);
        text.setY(50);
        getChildren().add(text);
    }

    public void updateHealthBar() {
        text.setText("Health: " + snake.getHealth() + '%');
    }
}
