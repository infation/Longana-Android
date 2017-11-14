package edu.ramapo.sminev.longana.Model;


import android.widget.Toast;

import edu.ramapo.sminev.longana.View.RoundActivity;

/**
 * Created by sminev on 11/5/17.
 */

public class Human extends Player {

    @Override
    public Boolean play(Board board, Deck deck, boolean compPassed, RoundActivity activity, int index) {
            String s;
            //Check if there are any available moves with the current hand
            if (!board.checkAvailableMoves(true, getHand(), compPassed)) {
                //Check if the deck is empty return
                if(deck.size()==0){
                    s = "No more available moves and there are no more tiles in the deck.";
                    setPassed(true);
                    activity.makeToast(s);
                    return null;
                }
                //If the deck wasn't empty draw a tile and check if the move with the new tile is valid
                else{
                    //Draw the new tile and add it to the hand
                    s = "No more available moves ! Drawing from deck...";
                    activity.makeToast(s);
                    getHand().addTile(deck.draw());
                    //Check if there are any available moves with the new hand
                    if(!board.checkAvailableMoves(true, getHand(),compPassed)){
                        s = "Unfortunately it couldn't be placed anywhere.";
                        activity.makeToast(s);
                        setPassed(true);
                        return null;
                    }
                    else{
                        s = "You got lucky !";
                        //play(board, deck, compPassed, activity, getHand().size()-1);
                        activity.makeToast(s);
                        return false;
                    }
                }
            }

            if(getHand().getTileAt(index).isDouble()||compPassed) {
                //Hand and set the flags

                if (board.checkRulesForPlacement(true, getHand().getTileAt(index))) {
                    board.addTileToLeft(getHand().playTileAt(index));
                    setPassed(false);
                    return true;
                }
                else if (board.checkRulesForPlacement(false, getHand().getTileAt(index))) {
                    board.addTileToRight(getHand().playTileAt(index));
                    setPassed(false);
                    return true;
                    //User made invalid move
                } else {
                    s = "Invalid move !!!!!!!!";
                    activity.makeToast(s);
                }
            }

            else{
                    //Check the rules add it to the board, update the view, remove the tile from the
                    //Hand and set the flags
                if (board.checkRulesForPlacement(true, getHand().getTileAt(index))) {
                    board.addTileToLeft(getHand().playTileAt(index));
                    setPassed(false);
                    return true;
                }
                    s = "Invalid move !!!!!!!!";
                    activity.makeToast(s);
            }

        return false;
    }
}
