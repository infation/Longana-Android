package edu.ramapo.sminev.longana.Model;

/**
 * Created by sminev on 11/5/17.
 */

public class Tile {

    //Members
    private int[] pips;

    //Member methods
    public Tile(){
        pips = new int[2];
        pips[0] = 0;
        pips[1] = 0;
    }

    public Tile(int pip1, int pip2){
        pips = new int[2];
        pips[0] = pip1;
        pips[1] = pip2;
    }

    public int[] getPips(){
        return pips;
    }

    public void setPips(int pip1, int pip2){
        pips[0] = pip1;
        pips[1] = pip2;
    }

    public int getFirstPip(){
        return pips[0];
    }

    public int getSecondPip(){
        return pips[1];
    }

    public int sum(){
        return pips[0] + pips[1];
    }

    public void switchPips(){
        int p = pips[0];
        pips[0] = pips[1];
        pips[1] = p;
    }

    public void printTile() {
        System.out.print(" " + pips[0] + "-" + pips[1] +" ");
    }

    public boolean isDouble() {
        if (pips[0] == pips[1])
            return true;
        else
            return false;
    }
}
