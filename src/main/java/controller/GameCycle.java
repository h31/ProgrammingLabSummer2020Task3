package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.*;
import view.Tetris;

import java.util.ArrayList;
import java.util.Random;

public class GameCycle extends GameField {
    FigureL figureL = new FigureL();
    FigureZ figureZ = new FigureZ();
    FigureT figureT = new FigureT();
    FigureO figureO = new FigureO();
    FigureI figureI = new FigureI();

    ArrayList<Figure> figures = new ArrayList<>();
    Random random = new Random();
    Figure figure;

    Button startButton = new Button("START");
    Button pauseButton = new Button("PAUSE");

    public GameCycle() {
        figures.add(figureL);
        figures.add(figureT);
        figures.add(figureZ);
        figures.add(figureI);
        figures.add(figureO);

        Tetris tetris = new Tetris();

        startButton.setStyle("-fx-background-color: #00ff00");
        startButton.setFont(new Font(20));
        startButton.setLayoutY(150);
        startButton.setLayoutX(450);

        pauseButton.setStyle("-fx-background-color: #ffa500");
        pauseButton.setFont(new Font(20));
        pauseButton.setLayoutY(300);
        pauseButton.setLayoutX(450);

        Lighting lighting = new Lighting();
        DropShadow shadow = new DropShadow();

        startButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> startButton.setEffect(shadow));

        startButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> startButton.setEffect(null));

        startButton.setOnAction(actionEvent -> {
            startButton.setEffect(lighting);
            startGame();
        });

        pauseButton.setOnAction(event -> {
        });

        getChildren().addAll(figureI, figureL, figureO, figureT, figureZ, tetris.getCanvas(), startButton);
        keyController();
    }

    private int keyPressedCount = 0;//отсчет нажатий клавиши

    public void startGame() {
        figure = figures.get(random.nextInt(figures.size()));//выбор случайной фигуры из списка

        if (endGame()) {
            repaintField();
            clearRow();
        }
    }

    public void keyController() {
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(210), t -> {
            if (figure == figureI) {
                figureI.moveDown();

                if (figureI.stop()) {
                    figure = figures.get(random.nextInt(figures.size()));
                    keyPressedCount = 0;
                }

            } else if (figure == figureL) {
                figureL.moveDown();

                if (figureL.stop()) {
                    figure = figures.get(random.nextInt(figures.size()));
                    keyPressedCount = 0;
                }
            } else if (figure == figureO) {
                figureO.moveDown();

                if (figureO.stop()) {
                    figure = figures.get(random.nextInt(figures.size()));
                }
            } else if (figure == figureT) {
                figureT.moveDown();

                if (figureT.stop()) {
                    figure = figures.get(random.nextInt(figures.size()));
                    keyPressedCount = 0;
                }
            } else if (figure == figureZ) {
                figureZ.moveDown();

                if (figureZ.stop()) {
                    figure = figures.get(random.nextInt(figures.size()));
                    keyPressedCount = 0;
                }
            }

            startButton.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT) {
                    if (figure == figureI) {
                        figureI.moveLeft();
                    } else if (figure == figureL) {
                        figureL.moveLeft();
                    } else if (figure == figureO) {
                        figureO.moveLeft();
                    } else if (figure == figureT) {
                        figureT.moveLeft();
                    } else if (figure == figureZ) {
                        figureZ.moveLeft();
                    }

                } else if (event.getCode() == KeyCode.RIGHT) {
                    if (figure == figureL) {
                        figureL.moveRight();
                    } else if (figure == figureI) {
                        figureI.moveRight();
                    } else if (figure == figureO) {
                        figureO.moveRight();
                    } else if (figure == figureT) {
                        figureT.moveRight();
                    } else if (figure == figureZ) {
                        figureZ.moveRight();
                    }
                } else if (event.getCode() == KeyCode.UP) {
                    if (figure == figureI) {
                        keyPressedCount++;
                        if (keyPressedCount == 1) {
                            figureI.changeForm();
                        } else if (keyPressedCount == 2) {
                            figureI.returnForm();
                            keyPressedCount = 0;
                        }
                    } else if (figure == figureL) {
                        keyPressedCount++;
                        if (keyPressedCount == 1) {
                            figureL.changeFirstForm();
                        } else if (keyPressedCount == 2) {
                            figureL.changeSecondForm();
                        } else if (keyPressedCount == 3) {
                            figureL.changeThirdForm();
                        } else if (keyPressedCount == 4) {
                            figureL.returnForm();
                            keyPressedCount = 0;
                        }
                    } else if (figure == figureT) {
                        keyPressedCount++;
                        if (keyPressedCount == 1) {
                            figureT.changeFirstForm();
                        } else if (keyPressedCount == 2) {
                            figureT.changeSecondForm();
                        } else if (keyPressedCount == 3) {
                            figureT.changeThirdForm();
                        } else if (keyPressedCount == 4) {
                            figureT.returnToDefaultForm();
                            keyPressedCount = 0;
                        }
                    } else if (figure == figureZ) {
                        keyPressedCount++;
                        if (keyPressedCount == 1) {
                            figureZ.changeForm();
                        } else if (keyPressedCount == 2) {
                            figureZ.returnForm();
                            keyPressedCount = 0;
                        }
                    }
                }
            });

            endGame();
            clearRow();
            drawFigure();

        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

}