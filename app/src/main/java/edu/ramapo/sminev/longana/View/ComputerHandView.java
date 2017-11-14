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
    private Vector<TileView> computerHandButtons;

    public ComputerHandView(RoundActivity activity) {
        this.activity = activity;
    }

    public Vector<TileView> getView(){ return computerHandButtons;}

    public void updateComputerHandView(Hand hand, boolean isEnabled){

        computerHandButtons = new Vector<TileView>();
        ViewGroup linearLayout = (ViewGroup) activity.findViewById(R.id.computer_hand);
        linearLayout.removeAllViews();
        TextView tv = new TextView(activity);
        tv.setText("Computer hand: ");
        linearLayout.addView(tv);
        for(int i = 0; i < hand.size(); i++){
            Tile t = hand.getTileAt(i);
            computerHandButtons.addElement(new TileView(activity));
            computerHandButtons.elementAt(i).updateView(t, i, isEnabled);

            computerHandButtons.elementAt(i).getView().elementAt(0).setOnClickListener(activity.handButtonsHandler);
            computerHandButtons.elementAt(i).getView().elementAt(1).setOnClickListener(activity.handButtonsHandler);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 0, 0);
            computerHandButtons.elementAt(i).getView().elementAt(0).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 10, 0);
            computerHandButtons.elementAt(i).getView().elementAt(1).setLayoutParams(params);
            linearLayout.addView(computerHandButtons.elementAt(i).getView().elementAt(0));
            linearLayout.addView(computerHandButtons.elementAt(i).getView().elementAt(1));
        }
    }
}
