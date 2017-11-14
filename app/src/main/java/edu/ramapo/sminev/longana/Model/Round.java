package edu.ramapo.sminev.longana.Model;

/**
 * Created by sminev on 11/5/17.
 */

public class Round {

    private Player[] players;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    private Deck deck;
    private Board board;
    private int turn;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Round(){
        deck = new Deck();
        turn = 0;
        board = new Board();
        players = new Player[2];
        players[0] = new Human();
        players[1] = new Computer();

    }

    public void drawHands(){
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 2; j++){
                players[j].addToHand(deck.draw());
            }
        }
    }

    public void printHands(){
        for(int i = 0 ; i < 2 ; i++){
            players[i].getHand().printHand();
        }
    }

    public void startRound(){
        drawHands();

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
