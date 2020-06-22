package Controller;

import Model.Logic;
import View.View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Controller {
    private View view;
    private Logic logic;
    private KeyCode keyPressed;

    public Controller(Group group, Scene scene) {
        this.view = new View();
        int[][] myBoard = new int[4][4];
        view.createMenu(group);

        //Создание кнопок для главного меню
        View.MenuButton buttonStart = new View.MenuButton("START");
        View.MenuButton buttonExit = new View.MenuButton("EXIT");

        //Создание кнопок для внутриигрового меню
        View.MenuButton buttonCont = new View.MenuButton("Continue");
        View.MenuButton buttonRest = new View.MenuButton("Restart");
        View.MenuButton buttonQuit = new View.MenuButton("Quit");

        group.getChildren().add(view.vBoxMenu(buttonStart,buttonExit));
        buttonStart.setOnMouseClicked(mouseEvent -> {
            logic = new Logic(myBoard);
            view.createBoard(group);
            logic.start();
            view.drawBoard(group,myBoard,logic.getScore());
            scene.setOnKeyPressed(keyEvent -> {
                keyPressed = keyEvent.getCode();
                switch (keyPressed) {
                    case ESCAPE:
                        group.getChildren().add(view.vBoxGameMenu(buttonCont,buttonRest,buttonQuit));
                        break;
                    case UP:
                        logic.up();
                        logic.randomCell(myBoard);
                        view.drawBoard(group, myBoard, logic.getScore());
                        break;
                    case DOWN:
                        logic.down();
                        logic.randomCell(myBoard);
                        view.drawBoard(group, myBoard, logic.getScore());
                        break;
                    case RIGHT:
                        logic.right();
                        logic.randomCell(myBoard);
                        view.drawBoard(group, myBoard, logic.getScore());
                        break;
                    case LEFT:
                        logic.left();
                        logic.randomCell(myBoard);
                        view.drawBoard(group, myBoard, logic.getScore());
                        break;
                }
                if (logic.isWin(myBoard)) {
                    view.createWin(group);
                    group.getChildren().add(view.vBoxWin(buttonRest,buttonQuit));
                }
            });
        });
        buttonQuit.setOnMouseClicked(mouseEvent -> System.exit(0));
        buttonRest.setOnMouseClicked(mouseEvent -> {
            logic.start();
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(view.vBoxGameMenu(buttonCont,buttonRest,buttonQuit));
        });
        buttonCont.setOnMouseClicked(mouseEvent -> {
            view.vBoxGameMenu(buttonCont,buttonRest,buttonQuit).setManaged(false);
            view.vBoxGameMenu(buttonCont,buttonRest,buttonQuit).setVisible(false);
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(view.vBoxGameMenu(buttonCont,buttonRest,buttonQuit));
        });
        buttonExit.setOnMouseClicked(mouseEvent -> System.exit(0));
    }
}

