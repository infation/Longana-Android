package edu.ramapo.sminev.longana.Model;


import android.widget.Toast;

import edu.ramapo.sminev.longana.View.HumanHandView;
import edu.ramapo.sminev.longana.View.RoundActivity;

/**
 * Created by sminev on 11/5/17.
 */

public class Human extends Player {

    public Human(RoundActivity activity){
        this.activity = activity;
    }

    @Override
    public Boolean play(Board board, Deck deck, boolean compPassed, int index, Boolean isLeftPlacement) {


        //Hand and set the flags
        return false;
    }

    public boolean playTile(Round round, int index, HumanHandView view){
        if(checkIfCanPlay(round)) {
            if(getHand().getTileAt(index).isDouble() || round.getPlayers()[1].isPassed()){
                //Third argument is hardcoded
                view.askToChooseSide();
                return false;
            }
            else{
                if(playNormalTile(round, index)) return true;
            }
        }
        return false;
    }

    public boolean checkIfCanPlay(Round round){
        String s;
        boolean compPassed = round.getPlayers()[1].isPassed();
        //Check if there are any available moves with the current hand
        if (!round.getBoard().checkAvailableMoves(true, getHand(), compPassed)) {
            //Check if the deck is empty return
            if(round.getDeck().size()==0){
                s = "No more available moves and there are no more tiles in the deck.";
                setPassed(true);
                activity.makeToast(s);
                round.switchTurn();
                return false;
            }
            //If the deck wasn't empty draw a tile and check if the move with the new tile is valid
            else{
                if(drawTileAndTry(round)){
                    return true;
                }
            }
        }
        round.getBoard().checkAvailableMoves(true, getHand(), round.getPlayers()[1].isPassed());
        return true;
    }

    public boolean drawTileAndTry(Round round){
        //Draw the new tile and add it to the hand
        String s = "No more available moves ! Drawing from deck...";
        activity.makeToast(s);
        getHand().addTile(round.getDeck().draw());
        //Check if there are any available moves with the new hand
        if(!round.getBoard().checkAvailableMoves(true, getHand(), round.getPlayers()[1].isPassed())){
            s = "Unfortunately it couldn't be placed anywhere.";
            activity.makeToast(s);
            setPassed(true);
            round.switchTurn();
            return false;
        }
        else{
            round.getBoard().checkAvailableMoves(true, getHand(), round.getPlayers()[1].isPassed());
            s = "You got lucky !";
            //play(board, deck, compPassed, activity, getHand().size()-1);
            activity.makeToast(s);
            return true;
        }
    }

    public boolean playNormalTile(Round round, int index){
        //Check the rules add it to the board, update the view, remove the tile from the
        //Hand and set the flags
        if (round.getBoard().checkRulesForPlacement(true, getHand().getTileAt(index))) {
            round.getBoard().addTileToLeft(getHand().playTileAt(index));
            setPassed(false);
            round.switchTurn();
            return true;
        }
        String s = "Invalid move !!!!!!!!";
        activity.makeToast(s);
        return false;
    }

    public boolean playDoubleTile(Round round, int index, boolean isLeftPlacement){

        if (round.getBoard().checkRulesForPlacement(true, getHand().getTileAt(index))&&isLeftPlacement) {
            round.getBoard().addTileToLeft(getHand().playTileAt(index));
            setPassed(false);
            round.switchTurn();
            return true;
        } else if (round.getBoard().checkRulesForPlacement(false, getHand().getTileAt(index))&&!isLeftPlacement) {
            round.getBoard().addTileToRight(getHand().playTileAt(index));
            setPassed(false);
            round.switchTurn();
            return true;
            //User made invalid move
        }
        String s = "Invalid move !!!!!!!!";
        activity.makeToast(s);
        return false;
    }

    public boolean hint(Round round){

        if(!checkIfCanPlay(round)) {
            String s = "Computer couldn't help.";
            activity.makeToast(s);
            return false;
        }

        int index = 0;
        int maxSum = 0;
        boolean position = true;

        for(int i = 0; i < getHand().size(); i++){
            if(getHand().getTileAt(i).isDouble()|| round.getPlayers()[1].isPassed()) {
                if (round.getBoard().checkRulesForPlacement(true, getHand().getTileAt(i))) {
                    if (getHand().getTileAt(i).sum() > maxSum) {
                        index = i;
                        maxSum = getHand().getTileAt(i).sum();
                    }
                }

                if (round.getBoard().checkRulesForPlacement(false, getHand().getTileAt(i))) {
                    if (getHand().getTileAt(i).sum() > maxSum) {
                        index = i;
                        maxSum = getHand().getTileAt(i).sum();
                        position = false;
                    }
                }
            }
            else{
                if (round.getBoard().checkRulesForPlacement(true, getHand().getTileAt(i))) {
                    if (getHand().getTileAt(i).sum() > maxSum) {
                        index = i;
                        maxSum = getHand().getTileAt(i).sum();
                    }
                }
            }
        }

        if(position){
            String s = "The computer suggests to play" + getHand().getTileAt(index).toString() + "on the left";
            activity.makeToast(s);
        }
        else{
            String s = "The computer suggest to play " +getHand().getTileAt(index).toString() + "on the right";
            activity.makeToast(s);
        }

        return true;
    }
}
