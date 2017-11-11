package edu.ramapo.sminev.longana.Model;

/**
 * Created by sminev on 11/5/17.
 */

public class Round {

    private Player[] players;
    private Deck deck;
    private Board board;

    public Round(){
        deck = new Deck();
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
