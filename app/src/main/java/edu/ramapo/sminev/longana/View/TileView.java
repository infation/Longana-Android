package edu.ramapo.sminev.longana.View;

import android.app.AlertDialog;
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

public class TileView {
    private RoundActivity activity;
    AlertDialog.Builder builder;

    public  LinearLayout getView() {
        return tile;
    }

    private LinearLayout tile;

    public TileView(RoundActivity activity) {
        this.activity = activity;
    }

    public void updatePipView(int pip, Button b){
        switch(pip){
            case 0:
                b.setBackgroundResource(R.drawable.button_border);
                break;
            case 1:
                b.setBackgroundResource(R.drawable.pip_one);
                break;
            case 2:
                b.setBackgroundResource(R.drawable.pip_two);
                break;
            case 3:
                b.setBackgroundResource(R.drawable.pip_three);
                break;
            case 4:
                b.setBackgroundResource(R.drawable.pip_four);
                break;
            case 5:
                b.setBackgroundResource(R.drawable.pip_five);
                break;
            case 6:
                b.setBackgroundResource(R.drawable.pip_six);
                break;
            default:
                b.setBackgroundResource(R.drawable.button_border);
                break;
        }
    }

    public void updateTileView(Tile t, Button pip1, Button pip2, boolean isEnabled){
        updatePipView(t.getFirstPip(), pip1);
        updatePipView(t.getSecondPip(), pip2);
        if(!isEnabled) {
            pip1.setEnabled(isEnabled);
            pip2.setEnabled(isEnabled);
        }
    }



    public void updateView(Tile t, int bid, boolean isEnabled){
        tile = new LinearLayout(activity);

        tile.setId(bid);
        Button pip1= new Button(activity);
        Button pip2 = new Button(activity);
        pip1.setId(bid);
        pip2.setId(bid);

        pip1.setOnClickListener(activity.handButtonsHandler);
        pip2.setOnClickListener(activity.handButtonsHandler);

        updateTileView(t, pip1, pip2, isEnabled);
        tile.addView(pip1);
        tile.addView(pip2);
    }
}
