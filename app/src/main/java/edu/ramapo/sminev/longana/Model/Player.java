package edu.ramapo.sminev.longana.Model;

/**
 * Created by sminev on 11/5/17.
 */

public class Player {
    private Hand hand;
    private int roundScore;
    private int tournamentScore;
    private boolean isPassed;


    public Player(){
        hand = new Hand();
        roundScore = 0;
        tournamentScore = 0;
        isPassed = false;
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
