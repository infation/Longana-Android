package edu.ramapo.sminev.longana.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Hand;
import edu.ramapo.sminev.longana.Model.Round;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.R;

public class RoundActivity extends AppCompatActivity {

    private Vector<Button> humanHandButtons = new Vector<Button>();
    private Vector<Button> computerHandButtons = new Vector<Button>();
    private Vector<Button> boardButtons = new Vector<Button>();
    private Round round = new Round();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        round.startRound();
        updateHumanHandView(round.getPlayers()[0].getHand());
        updateComputerHandView(round.getPlayers()[1].getHand());
        //updateHandView(handTiles, round.getPlayers()[1].getHand(), R.id.computer_hand);


        /*Button bt = new Button(this);
        bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        updateTileView(t.getFirstPip(), bt);
        linearLayout.addView(bt);*/
    }

    public void updateHumanHandView(Hand hand){
        humanHandButtons = new Vector<Button>();
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.human_hand);
        linearLayout.removeAllViews();
        TextView tv = new TextView(this);
        tv.setText("Human hand: ");
        linearLayout.addView(tv);
        for(int i = 0; i < hand.size(); i++){
            Tile t = hand.getTileAt(i);
            humanHandButtons.addElement(new Button(this));
            humanHandButtons.get(i).setId(i);
            humanHandButtons.elementAt(i).setOnClickListener(handButtonsHandler);
            humanHandButtons.elementAt(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t, humanHandButtons.elementAt(i));
            System.out.println("Button Added To view.");
            linearLayout.addView(humanHandButtons.elementAt(i));
        }
    }

    public void updateComputerHandView(Hand hand){
        computerHandButtons = new Vector<Button>();

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.computer_hand);
        linearLayout.removeView(linearLayout);
        for(int i = 0; i < hand.size(); i++){
            Tile t = hand.getTileAt(i);
            computerHandButtons.addElement(new Button(this));
            computerHandButtons.elementAt(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t, computerHandButtons.elementAt(i));
            linearLayout.addView(computerHandButtons.elementAt(i));
        }
    }

    public void updateBoardView(Tile t, boolean isLeft) {

        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.board);
        if (isLeft) {
            boardButtons.add(0,new Button(this));
            boardButtons.elementAt(0).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t, boardButtons.elementAt(0));
            linearLayout.addView(boardButtons.elementAt(0));
        }
        else{
            boardButtons.add(boardButtons.size()-1,new Button(this));
            boardButtons.elementAt(boardButtons.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t, boardButtons.elementAt(boardButtons.size()-1));
            linearLayout.addView(boardButtons.elementAt(boardButtons.size()-1));
        }
    }
    public void updateTileView(Tile tile, Button b){
        b.setBackgroundResource(R.drawable.button_border);
        b.setTextSize(10);
        String s = tile.getFirstPip() + "-" + tile.getSecondPip();
        b.setText(s);
    }

    View.OnClickListener handButtonsHandler = (new View.OnClickListener() {
        public void onClick(View view) {
            System.out.println("Pressed " + view.getId());
            for(int i = 0; i < round.getPlayers()[0].getHand().size(); i++){
                if(humanHandButtons.elementAt(i).getId() == view.getId()){
                    round.getBoard().addTileToLeft(round.getPlayers()[0].getHand().playTileAt(i));
                    updateBoardView(round.getBoard().getTileAt(0), true);
                    round.getPlayers()[0].getHand().printHand();
                    updateHumanHandView(round.getPlayers()[0].getHand());
                }
            }
        }
    });

}
