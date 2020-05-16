package BG_model;

import java.util.ArrayList;
import java.util.List;

import static BG_model.ChipColor.*;

public class Board {
    private List<Column> board;
    private Column whiteBlot;
    private Column blackBlot;

    public Board(){
        final Chip white = new Chip(WHITE);
        final Chip black = new Chip(BLACK);
        this.board = new ArrayList<Column>();
        this.whiteBlot = new Column();
        this.blackBlot = new Column();

        board.add(new Column(new Chip[] { black, black } ));
        for (int i = 0; i < 4; i++){
            board.add(new Column());
        }
        board.add(new Column(new Chip[] { white, white, white, white,white}));
        board.add(new Column());
        board.add(new Column(new Chip[] { white, white, white}));
        for (int i = 0; i < 3; i++){
            board.add(new Column());
        }
        board.add(new Column(new Chip[] { black, black, black, black,black}));
        board.add(new Column(new Chip[] { white, white, white, white,white}));
        for (int i = 0; i < 3; i++){
            board.add(new Column());
        }
        board.add(new Column(new Chip[] {black, black, black}));
        board.add(new Column());
        board.add(new Column(new Chip[] { black, black, black, black,black}));
        for (int i = 0; i < 4; i++){
            board.add(new Column());
        }
        board.add(new Column(new Chip[] { white, white } ));
    }

    public boolean move (int from, int to){
        if (board.get(from).onTop().equals(board.get(from).onTop())) {
            return board.get(from).move(board.get(to));
        } else if (board.get(to).size() == 1) {
            if (board.get(to).onTop().equals(WHITE)) board.get(to).move(whiteBlot);
            else board.get(to).move(blackBlot);
            return board.get(from).move(board.get(to));
        } else {
            return false;
        }
    }

    public boolean blotMove (ChipColor color, int to){
        if (color.equals(WHITE)) {
            if (board.get(to).onTop().equals(color)) {
                return whiteBlot.move(board.get(to));
            } else if (board.get(to).size() == 1) {
                board.get(to).move(blackBlot);
                return whiteBlot.move(board.get(to));
            }

        } else {
            if (board.get(23-to).onTop().equals(color)) {
                return whiteBlot.move(board.get(23 - to));
            } else if (board.get(23-to).size() == 1) {
                board.get(23-to).move(whiteBlot);
                return whiteBlot.move(board.get(to));
            }
        }
        return false;
    }

    public Column getWhiteBlot(){
        return whiteBlot;
    }

    public Column getBlackBlot(){
        return blackBlot;
    }

    public List<Column> getBoard(){
        return this.board;
    }


}
