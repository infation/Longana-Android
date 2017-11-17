package edu.ramapo.sminev.longana.View;

import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Board;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.R;

/**
 * Created by sminev on 11/14/17.
 */

public class BoardView {

    private RoundActivity activity;
    private Vector<TileView> tileViews;

    public BoardView(RoundActivity activity) {
        this.activity = activity;
    }

    public Vector<TileView> getView(){ return tileViews;}

    public void updateBoardView(Board board, boolean isEnabled) {

        tileViews = new Vector<TileView>();
        ViewGroup linearLayout = (ViewGroup) activity.findViewById(R.id.board);
        linearLayout.removeAllViews();

        TextView tv = new TextView(activity);
        tv.setText("Board:  L  ");
        tv.setTextSize(20);
        tv.setAllCaps(true);
        linearLayout.addView(tv);

        for(int i = 0; i < board.size(); i++){

            Tile t = board.getTileAt(i);
            TileView tview = new TileView(activity);
            tview.updateView(t, i, isEnabled);

            if(board.getTileAt(i).isDouble()){
                tview.getView().setOrientation(LinearLayout.VERTICAL);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            tview.getView().setLayoutParams(params);
            linearLayout.addView(tview.getView());
            tileViews.addElement(tview);
        }

        tv = new TextView(activity);
        tv.setText("  R");
        tv.setTextSize(20);
        tv.setAllCaps(true);
        linearLayout.addView(tv);
    }
}
