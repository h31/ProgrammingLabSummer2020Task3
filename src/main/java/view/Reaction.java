package view;

import core.Cell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Reaction {
    BattleShipView view;

    public Reaction(BattleShipView view) {
        this.view = view;
    }

    public void concludingPhrase(boolean isEnemyBoard) {
        Text text;
        if (isEnemyBoard) {
            text = new Text("\nВы проиграли =(((");
        } else text = new Text("\nПоздравляем! Вы победили");
        text.setFont(Font.font(50));
        text.setFill(Color.RED);
        view.message.getChildren().add(text);
        view.showDialog();
    }

    public void justPhrase(boolean isEnemyBoard) {
        Text text;
        if (isEnemyBoard) {
            text = new Text("Разместите свои корабли");
        } else text = new Text("\nВаш ход первый!Да начнется игра!");
        text.setFont(Font.font("Verdana", 20));
        text.setFill(Color.DARKGREEN);
        view.message.getChildren().add(text);
    }

    public void setColorMissed(Cell cell) {
        cell.setFill(Color.VIOLET);
    }

    public void setColorInjured(Cell cell) {
        cell.setFill(Color.RED);
    }

    public void setShipColor(Cell cell) {
        cell.setFill(Color.DARKBLUE);
    }
}
