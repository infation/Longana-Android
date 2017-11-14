package edu.ramapo.sminev.longana.Model;

import edu.ramapo.sminev.longana.View.ComputerHandView;
import edu.ramapo.sminev.longana.View.HumanHandView;
import edu.ramapo.sminev.longana.View.RoundActivity;

/**
 * Created by sminev on 11/5/17.
 */

public class Round {

    private RoundActivity activity;
    private Player[] players;
    private Deck deck;
    private Board board;
    private int turn;
    private int engine;
    private int tournamentMax;


    public Round(RoundActivity activity){
        this.activity = activity;
        deck = new Deck();
        turn = 0;
        engine = 6;
        board = new Board();
        players = new Player[2];
        players[0] = new Human(activity);
        players[1] = new Computer(activity);
    }

    public Round(RoundActivity activity, Deck deck, int turn, int engine, Board board, Player[] players){
        this.activity = activity;
        this.deck = deck;
        this.turn = turn;
        this.engine = engine;
        this.board = board;
        players = new Player[2];
        this.players[0] = players[0];
        this.players[1] = players[1];
    }

    public void setTournamentMax(int tournamentMax) {
        this.tournamentMax = tournamentMax;
    }

    public void decideWinner() {
        if(players[0].getHand().sumTiles() < players[1].getHand().sumTiles()){
            players[0].setRoundScore(players[1].getHand().sumTiles());
            players[0].setTournamentScore(players[0].getTournamentScore() + players[1].getHand().sumTiles());
            System.out.println("Before 1" + players[0].getTournamentScore());
        }
        else if (players[0].getHand().sumTiles() > players[1].getHand().sumTiles()){
            players[1].setRoundScore(players[0].getHand().sumTiles());
            players[1].setTournamentScore(players[1].getTournamentScore() + players[0].getHand().sumTiles());
            System.out.println("Before 2" + players[1].getTournamentScore());
        }
        else{

        }
    }

    public int checkForEngine(HumanHandView humanView, ComputerHandView computerView){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < players[i].getHand().size(); j++){
                if(players[i].getHand().getTileAt(j).getSecondPip() == engine && players[i].getHand().getTileAt(j).getFirstPip() == engine){
                    activity.makeToast("ENGINE IS FOUND.");
                    if(i == 0){
                        humanView.getView().elementAt(j).getView().setEnabled(true);
                        humanView.getView().elementAt(j).getView().setEnabled(true);
                        setTurn(0);
                    }
                    else{
                        computerView.getView().elementAt(j).getView().setEnabled(true);
                        computerView.getView().elementAt(j).getView().setEnabled(true);
                        setTurn(1);
                    }
                    return j;
                }
            }
        }
        activity.makeToast("ENGINE NOT FOUND. DRAWING TILES.");
        return -1;
    }

    public void drawForEngine(){

        for(int i = 0; i < 2; i++){
            players[i].getHand().addTile(deck.draw());
        }
    }

    public boolean hasEnded(){
        if(players[0].getHand().isEmpty()||players[1].getHand().isEmpty()){
            decideWinner();
            System.out.println("After 1" + players[1].getTournamentScore());
            decreaseEngine();
            return true;
        }
        else if(players[0].isPassed()&& players[1].isPassed()&&deck.size() == 0){
            decideWinner();
            System.out.println("After 2" + players[1].getTournamentScore());
            decreaseEngine();
            return true;
        }
        return false;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void drawHands(){
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 2; j++){
                players[j].addToHand(deck.draw());
            }
        }
    }

    public void decreaseEngine(){
        if(engine == 0){
            engine = 6;
        }
        else{
            engine--;
        }
    }

    public void printHands(){
        for(int i = 0 ; i < 2 ; i++){
            players[i].getHand().printHand();
        }
    }

    public int startRound(HumanHandView humanView, ComputerHandView computerView){
        drawHands();
        activity.updateAllViews(false);
        int result = checkForEngine(humanView, computerView);
        if(result ==-1){
            activity.makeToast("The engine is not found. Press DRAW to draw tiles.");
        }
        else{
            activity.makeToast("THE ENGINE IS FOUND");
            activity.unlockButtons();
        }
        return result;
    }


    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }


    public int getTournamentMax() {
        return tournamentMax;
    }


    public void switchTurn(){
        if (turn == 0)
        {
            turn = 1;
        }
        else{
            turn = 0;
        }
    }

    public Tile getFirstTile(){
        return players[0].getHand().playTileAt(0);
    }

    public Player[] getPlayers(){
        return players;
    }

    public Board getBoard(){
        return board;
    }

    public void setBoard(Board board){
        this.board = board;
    }
}
