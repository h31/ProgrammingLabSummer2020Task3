package BG_control;

import BG_model.ChipColor;
import BG_view.Board;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static BG_view.Board.CHIP_SIZE;

public class Turn  {
    private int turnCount = 0;
    private List<Integer> moveList = new ArrayList<Integer>();
    private List<Boolean> notEmptyBlot = Arrays.asList(false,false);

    void setNotEmptyBlot(boolean notEmptyBlot, int i){
        this.notEmptyBlot.set(i, notEmptyBlot);
    }

    public void startTurn(Board board){
        turnCount++;
        newTurnAlert();
        if (notEmptyBlot.get(playerNumber())){
            new Move(this, board).setBlotMove(playerNumber() == 0?ChipColor.WHITE : ChipColor.BLACK);
        }
        List<Integer> moveList = moveList(diceRoll());
        board.getGrid().add(diceView(),13,0);


    }
    private void newTurnAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Turn:"+(turnCount+1)/2);
        alert.setHeaderText("Player "+ (playerNumber()+1) +" turn");
        alert.setContentText(null);
        alert.showAndWait();
    }

    private int[] diceRoll(){
        int d1 = (int) (Math.random()*6) + 1;
        //int d2 = (int) (Math.random()*6) + 1;
        //if (d1 == d1)
        //else return new int[]{d1, d2};
            return new int[]{1,1,1,1};
    }

    private List<Integer> moveList (int[] dices){
        moveList = new ArrayList<Integer>();
        if (dices.length == 2){
            moveList.add(dices[0]);
            moveList.add(dices[1]);
            moveList.add(dices[0]+dices[1]);

        } else {
            moveList.add(dices[0]);
            moveList.add(dices[0]);
            moveList.add(dices[0]*2);
            moveList.add(dices[0]);
            moveList.add(dices[0]);
            moveList.add(dices[0]*2);
            moveList.add(dices[0]*3);
            moveList.add(dices[0]*4);
        }  return moveList;
    }

    private Canvas dicePrint(int i){
        Canvas canvas = new Canvas(CHIP_SIZE,CHIP_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill( Color.WHITE );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Arial", FontWeight.BOLD, CHIP_SIZE*0.9 );
        gc.setFont( theFont );

        gc.fillRect(2,2,CHIP_SIZE*0.9,CHIP_SIZE*0.9);
        gc.strokeRect(0,0,CHIP_SIZE,CHIP_SIZE);

        gc.strokeText(String.valueOf(i), 10, 45 );

        return canvas;
    }

    private VBox diceView(){
        VBox v = new VBox();
        v.setSpacing(0);
        v.setPadding(new Insets(CHIP_SIZE*4,0, 0,0));
        v.getChildren().addAll(dicePrint(moveList.get(0)),dicePrint(moveList.get(1)));
        return v;
    }

    public void setMoveList(List<Integer> moveList){
        this.moveList = moveList;
    }

    public List<Integer> getMoveList(){
        return moveList;
    }

    public int getTurnCount(){
        return turnCount;
    }

    public int playerNumber(){
        return ((turnCount-1) %2);
    }

}
