package controller;

import model.Logic;
import view.View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

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
        View.MenuButton buttonStart = new View.MenuButton("START");
        View.MenuButton buttonExit = new View.MenuButton("EXIT");
        //Создание кнопок для внутриигрового меню
        View.MenuButton buttonCont = new View.MenuButton("Continue");
        View.MenuButton buttonRest = new View.MenuButton("Restart");
        View.MenuButton buttonQuit = new View.MenuButton("Quit");
        group.getChildren().add(view.vBoxMenu(buttonStart, buttonExit));

        buttonStart.setOnMouseClicked(mouseEvent -> {
            logic = new Logic(myBoard);
            view.createBoard(group);
            logic.start(myBoard);
            view.drawBoard(group, myBoard, logic.getScore());
            scene.setOnKeyPressed(keyEvent -> {
                keyPressed = keyEvent.getCode();
                if (!winner) {
                    switch (keyPressed) {
                        case ESCAPE:
                            group.getChildren().add(view.vBoxGameMenu(buttonCont, buttonRest, buttonQuit));
                            break;
                        case UP:
                        case LEFT:
                            logic.move(myBoard, keyPressed.getName());
                            logic.randomCell(myBoard);
                            view.drawBoard(group, myBoard, logic.getScore());
                            break;
                        case DOWN:
                        case RIGHT:
                            myBoard = logic.rotate(myBoard);
                            myBoard = logic.rotate(myBoard);
                            logic.move(myBoard, keyPressed.getName());
                            myBoard = logic.rotate(myBoard);
                            myBoard = logic.rotate(myBoard);
                            logic.randomCell(myBoard);
                            view.drawBoard(group, myBoard, logic.getScore());
                            break;
                    }
                }
                if (logic.isWin(myBoard)) {
                    winner = true;
                    view.createWin(group);
                    group.getChildren().add(view.vBoxWin(buttonRest, buttonQuit));
                }
            });
        });
        buttonQuit.setOnMouseClicked(mouseEvent -> System.exit(0));
        buttonRest.setOnMouseClicked(mouseEvent -> {
            logic.start(myBoard);
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(view.vBoxGameMenu(buttonCont, buttonRest, buttonQuit));
            winner = false;
        });
        buttonCont.setOnMouseClicked(mouseEvent -> {
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(view.vBoxGameMenu(buttonCont, buttonRest, buttonQuit));
        });
        buttonExit.setOnMouseClicked(mouseEvent -> System.exit(0));
    }
}

