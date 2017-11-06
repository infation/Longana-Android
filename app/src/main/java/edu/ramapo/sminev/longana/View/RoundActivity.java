package edu.ramapo.sminev.longana.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Deck;
import edu.ramapo.sminev.longana.Model.Hand;
import edu.ramapo.sminev.longana.Model.Round;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.R;

public class RoundActivity extends AppCompatActivity {

    private Vector<Button> handTiles = new Vector<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        Round round = new Round();
        round.drawHands();
        updateHandView(handTiles, round.getPlayer().getHand());

        /*Button bt = new Button(this);
        bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        updateTileView(t.getFirstPip(), bt);
        linearLayout.addView(bt);*/
    }

    public void updateHandView(Vector<Button> handTiles, Hand hand){
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.board);
        for(int i = 0; i < 16; i=i+2){
            Tile t = hand.getTileAt(i);
            handTiles.addElement(new Button(this));
            handTiles.elementAt(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t.getFirstPip(), handTiles.elementAt(i));
            handTiles.addElement(new Button(this));
            handTiles.elementAt(i+1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t.getSecondPip(), handTiles.elementAt(i));
            linearLayout.addView(handTiles.elementAt(i));
            linearLayout.addView(handTiles.elementAt(i+1));
        }
    }

    public void updateTileView(int pip, Button b){

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
        }
    }
}
