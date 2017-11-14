package edu.ramapo.sminev.longana.Model;

import edu.ramapo.sminev.longana.View.HumanHandView;
import edu.ramapo.sminev.longana.View.RoundActivity;

/**
 * Created by sminev on 11/5/17.
 */

public class Computer extends Player {
    public Computer(RoundActivity activity){
        this.activity = activity;
    }

    public boolean checkIfCanPlay(Round round){
        boolean humanPassed = round.getPlayers()[0].isPassed();
        //Check if there are any available moves with the current hand
        if (!round.getBoard().checkAvailableMoves(false, getHand(), humanPassed)) {
            //Check if the deck is empty return
            if (round.getDeck().size() == 0) {
                setPassed(true);
                String s = "No more available moves and computer couldn't draw a tile.";
                activity.makeToast(s);
                round.switchTurn();
                return false;
            }
            //If the deck wasn't empty draw a tile and check if the move with the new tile is valid
            else {
                String s = "No more available moves computer drawing a tile.";
                activity.makeToast(s);
                return drawTileAndTry(round);
            }
        }
        round.getBoard().checkAvailableMoves(false, getHand(), humanPassed);
        return true;
    }

    public boolean drawTileAndTry(Round round){
        getHand().addTile(round.getDeck().draw());
        //Check if there are any available moves with the new hand
        if(!round.getBoard().checkAvailableMoves(false, getHand(), round.getPlayers()[0].isPassed())){
            String s = "It couldn't place it anywhere";
            activity.makeToast(s);
            setPassed(true);
            round.switchTurn();
            return false;
        }
        else{
            String s = "Computer got lucky and placed the tile.";
            activity.makeToast(s);
            round.getBoard().checkAvailableMoves(false, getHand(), round.getPlayers()[0].isPassed());
            return true;
        }
    }

    public boolean playTile(Round round, int is, HumanHandView view){

        if(!checkIfCanPlay(round)) return false;

        int index = 0;
        int maxSum = 0;
        boolean position = false;

        for(int i = 0; i < getHand().size(); i++){
            if(getHand().getTileAt(i).isDouble()|| round.getPlayers()[0].isPassed()) {
                if (round.getBoard().checkRulesForPlacement(false, getHand().getTileAt(i))) {
                    if (getHand().getTileAt(i).sum() > maxSum) {
                        index = i;
                        maxSum = getHand().getTileAt(i).sum();
                    }
                }

                if (round.getBoard().checkRulesForPlacement(true, getHand().getTileAt(i))) {
                    if (getHand().getTileAt(i).sum() > maxSum) {
                        index = i;
                        maxSum = getHand().getTileAt(i).sum();
                        position = true;
                    }
                }
            }
            else{
                if (round.getBoard().checkRulesForPlacement(false, getHand().getTileAt(i))) {
                    if (getHand().getTileAt(i).sum() > maxSum) {
                        index = i;
                        maxSum = getHand().getTileAt(i).sum();
                    }
                }
            }
        }

        if(position){
            round.getBoard().addTileToLeft(getHand().playTileAt(index));
            String s = "The computer played " + round.getBoard().getTileAt(0).toString() + "on the left";
            activity.makeToast(s);
        }
        else{
            round.getBoard().addTileToRight(getHand().playTileAt(index));
            String s = "The computer played " + round.getBoard().getTileAt(round.getBoard().size()-1).toString() + "on the right";
            activity.makeToast(s);
        }

        setPassed(false);
        round.switchTurn();
        return true;
    }
}
