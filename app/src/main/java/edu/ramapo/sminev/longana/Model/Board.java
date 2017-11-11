package edu.ramapo.sminev.longana.Model;

import java.util.Vector;

/**
 * Created by sminev on 11/5/17.
 */

public class Board {
    private Vector<Tile> board;

    public Board(){
        board = new Vector<Tile>();
    }

    public void addTileToRight(Tile t){
        board.addElement(t);
    }

    public void addTileToLeft(Tile t){
        board.add(0, t);
    }

    public void printboard() {
        System.out.print("Your board so far:    ");
        for (int i = 0; i < board.size(); i++) {
            board.elementAt(i).printTile();
        }
        System.out.println();
    }

    public Tile getTileAt(int index){
        return board.get(index);
    }

    public int size(){
        return board.size();
    }

    public Vector<Tile> getBoard(){
        return board;
    }

    public void setBoard(Vector<Tile> board){
        this.board = board;
    }
}
