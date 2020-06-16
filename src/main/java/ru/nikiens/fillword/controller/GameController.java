package ru.nikiens.fillword.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;

import javafx.collections.*;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import ru.nikiens.fillword.model.Cell;
import ru.nikiens.fillword.model.Game;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class GameController implements Initializable {

    @FXML
    private GridPane table;

    @FXML
    private Label category;

    @FXML
    private JFXListView<String> wordsList;

    @FXML
    private StackPane stackPane;

    private PseudoClass selected = PseudoClass.getPseudoClass("selected");
    private PseudoClass marked = PseudoClass.getPseudoClass("marked");

    private ObservableSet<Label> selectedCells = FXCollections.observableSet(new LinkedHashSet<>());

    private final int BOARD_SIZE = Game.getInstance().getBoardSize().value();

    public void initialize(URL location, ResourceBundle resources) {
        GridLocation dragged = new GridLocation();

        Game.getInstance().initializeBoard();
        Game.getInstance().fillWithWords();
        Game.getInstance().fillWithLetters();

        wordsList.setItems(FXCollections.observableArrayList(Game.getInstance().getWords()));
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
                    setId("wordCell-" + item);
                } else {
                    setText(null);
                }
            }
        };
    }

    private Label generateLabel(int x, int y, GridLocation gridLocation) {
        Label label = new Label(Character.toString(Game.getInstance().getCell(x, y).getLetter()));

        label.prefHeightProperty().bind(table.heightProperty().divide(BOARD_SIZE));
        label.prefWidthProperty().bind(table.widthProperty().divide(BOARD_SIZE));
        label.setAlignment(Pos.CENTER);

        label.setOnDragDetected(event -> {
            gridLocation.x = x;
            gridLocation.y = y;

            selectedCells.clear();
            selectedCells.add(label);
            label.startFullDrag();
        });

        label.setOnMouseDragEntered(event -> recomputeSelection(gridLocation, x, y));
        label.setOnMouseDragReleased(event -> processSelection());

        return label;
    }

    private void recomputeSelection(GridLocation dragged, int x, int y) {
        Set<Label> selection = new LinkedHashSet<>();
        Set<Label> horizontalSelection = new LinkedHashSet<>();
        Set<Label> diagonalSelection = new LinkedHashSet<>();

        for (int j = dragged.y; j <= y; j++) {
            for (int i = dragged.x; i <= x; i++) {
                Label label = (Label) table.getChildren().get(i * BOARD_SIZE + 1 + j);

                if (Game.getInstance().getCell(i, j).getState() == Cell.State.MARKED) {
                    return;
                }

                if (j == dragged.y) {
                    selection.add(label);
                }
                if (i == dragged.x) {
                    horizontalSelection.add(label);
                    selection.clear();
                    selection.addAll(horizontalSelection);
                }
                if (j - i == dragged.y - dragged.x) {
                    diagonalSelection.add(label);
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

        if (Game.getInstance().getWords().contains(word)) {
            Game.getInstance().getWords().remove(word);
            wordsList.lookup("#wordCell-" + word).pseudoClassStateChanged(selected, true);

            selectedCells.forEach(it -> {
                int y = GridPane.getRowIndex(it);
                int x = GridPane.getColumnIndex(it);

                Game.getInstance().getCell(x, y).setState(Cell.State.MARKED);
                it.pseudoClassStateChanged(marked, true);
            });
        }

        selectedCells.clear();

        if (Game.getInstance().getWords().isEmpty()) {
            finishGame();
        }
    }

    private void finishGame() {
        stackPane.setVisible(true);

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("You won!"));
        content.setBody(new Text("You have successfully completed the level!"));

        JFXButton button = new JFXButton("Return to menu");
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        button.setOnAction(event -> {
            try {
                returnToMenu();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Cannot return to main menu");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });
        button.setButtonType(JFXButton.ButtonType.RAISED);

        content.setActions(button);

        dialog.setId("endingDialog");
        dialog.show();
    }

    private void returnToMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Fillword");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        stackPane.getScene().getWindow().hide();
        Game.getInstance().getWords().clear();
    }

    @FXML
    void onMenuButton() {
        stackPane.setVisible(true);

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Are you sure?"));
        content.setBody(new Text("Do you really want to quit?"));

        JFXButton yesButton = new JFXButton("Yes");
        JFXButton noButton = new JFXButton("No");
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        yesButton.setOnAction(event -> {
            try {
                returnToMenu();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Cannot return to main menu");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        noButton.setOnAction(event -> dialog.close());

        content.setActions(yesButton, noButton);

        dialog.setId("confirmationDialog");
        dialog.setOnDialogClosed(event -> stackPane.setVisible(false));
        dialog.show();
    }

    private static class GridLocation {
        int x, y;
    }
}