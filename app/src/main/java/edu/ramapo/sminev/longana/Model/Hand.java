package edu.ramapo.sminev.longana.Model;

import java.util.Vector;

/**
 * Created by sminev on 11/5/17.
 */

public class Hand {

    private Vector<Tile> hand;
    public Hand(){
        hand = new Vector<Tile>();
    }

    public void addTile(Tile t){
        hand.addElement(t);
    }

    public Tile playTileAt(int index){
        Tile t = hand.get(index);
        hand.remove(index);
        return t;
    }

    public boolean isEmpty() {
        if (hand.isEmpty()) {
            return true;
        } else {
            return false;

        }
    }

    public int sumTiles(){
        int sum = 0;
        for(int i = 0; i < hand.size(); i++){
            sum = sum + hand.elementAt(i).sum();
        }
        return sum;
    }

    public void printHand() {
        System.out.print("Your hand so far:    ");
        for (int i = 0; i < hand.size(); i++) {
            hand.elementAt(i).printTile();
        }
        System.out.println();
    }

    public Tile getTileAt(int index){
        return hand.get(index);
    }

    public int size(){
        return hand.size();
    }

    public Vector<Tile> getHand(){
        return hand;
    }

    public void setHand(Vector<Tile> hand){
        this.hand = hand;
    }
}
