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
import java.util.Objects;
import java.util.Random;

public class GameCycle extends GameField {
    FigureL figureL = new FigureL();
    FigureZ figureZ = new FigureZ();
    FigureT figureT = new FigureT();
    FigureO figureO = new FigureO();
    FigureI figureI = new FigureI();
    private int keyPressedCount = 0;//отсчет нажатий клавиши

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

        //эффекты для кнопок
        DropShadow shadow = new DropShadow();

        startButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> startButton.setEffect(shadow));

        startButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> startButton.setEffect(null));

        getChildren().addAll(figureI, figureL, figureO, figureT, figureZ, tetris.getCanvas(), startButton);
        keyController();
    }

    public void keyController() {
        Lighting lighting = new Lighting();

        startButton.setOnAction(actionEvent -> {
            startButton.setEffect(lighting);
            figure = figures.get(random.nextInt(figures.size()));
        });

        Timeline loop = new Timeline(new KeyFrame(Duration.millis(250), t -> {
            //движение фигуры вниз и проверка остановки
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
            //обработка нажатий с клаввиатуры
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
                }
                else if (event.getCode()==KeyCode.DOWN){
                    if (figure==figureI) {
                        figureI.acceleration();
                    }
                }

                else if (event.getCode() == KeyCode.UP) {
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

            //проверка на окончание игры
            if (!endGame()) {
                clearRow();
                drawFigure();
            } else {
                getEndGame().setVisible(true);
                if (startButton.isPressed()) {
                    repaintField();
                    getEndGame().setVisible(false);
                    getScore().setText("0");
                }
            }

        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCycle gameCycle = (GameCycle) o;
        return keyPressedCount == gameCycle.keyPressedCount &&
                Objects.equals(figureL, gameCycle.figureL) &&
                Objects.equals(figureZ, gameCycle.figureZ) &&
                Objects.equals(figureT, gameCycle.figureT) &&
                Objects.equals(figureO, gameCycle.figureO) &&
                Objects.equals(figureI, gameCycle.figureI) &&
                Objects.equals(figures, gameCycle.figures) &&
                Objects.equals(random, gameCycle.random) &&
                Objects.equals(figure, gameCycle.figure) &&
                Objects.equals(startButton, gameCycle.startButton) &&
                Objects.equals(pauseButton, gameCycle.pauseButton);
    }

    @Override
    public int hashCode() {
        return Objects.hash(figureL, figureZ, figureT, figureO, figureI, keyPressedCount, figures, random, figure, startButton, pauseButton);
    }

    @Override
    public String toString() {
        return "GameCycle{" +
                "figureL=" + figureL +
                ", figureZ=" + figureZ +
                ", figureT=" + figureT +
                ", figureO=" + figureO +
                ", figureI=" + figureI +
                ", keyPressedCount=" + keyPressedCount +
                ", figures=" + figures +
                ", random=" + random +
                ", figure=" + figure +
                ", startButton=" + startButton +
                ", pauseButton=" + pauseButton +
                '}';
    }
}