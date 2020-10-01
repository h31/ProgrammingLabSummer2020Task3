package controller;

import model.Logic;
import view.View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import static model.Logic.rotate;

public class Controller {
    private View view;
    private Logic logic;
    private KeyCode keyPressed;
    private boolean winner;
    int[][] myBoard = new int[4][4];

    public Controller(Group group, Scene scene) {

        this.view = new View();
        view.createMenu(group);
        //Создание кнопок для главного меню
        View.MenuButton buttonStart = new View.MenuButton("Start");
        View.MenuButton buttonExit = new View.MenuButton("Exit");
        //Создание кнопок для внутриигрового меню
        View.MenuButton buttonCont = new View.MenuButton("Continue");
        View.MenuButton buttonRest = new View.MenuButton("Restart");

        group.getChildren().add(view.vBoxMenu(buttonStart, buttonExit));

        buttonStart.setOnMouseClicked(mouseEvent -> {
            logic = new Logic(myBoard);
            logic.start(myBoard);
            view.drawBoard(group, myBoard, logic.getScore());
            scene.setOnKeyPressed(keyEvent -> {
                keyPressed = keyEvent.getCode();
                if (!winner) {
                    switch (keyPressed) {
                        case ESCAPE:
                            group.getChildren().add(view.vBoxGameMenu(buttonCont, buttonRest, buttonExit));
                            break;
                        case UP:
                        case LEFT:
                            logic.move(myBoard, keyPressed.getName());
                            logic.randomCell(myBoard);
                            view.drawBoard(group, myBoard, logic.getScore());
                            break;
                        case DOWN:
                        case RIGHT:
                            myBoard = rotate(myBoard);
                            myBoard = rotate(myBoard);
                            logic.move(myBoard, keyPressed.getName());
                            myBoard = rotate(myBoard);
                            myBoard = rotate(myBoard);
                            logic.randomCell(myBoard);
                            view.drawBoard(group, myBoard, logic.getScore());
                            break;
                    }
                }
                if (logic.isWin(myBoard)) {
                    winner = true;
                    view.createWin(group);
                    group.getChildren().add(view.vBoxWin(buttonRest, buttonExit));
                }
            });
        });
        buttonRest.setOnMouseClicked(mouseEvent -> {
            logic.start(myBoard);
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(view.vBoxGameMenu(buttonCont, buttonRest, buttonExit));
            winner = false;
        });
        buttonCont.setOnMouseClicked(mouseEvent -> {
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(view.vBoxGameMenu(buttonCont, buttonRest, buttonExit));
        });
        buttonExit.setOnMouseClicked(mouseEvent -> System.exit(0));
    }
}

