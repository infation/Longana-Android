package edu.ramapo.sminev.longana.Model;

import edu.ramapo.sminev.longana.View.HumanHandView;
import edu.ramapo.sminev.longana.View.RoundActivity;

/**
 * Created by sminev on 11/5/17.
 */

public class Player {
    public RoundActivity activity;
    private Hand hand;
    private int roundScore;
    private int tournamentScore;
    private boolean isPassed;


    public Player(RoundActivity activity){
        this.activity = activity;
        hand = new Hand();
        roundScore = 0;
        tournamentScore = 0;
        isPassed = false;
    }


    public Player(){
        this.activity = null;
        hand = new Hand();
        roundScore = 0;
        tournamentScore = 0;
        isPassed = false;
    }

    public boolean playDoubleTile(Round round, int index, boolean isLeftPlacement){return false;}
    public boolean playTile(Round round, int index, HumanHandView view){return false;}


    public Boolean play(Board board, Deck deck, boolean compPassed , int index, Boolean isLeftPlacement){
        return false;
    }

    public Hand getHand() {
        return hand;
    }

    public void addToHand(Tile t){
        hand.addTile(t);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public boolean hint(Round round){return true;}

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public int getTournamentScore() {
        return tournamentScore;
    }

    public void setTournamentScore(int tournamentScore) {
        this.tournamentScore = tournamentScore;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}
