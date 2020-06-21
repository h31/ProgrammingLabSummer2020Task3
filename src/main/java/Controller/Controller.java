package Controller;

import Model.Logic;
import View.View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller {
    private View view;
    private Logic logic;
    private KeyCode keyPressed;

    public Controller(Group group, Scene scene) {
        this.view = new View(group);
        int[][] myBoard = new int[4][4];
        view.createMenu(group);

        VBox menu = new VBox(30);
        View.MenuButton buttonStart = new View.MenuButton("START");
        View.MenuButton buttonExit = new View.MenuButton("EXIT");
        menu.setTranslateX(350);
        menu.setTranslateY(400);
        menu.getChildren().addAll(buttonStart, buttonExit);
        group.getChildren().addAll(menu);

        VBox pause = new VBox(20);
        Text text = new Text("Вы действительно хотите выйти из игры ?");
        text.setFill(Color.YELLOW);
        text.setFont(Font.font("Arial", 25));
        View.MenuButton buttonCont = new View.MenuButton("Continue");
        View.MenuButton buttonRest = new View.MenuButton("Restart");
        View.MenuButton buttonQuit = new View.MenuButton("Quit");
        pause.setTranslateX(350);
        pause.setTranslateY(400);
        pause.getChildren().addAll(buttonCont,buttonRest, buttonQuit);

        buttonQuit.setOnMouseClicked(mouseEvent -> System.exit(0));
        buttonRest.setOnMouseClicked(mouseEvent -> {
            logic.start();
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(pause);
        });
        buttonCont.setOnMouseClicked(mouseEvent -> {
            pause.setVisible(false);
            view.drawBoard(group, myBoard, logic.getScore());
            group.getChildren().remove(pause);
        });

        buttonStart.setOnMouseClicked(mouseEvent -> {
            logic = new Logic(myBoard);
            view.createBoard(group);
            logic.start();
            view.drawBoard(group,myBoard,logic.getScore());
            scene.setOnKeyPressed(keyEvent -> {
                keyPressed = keyEvent.getCode();
                switch (keyPressed) {
                    case ESCAPE:
                        group.getChildren().add(pause);
                        pause.setVisible(true);
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
                }
            });
        });
        buttonExit.setOnMouseClicked(mouseEvent -> System.exit(0));
    }
}

