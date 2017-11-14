package edu.ramapo.sminev.longana.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Hand;
import edu.ramapo.sminev.longana.Model.Round;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.R;

public class RoundActivity extends AppCompatActivity {

    private Vector<Button> humanHandButtons = new Vector<Button>();
    private ListView drawerListView;
    private Vector<String> drawerListViewItems;
    private Button playButton;
    private Vector<Button> computerHandButtons = new Vector<Button>();
    private Vector<Button> boardButtons = new Vector<Button>();
    private Round round = new Round();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_round);
        drawerListView = (ListView) findViewById(R.id.left_drawer);
        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(playButtonListener);
        getExtras();
        round.startRound();
        updateHumanHandView();
        updateComputerHandView();
        updateDrawer();
    }

    public void getExtras(){
        Bundle bundle = getIntent().getExtras();
        round.getPlayers()[1].setTournamentScore(bundle.getInt("comp_tour_score", 0));
        round.getPlayers()[0].setTournamentScore(bundle.getInt("human_tour_score", 0));
        round.setTournamentMax(bundle.getInt("tournament_max", 0));
    }

    public void makeToast(String s){
        Toast.makeText(RoundActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    public void updateHumanHandView(){
        humanHandButtons = new Vector<Button>();
        Hand hand = round.getPlayers()[0].getHand();
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

    public void updateDrawer(){
        //Drawer for tiles
        drawerListViewItems = new Vector<>();
        String s = "Remaining tiles in the deck: " + round.getDeck().size();
        drawerListViewItems.add(s);
        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview_item, drawerListViewItems));
        s = "Turn: ";
        if(round.getTurn() == 0){
            s = s+"Human";
        }
        else{
            s = s+"Computer";
        }
        drawerListViewItems.add(s);
        s = "Human score: " + round.getPlayers()[0].getTournamentScore();
        drawerListViewItems.add(s);
        s = "Computer score: " + round.getPlayers()[1].getTournamentScore();
        drawerListViewItems.add(s);
        s = "Tournament Max score: " + round.getTournamentMax();
        drawerListViewItems.add(s);
    }

    public void endActivity(Activity act){
        Intent endRound = new Intent(RoundActivity.this, RoundEndActivity.class);
        endRound.putExtra("comp_round_score", round.getPlayers()[1].getRoundScore());
        endRound.putExtra("human_round_score", round.getPlayers()[0].getRoundScore());
        endRound.putExtra("comp_tour_score", round.getPlayers()[1].getTournamentScore());
        endRound.putExtra("human_tour_score", round.getPlayers()[0].getTournamentScore());
        endRound.putExtra("tournament_max", round.getTournamentMax());
        startActivity(endRound);
        finish();
    }

    public void updateComputerHandView(){
        Hand hand = round.getPlayers()[1].getHand();
        computerHandButtons = new Vector<Button>();
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.computer_hand);
        linearLayout.removeAllViews();
        TextView tv = new TextView(this);
        tv.setText("Computer hand: ");
        linearLayout.addView(tv);
        for(int i = 0; i < hand.size(); i++){
            Tile t = hand.getTileAt(i);
            computerHandButtons.addElement(new Button(this));
            computerHandButtons.elementAt(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t, computerHandButtons.elementAt(i));
            linearLayout.addView(computerHandButtons.elementAt(i));
        }
    }

    public void updateBoardView() {
        boardButtons = new Vector<Button>();
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.board);
        linearLayout.removeAllViews();
        TextView tv = new TextView(this);
        tv.setText("Board:  L  ");
        linearLayout.addView(tv);

        for(int i = 0; i < round.getBoard().size(); i++){
            Tile t = round.getBoard().getTileAt(i);
            boardButtons.addElement(new Button(this));
            boardButtons.elementAt(0).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            updateTileView(t, boardButtons.elementAt(i));
            linearLayout.addView(boardButtons.elementAt(i));
        }

        tv = new TextView(this);
        tv.setText("  R");
        linearLayout.addView(tv);
    }
    public void updateTileView(Tile tile, Button b){
        b.setBackgroundResource(R.drawable.button_border);
        b.setTextSize(10);
        String s = tile.getFirstPip() + "-" + tile.getSecondPip();
        b.setText(s);
    }

    public void updateAllViews(){
        updateBoardView();
        updateComputerHandView();
        updateHumanHandView();
        updateDrawer();
    }

    View.OnClickListener playButtonListener = (new View.OnClickListener() {
        public void onClick(View view) {
            if(round.getTurn() == 0){
                makeToast("It's your turn");
            }
            else{
                round.getBoard().addTileToRight(round.getPlayers()[1].getHand().playTileAt(0));
                updateBoardView();
                String s = "The computer played " + round.getBoard().getTileAt(round.getBoard().size()-1).toString() + "on the right";
                makeToast(s);
                //updateComputerHandView();
                round.switchTurn();
            }

            if(round.getPlayers()[1].getHand().isEmpty()){
                int score = round.getPlayers()[0].getHand().sumTiles();
                int tScore = round.getPlayers()[0].getTournamentScore() + score;
                round.getPlayers()[1].setRoundScore(score);
                round.getPlayers()[1].setTournamentScore(tScore);
                endActivity(RoundActivity.this);
            }

            updateAllViews();
        }
    });



    View.OnClickListener handButtonsHandler = (new View.OnClickListener() {
        public void onClick(View view) {
            for(int i = 0; i < round.getPlayers()[0].getHand().size(); i++){
                if(humanHandButtons.elementAt(i).getId() == view.getId()){
                    if(round.getTurn() == 0){
                        /*if (round.getBoard().checkRulesForPlacement(true, round.getPlayers()[0].getHand().getTileAt(i))){
                            round.getBoard().addTileToLeft(round.getPlayers()[0].getHand().playTileAt(i));
                            updateBoardView();
                            updateHumanHandView();
                            updateDrawer();
                            round.switchTurn();
                        }
                        else {
                            makeToast("You can't place it there");
                        }*/
                        Boolean result = round.getPlayers()[0].play(round.getBoard(), round.getDeck(), round.getPlayers()[1].isPassed(), RoundActivity.this, i);
                        if(result == null || result == true){
                           round.switchTurn();
                        }
                        if(round.getPlayers()[0].getHand().isEmpty()){
                            int score = round.getPlayers()[1].getHand().sumTiles();
                            int tScore = round.getPlayers()[1].getTournamentScore() + score;
                            round.getPlayers()[0].setRoundScore(score);
                            round.getPlayers()[0].setTournamentScore(tScore);
                            endActivity(RoundActivity.this);
                        }

                        updateAllViews();
                    }
                    else{
                        makeToast("It's computer's turn. Press Play for the computer. ");
                    }
                    //round.switchTurn();
                    //round.getBoard().addTileToLeft(round.getPlayers()[0].getHand().playTileAt(i));
                    //updateBoardView(round.getBoard().getTileAt(0), true);
                    //round.getPlayers()[0].getHand().printHand();
                    //updateHumanHandView(round.getPlayers()[0].getHand());
                }
            }
        }
    });

}
