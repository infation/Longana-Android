package edu.ramapo.sminev.longana.Model;

/**
 * Created by sminev on 11/5/17.
 */

import java.util.Random;
import java.util.Vector;


public class Deck {

    private Vector<Tile> deck;

    public Deck(boolean shouldInitialize){
        deck = new Vector<Tile>();
        if(shouldInitialize)
            initializeDeck();
    }

    public Tile getTileAt(int index){
        return deck.get(index);
    }

    /* *********************************************************************
    Function Name: initializeDeck
    Purpose: To prepare a deck object before the start of a round
    Parameters:none
    Return Value: none
    Assistance Received: none;
    ********************************************************************* */
    public void initializeDeck(){

        //Generate the 28 tiles and push it in the deck
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                Tile t = new Tile(i, j);
                deck.addElement(t);
            }
        }
        //Shuffle the deck
        shuffle();
    }

    public void shuffle(){
        Random rand = new Random();
        //Generate random number from 0 to (size of deck - 1)
        int randIndex1 = rand.nextInt(deck.size());
        int randIndex2 = rand.nextInt(deck.size());
        for (int i = 0; i < 5000; i++) {
            //Swap elements from randIndex1 and randIndex2;
            Tile t1 = deck.elementAt(randIndex1);
            Tile t2 = deck.elementAt(randIndex2);
            deck.setElementAt(t2, randIndex1);
            deck.setElementAt(t1, randIndex2);
            //Generate different random indexes
            randIndex1 = rand.nextInt(deck.size());
            randIndex2 = rand.nextInt(deck.size());
        }
    }

    public void addTile(Tile t){
        deck.addElement(t);
    }

    public int size(){
        return deck.size();
    }

    public void printDeck(){
        System.out.print("\n\n\nDeck: ");
        for(int i = 0; i < deck.size(); i++)
            deck.elementAt(i).printTile();
        System.out.print("\n\n\n");
    }

    public Tile draw(){
        Tile t = deck.elementAt(0);
        deck.removeElementAt(0);
        return t;
    }
}
