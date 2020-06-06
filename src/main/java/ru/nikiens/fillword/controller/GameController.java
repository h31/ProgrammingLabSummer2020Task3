package ru.nikiens.fillword.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.*;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.StackPane;
import ru.nikiens.fillword.model.Cell;
import ru.nikiens.fillword.model.CellState;
import ru.nikiens.fillword.model.Game;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class GameController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label timer;

    @FXML
    private GridPane table;

    @FXML
    private Label category;

    @FXML
    private JFXListView<String> wordsList;

    @FXML
    private StackPane stackPane;

    private ObservableSet<Cell> selectedCells = FXCollections.observableSet(new LinkedHashSet<>());
    private PseudoClass selected = PseudoClass.getPseudoClass("selected");

    private Set<String> words = Game.getInstance().getWords();

    private final int BOARD_SIZE = Game.getInstance().getBoardSize().value();

    public void initialize(URL location, ResourceBundle resources) {
        GridLocation dragged = new GridLocation();
        Game.getInstance().initializeBoard();
        Game.getInstance().fillWithWords();
        //Game.getInstance().fillWithLetters();

        wordsList.setItems(FXCollections.observableArrayList(Game.getInstance().getWords()));
        wordsList.setMouseTransparent(true);
        wordsList.setFocusTraversable(false);
        wordsList.setCellFactory(lc -> createWordCell());

        category.setText(Game.getInstance().getCategory());

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                table.add(generateLabel(i, j, dragged), i, j);
            }
        }

        selectedCells.addListener((SetChangeListener.Change<? extends Label> change) -> {
            if (change.wasAdded()) {
                Label label = change.getElementAdded();
                label.pseudoClassStateChanged(selected, true);
            } else if (change.wasRemoved()) {
                Label label = change.getElementRemoved();
                label.pseudoClassStateChanged(selected, false);
            }
        });
    }

    private ListCell<String> createWordCell() {
        return new ListCell<>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null) {
                    setText(item);

                    if (!words.contains(item)) {
                        pseudoClassStateChanged(selected, true);
                    }
                } else {
                    setText(null);
                }
            }
        };
    }

    private Cell generateLabel(int x, int y, GridLocation gridLocation) {
        Cell cell = Game.getInstance().getCell(y, x);

        cell.prefHeightProperty().bind(table.heightProperty().divide(BOARD_SIZE));
        cell.prefWidthProperty().bind(table.widthProperty().divide(BOARD_SIZE));
        cell.setAlignment(Pos.CENTER);

        cell.setOnDragDetected(event -> {
            gridLocation.x = x;
            gridLocation.y = y;

            selectedCells.clear();
            selectedCells.add(cell);
            cell.startFullDrag();
        });

        cell.setOnMouseDragEntered(event -> recomputeSelection(gridLocation, x, y));
        cell.setOnMouseDragReleased(event -> processSelection());

        return cell;
    }

    private void recomputeSelection(GridLocation dragged, int x, int y) {
        Set<Cell> selection = new HashSet<>();
        Set<Cell> horizontalSelection = new HashSet<>();
        Set<Cell> diagonalSelection = new HashSet<>();

        for (int j = dragged.y; j <= y; j++) {
            for (int i = dragged.x; i <= x; i++) {
                Cell cell = (Cell) table.getChildren().get(i * BOARD_SIZE + j);

                if (cell.getState() == CellState.MARKED) return;

                if (j == dragged.y) {
                    selection.add(cell);
                }
                if (i == dragged.x) {
                    horizontalSelection.add(cell);
                    selection.clear();
                    selection.addAll(horizontalSelection);
                }
                if (j - i == dragged.y - dragged.x) {
                    diagonalSelection.add(cell);
                    selection.clear();
                    selection.addAll(diagonalSelection);
                }
            }
        }
        selectedCells.retainAll(selection);
        selectedCells.addAll(selection);
    }

    private void processSelection() {
        String word = selectedCells.stream().map(Labeled::getText).collect(Collectors.joining());

        if (words.contains(word)) {
            words.remove(word);
            wordsList.refresh();

            selectedCells.forEach(it -> it.setState(CellState.MARKED));
        }

        selectedCells.clear();

        if (words.isEmpty()) {
            finishGame();
        }
    }

    private void finishGame() {

    }

    private static class GridLocation {
        int x, y;
    }
}