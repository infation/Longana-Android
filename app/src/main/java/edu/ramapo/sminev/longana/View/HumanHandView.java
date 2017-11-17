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
        return humanHandView;
    }

    Vector<TileView> humanHandView;

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

        humanHandView = new Vector<TileView>();
        ViewGroup linearLayout = (ViewGroup) activity.findViewById(R.id.human_hand);
        linearLayout.removeAllViews();
        TextView tv = new TextView(activity);
        tv.setText("Human hand: ");
        tv.setTextSize(20);
        tv.setAllCaps(true);
        linearLayout.addView(tv);

        for(int i = 0; i < hand.size(); i++){
            Tile t = hand.getTileAt(i);
            TileView tview = new TileView(activity);
            tview.updateView(t, i, isEnabled);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 10, 0);

            tview.getView().setLayoutParams(params);
            linearLayout.addView(tview.getView());
            humanHandView.addElement(tview);
        }
    }


}
