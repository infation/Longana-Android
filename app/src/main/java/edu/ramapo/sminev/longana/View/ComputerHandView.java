package edu.ramapo.sminev.longana.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Hand;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.R;

/**
 * Created by sminev on 11/14/17.
 */

public class ComputerHandView {

    private RoundActivity activity;
    private Vector<TileView> computerHandView;

    public ComputerHandView(RoundActivity activity) {
        this.activity = activity;
    }

    public Vector<TileView> getView(){ return computerHandView;}

    public void updateComputerHandView(Hand hand, boolean isEnabled){

        computerHandView = new Vector<TileView>();
        ViewGroup linearLayout = (ViewGroup) activity.findViewById(R.id.computer_hand);
        linearLayout.removeAllViews();
        TextView tv = new TextView(activity);
        tv.setText("Computer hand: ");
        tv.setTextSize(20);
        tv.setAllCaps(true);
        linearLayout.addView(tv);
        for(int id = 0; id < hand.size(); id++){
            Tile t = hand.getTileAt(id);
            TileView tview = new TileView(activity);
            tview.updateView(t, id, isEnabled);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(10, 0, 10, 0);

            tview.getView().setLayoutParams(params);
            linearLayout.addView(tview.getView());
            computerHandView.addElement(tview);
        }
    }
}
