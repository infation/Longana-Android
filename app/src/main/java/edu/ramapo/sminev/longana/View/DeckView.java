package edu.ramapo.sminev.longana.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Board;
import edu.ramapo.sminev.longana.Model.Deck;
import edu.ramapo.sminev.longana.Model.Round;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.R;

/**
 * Created by sminev on 11/15/17.
 */

public class DeckView {

    private RoundActivity activity;
    private Vector<String> tileViews;

    public DeckView(RoundActivity activity) {
        this.activity = activity;
    }

    public Vector<String> getView(){ return tileViews;}

    public void updateView(Round round, int tournamentMax) {

        tileViews = new Vector<String>();
        AdapterView linearLayout = (AdapterView) activity.findViewById(R.id.left_drawer);

        updateDrawer(round, tournamentMax);

        String s = "Remaining deck: ";
        tileViews.add(s);

        for(int i = 0; i < round.getDeck().size(); i++){

            Tile t = round.getDeck().getTileAt(i);
            //Drawer for tiles


            tileViews.add(t.toString());
        }


        linearLayout.setAdapter(new ArrayAdapter<String>(activity,
                R.layout.drawer_listview_item, tileViews));
    }


    public void updateDrawer(Round round, int tournamentMax){

        //Drawer for tiles
        tileViews = new Vector<>();
        String s = "Remaining tiles in the deck: " + round.getDeck().size();
        tileViews.add(s);
        // Set the adapter for the list view

        s = "Turn: ";
        if(round.getTurn() == 0){
            s = s+"Human";
        }
        else{
            s = s+"Computer";
        }
        tileViews.add(s);
        s = "Human score: " + round.getPlayers()[0].getTournamentScore();
        tileViews.add(s);
        s = "Computer score: " + round.getPlayers()[1].getTournamentScore();
        tileViews.add(s);
        s = "Tournament Max score: " + tournamentMax;
        tileViews.add(s);
        s = "Engine: " + round.getEngine();
        tileViews.add(s);
    }
}
