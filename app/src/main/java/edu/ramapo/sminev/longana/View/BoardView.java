package edu.ramapo.sminev.longana.View;

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
    private Vector<TileView> boardButtons;

    public BoardView(RoundActivity activity) {
        this.activity = activity;
    }

    public Vector<TileView> getView(){ return boardButtons;}

    public void updateBoardView(Board board, boolean isEnabled) {
        boardButtons = new Vector<TileView>();
        ViewGroup linearLayout = (ViewGroup) activity.findViewById(R.id.board);
        linearLayout.removeAllViews();
        TextView tv = new TextView(activity);
        tv.setText("Board:  L  ");
        linearLayout.addView(tv);

        for(int i = 0; i < board.size(); i++){
            Tile t = board.getTileAt(i);
            boardButtons.addElement(new TileView(activity));
            boardButtons.elementAt(i).updateView(t, i, isEnabled);

            boardButtons.elementAt(i).getView().elementAt(0).setOnClickListener(activity.handButtonsHandler);
            boardButtons.elementAt(i).getView().elementAt(1).setOnClickListener(activity.handButtonsHandler);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 0, 0);
            boardButtons.elementAt(i).getView().elementAt(0).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 10, 0);
            boardButtons.elementAt(i).getView().elementAt(1).setLayoutParams(params);
            linearLayout.addView( boardButtons.elementAt(i).getView().elementAt(0));
            linearLayout.addView( boardButtons.elementAt(i).getView().elementAt(1));
        }

        tv = new TextView(activity);
        tv.setText("  R");
        linearLayout.addView(tv);
    }
}
