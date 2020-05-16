package BG_control;

import BG_model.Column;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class Turn  {
    private int turnCount = 1;

    public void startTurn(GridPane board){
        newTurnAlert();
        List<Integer> moveList = moveList(diceRoll());
        board.add(diceView(diceRoll()),15,0);
        //while (!moveList.isEmpty()){
            //TODO()
        //}

    }
    private void newTurnAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Turn:"+(turnCount+1)/29);
        alert.setHeaderText("Player "+ (playerNumber()+1) +" turn");
        alert.setContentText(null);
        turnCount++;
        alert.showAndWait();
    }

    private int[] diceRoll(){
        int d1 = (int) (Math.random()*6) + 1;
        int d2 = (int) (Math.random()*6) + 1;
        if (d1 == d2) return new int[]{d1,d1,d2,d2};
        else return new int[]{d1, d2};
    }

    private List<Integer> moveList (int[] dices){
        List<Integer> moveList = new ArrayList<Integer>();
        if (dices.length == 2){
            moveList.add(dices[0]);
            moveList.add(dices[1]);
            moveList.add(dices[0]+dices[1]);

        } else {
            moveList.add(dices[0]);
            moveList.add(dices[0]);
            moveList.add(dices[0]);
            moveList.add(dices[0]);
            moveList.add(dices[0]*2);
            moveList.add(dices[0]*2);
            moveList.add(dices[0]*3);
            moveList.add(dices[0]*4);
        }  return moveList;
    }

    private Canvas dicePrint(int i){
        Canvas canvas = new Canvas(55,55);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill( Color.WHITE );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Arial", FontWeight.BOLD, 50 );
        gc.setFont( theFont );

        gc.fillRect(2,2,45,51);
        gc.strokeRect(0,0,55,55);

        gc.strokeText(String.valueOf(i), 10, 45 );

        return canvas;
    }

    private VBox diceView(int[] dices){
        VBox v = new VBox();
        v.setSpacing(0);
        v.setPadding(new Insets(230,0, 0,0));
        v.getChildren().addAll(dicePrint(dices[0]),dicePrint(dices[1]));
        return v;
    }

    public void move(List<Integer> moveList, Column from, Column to, VBox fromB, VBox toB){
        boolean white;
        if (playerNumber() == 0) white = true;
        else white = false;
        from.move(to);


    };



    public int getTurnCount(){
        return turnCount;
    }

    public int playerNumber(){
        return ((turnCount-1) %2);
    }

}
