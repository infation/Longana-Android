package edu.ramapo.sminev.longana.Model;

import edu.ramapo.sminev.longana.View.RoundActivity;

/**
 * Created by sminev on 11/5/17.
 */

public class Tournament {

    private Round round;
    private int humanTourScore;
    private int computerTourScore;
    private int maxTourScore;
    private Parser parser;

    public Tournament(RoundActivity activity){
        round = new Round(activity);
        humanTourScore = 0;
        computerTourScore = 0;
        maxTourScore = 0;
        parser = new Parser();
    }


    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public int getHumanTourScore() {
        return humanTourScore;
    }

    public void setHumanTourScore(int humanTourScore) {
        this.humanTourScore = humanTourScore;
    }

    public int getComputerTourScore() {
        return computerTourScore;
    }

    public void setComputerTourScore(int computerTourScore) {
        this.computerTourScore = computerTourScore;
    }

    public int getMaxTourScore() {
        return maxTourScore;
    }

    public void setMaxTourScore(int maxTourScore) {
        this.maxTourScore = maxTourScore;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

}
