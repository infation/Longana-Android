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

    public boolean checkIfEnginePlaced(int engine){
        for(int i = 0; i< board.size(); i ++){
            if(engine == board.elementAt(i).getFirstPip() && engine ==board.elementAt(i).getSecondPip()){
                return true;
            }
        }
        return false;
    }

    public Tile getTileAt(int index){
        return board.get(index);
    }

    public int size(){
        return board.size();
    }


    public boolean checkAvailableMoves(boolean isHuman, Hand h, boolean hasPassed) {
        //Go through the deck
        for (int i = 0; i < h.size(); i++) {
            Tile t = h.getTileAt(i);
            //Check if the current tile is a double tile
            if(!t.isDouble()&&!hasPassed){
                //If the tile is not double and the previous player hasn't passed
                if (checkRulesForPlacement(isHuman, t))
                    return true;
            }
            //If the tile is either double or the previous player has passed
            else{
                //Check the rule
                if(checkRulesForPlacement(true, t) || checkRulesForPlacement(false, t)){
                    return true;
                }
            }
        }
        return false;
    }

    /* *********************************************************************
Function Name: checkLeft
Purpose: to check if the placement of the tile on the left is correct according to the rules of the game
Parameters: t-> the tile to check the rules for
Return Value: if the tile can be place on the left
Assistaince Received: none;
********************************************************************* */
   public boolean checkLeft(Tile t){
        if (board.get(0).getFirstPip()== t.getSecondPip()){
            return true;
        }
        //Check the reversed pips and the switch the pips for that specific tile
        else if (board.get(0).getFirstPip()== t.getFirstPip()) {
            t.switchPips();
            return true;
        }
        else {
            return false;
        }
    }

    /* *********************************************************************
    Function Name: checkRight
    Purpose: to check if the placement of a tile on the right is correct according to the rules of the game
    Parameters: t-> the tile to check the rules for
    Return Value: if the tile can be placed on the right, bool
    Assistaince Received: none;
    ********************************************************************* */
    public boolean checkRight(Tile t){
        int size = board.size() - 1 ;
        if (board.get(size).getSecondPip() == t.getFirstPip()) {
            return true;
        }
        //Check the reversed pips and the switch the pips for that specific tile
        else if (board.get(size).getSecondPip()== t.getSecondPip()) {
            t.switchPips();
            return true;
        }
        else {
            return false;
        }
    }

    /* *********************************************************************
    Function Name: checkRulesForPlacement
    Purpose: to check if the placement is correct according to the rules of the game
    Parameters: isHuman -> true, if the player is human, false otherwise
                t-> the tile to check the rules for
    Return Value: if the tile can be place, bool
    Assistaince Received: none;
    ********************************************************************* */
    public boolean checkRulesForPlacement(boolean isLeft, Tile t) {
        //If the players are adding a tile on the left side of the board
        if(board.size()>0) {
            if (isLeft) {
                return checkLeft(t);
            }
            //If the players are adding a tile on the right side of the board
            else {
                return checkRight(t);
            }
        }
        return true;
    }

    public Vector<Tile> getBoard(){
        return board;
    }

    public void setBoard(Vector<Tile> board){
        this.board = board;
    }
}
