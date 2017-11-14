package edu.ramapo.sminev.longana.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Hand;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.R;

/**
 * Created by sminev on 11/10/17.
 */

public class HumanHandView {

    private RoundActivity activity;
    AlertDialog.Builder builder;

    public Vector<TileView> getView() {
        return humanHandButtons;
    }

    Vector<TileView> humanHandButtons;

    public HumanHandView(RoundActivity activity) {
        this.activity = activity;
    }

    public void askToChooseSide(){
        builder = new AlertDialog.Builder(activity);
        builder.setTitle("Choose side for placement");
        builder.setPositiveButton("RIGHT", activity.chooseLeftOrRightListener);
        builder.setNegativeButton("LEFT", activity.chooseLeftOrRightListener);
        builder.create().show();
    }

    public void updateView(Hand hand, boolean isEnabled){
        humanHandButtons = new Vector<TileView>();
        ViewGroup linearLayout = (ViewGroup) activity.findViewById(R.id.human_hand);
        linearLayout.removeAllViews();
        TextView tv = new TextView(activity);
        tv.setText("Human hand: ");
        linearLayout.addView(tv);
        for(int i = 0; i < hand.size(); i++){
            Tile t = hand.getTileAt(i);
            humanHandButtons.addElement(new TileView(activity));
            humanHandButtons.elementAt(i).updateView(t, i, isEnabled);

            humanHandButtons.elementAt(i).getView().elementAt(0).setOnClickListener(activity.handButtonsHandler);
            humanHandButtons.elementAt(i).getView().elementAt(1).setOnClickListener(activity.handButtonsHandler);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 0, 0);
            humanHandButtons.elementAt(i).getView().elementAt(0).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 10, 0);
            humanHandButtons.elementAt(i).getView().elementAt(1).setLayoutParams(params);
            linearLayout.addView(humanHandButtons.elementAt(i).getView().elementAt(0));
            linearLayout.addView(humanHandButtons.elementAt(i).getView().elementAt(1));
        }
    }
}
