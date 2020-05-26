package com.example.project;

import com.example.project.Board;
import com.example.project.Chip;
import com.example.project.Game_checkers;
import com.example.project.MoveGenerator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;


public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Game_checkers game;
    /** Последняя точка, которую текущий игрок выбрал на поле */
    private Point selected;

    private boolean selectionValid;

    /** Флаг, показывающий если ход первого игрока */
    private boolean isP1Turn;

    private int skipIndex;

    Label response;
    Board board = new Board();
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.game = (game == null)? new Game_checkers() : game;

        FlowPane root = new FlowPane(10, 10); //установить промежуток между элементами управления по гор. и вер.
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Russian Checkers");
        primaryStage.setScene(scene);
        //указать метку
        response = new Label("Push a button");

        Button btnStart = new Button("Start");
        Button btnQuit = new Button("Quit");

        //обработка события от действия кнопки Start
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                root.getChildren().removeAll(btnStart, btnQuit, response);
                GridPane gridPane = new GridPane(); //Прорисовываем поле
                Scene sceneBoard = new Scene(gridPane, 400, 400);
                gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    if (e.getClickCount() == 2) {
                        Node source = e.getPickResult().getIntersectedNode();
                        Integer colIndex = gridPane.getColumnIndex(source);
                        Integer rowIndex = gridPane.getRowIndex(source);
                        Game_checkers copy = game.copy();
                        Point sel = new Point(rowIndex, colIndex);
                        if (Board.isValidPoint(sel) && Board.isValidPoint(selected)){
                            boolean change = copy.isP1Turn();
                            if (change) {
                                primaryStage.setTitle("Russian Checkers Turn Blue");
                            }
                            else {
                                primaryStage.setTitle("Russian Checkers Turn Red");
                            }
                            String expected = copy.getGameState();
                            boolean move = copy.move(selected, sel);
                            boolean updated = (move?
                                    setGameState(true, copy.getGameState(), expected) : false);
                            change = (copy.isP1Turn() != change);
                            selected = change? null : sel;
                        }else {
                            selected = sel;
                        }
                        // Проверяем, если выбор валидный
                        selectionValid = isValidSelection(
                                copy.getBoard(), copy.isP1Turn(), selected);

                        Board b = game.getBoard();
                        drawCheckers(b, gridPane);
                        if (copy.isGameOver()) {//
                            FlowPane root1 = new FlowPane(10, 10); //установить промежуток между элементами управления по гор. и вер.
                            root1.setAlignment(Pos.CENTER);
                            Label gameOver = new Label("GAME OVER!");
                            gameOver.setFont(Font.font(30));
                            Button restart = new Button("restart");
                            restart.setFont(Font.font(15));
                            root1.getChildren().addAll(gameOver, restart);
                            Scene game_Over = new Scene(root1, 400, 400);
                            primaryStage.setScene(game_Over);
                            restart.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    root1.getChildren().removeAll(gameOver, restart);
                                    game.restart();
                                    primaryStage.setScene(sceneBoard);
                                    drawCheckers(game.getBoard(), gridPane);
                                }
                            });
                        }
                        board.selectCellGraphics(rowIndex, colIndex, gridPane);

                    }
                        });
                drawCheckers(board, gridPane);
                primaryStage.setScene(sceneBoard);
            }
        });
        //обработка события от действия кнопки Quit
        btnQuit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                System.exit(0);
            }
        });
        root.getChildren().addAll(btnStart, btnQuit, response);
        primaryStage.show();
    }

    private void drawCheckers(Board b, GridPane gridPane){
        for (int i = 0; i < b.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                Rectangle rectangle = new Rectangle(50,50);
                Circle circle = new Circle(25,25,20);
                if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 != 0) && (j % 2 != 0))) {
                    rectangle.setFill(Color.LIGHTGREY);
                }
                else {
                    rectangle.setFill(Color.BLACK);
                }
                gridPane.add(rectangle, j, i);
                if (b.getCell(i, j) == Chip.WHITE) {
                    circle.setFill(Color.AQUA);
                    gridPane.add(circle, j, i);
                }
                else if (b.getCell(i, j) == Chip.BLACK) {
                    circle.setFill(Color.RED);
                    gridPane.add(circle, j, i);
                }
                else if (b.getCell(i, j) == Chip.WHITE_KING) {
                    circle.setFill(Color.AQUA);
                    circle.setStroke(Color.SILVER);
                    circle.setStrokeWidth(3.0);
                    gridPane.add(circle, j, i);
                }
                else if (b.getCell(i, j) == Chip.BLACK_KING) {
                    circle.setFill(Color.RED);
                    circle.setStroke(Color.SILVER);
                    circle.setStrokeWidth(3.0);
                    gridPane.add(circle, j, i);
                }
            }
        }
    }

    /**
     * Проверяет, если выбранноя точка валидна игрока, которому принадлежит ход
     * @param b			текущее поле.
     * @param isP1Turn	флаг, показывающий, что ход первого игрока
     * @param selected	тестируемая точка
     * @return возращает истину, если только выбранная точка - шашка, которой можно сделать ход в текущем ходе.
     */
    private boolean isValidSelection(Board b, boolean isP1Turn, Point selected) {

        // Простые случаи
        int i = Board.toIndex(selected);
        Chip id = b.get(i);
        if (id == Chip.BLANK || id == Chip.INVALID) { // нет шашек здесь
            return false;
        } else if(isP1Turn ^ (id == Chip.BLACK ||
                id == Chip.BLACK_KING)) { // неверная шашка
            return false;
        } else if (!MoveGenerator.getSkips(b, i).isEmpty()) { // возможно пропустить(skip)
            return true;
        } else if (MoveGenerator.getMoves(b, i).isEmpty()) { // нет ходов
            return false;
        }

        // Описывает, если доступен пропуск(skip) для другой шашки
        List<Point> points = b.find(
                isP1Turn? Chip.BLACK : Chip.WHITE);
        points.addAll(b.find(
                isP1Turn? Chip.BLACK_KING : Chip.WHITE_KING));
        for (Point p : points) {
            int checker = Board.toIndex(p);
            if (checker == i) {
                continue;
            }
            if (!MoveGenerator.getSkips(b, checker).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public synchronized boolean setGameState(boolean testValue,
                                             String newState, String expected) {

        // Тестирует значение, если требуется
        if (testValue && !game.getGameState().equals(expected)) {
            return false;
        }

        // Обновляет состояние игры
        this.game.setGameState(newState);

        return true;
    }
}
