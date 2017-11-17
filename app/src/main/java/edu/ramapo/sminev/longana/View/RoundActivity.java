package edu.ramapo.sminev.longana.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import java.io.IOException;
import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Hand;
import edu.ramapo.sminev.longana.Model.Round;
import edu.ramapo.sminev.longana.Model.Tile;
import edu.ramapo.sminev.longana.Model.Tournament;
import edu.ramapo.sminev.longana.R;

public class RoundActivity extends AppCompatActivity {

    private boolean isEnginePlaced = false;
    private int playedTileIndex, engineTileIndex;
    private HumanHandView humanView;
    private DeckView deckView;
    private BoardView boardView;
    private ComputerHandView computerView;
    private Button playButton, saveButton, drawButton, hintButton;
    private Tournament tournament;
    private Round round;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_round);

        drawButton = findViewById(R.id.draw_button);
        drawButton.setOnClickListener(drawButtonListener);
        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(playButtonListener);
        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(saveButtonListener);
        hintButton = findViewById(R.id.hint_button);
        hintButton.setOnClickListener(hintButtonHandler);
        humanView = new HumanHandView(this);
        computerView = new ComputerHandView(this);
        deckView = new DeckView(this);
        boardView = new BoardView(this);

        if(getIntent().getExtras().getBoolean("load")){
            tournament = new Tournament(this);
            tournament.getParser().loadFile(tournament, "/Download/test.txt");
            round = tournament.getRound();
            if(round.getBoard().size()>0){
                unlockButtons();
            }
            updateAllViews(true);
        }
        else{
            tournament = new Tournament(this);
            round = tournament.getRound();
            getExtras();
            engineTileIndex = round.startRound(humanView, computerView);
        }

    }

    public void unlockButtons(){
        saveButton.setEnabled(true);
        hintButton.setEnabled(true);
        playButton.setEnabled(true);
        drawButton.setVisibility(View.INVISIBLE);
        drawButton.setClickable(false);
    }


    public void getExtras(){
        Bundle bundle = getIntent().getExtras();
        tournament.setComputerTourScore(bundle.getInt("comp_tour_score", 0));
        tournament.setHumanTourScore(bundle.getInt("human_tour_score", 0));
        tournament.setMaxTourScore(bundle.getInt("tournament_max", 0));
        tournament.setRoundNum(bundle.getInt("round_num", tournament.getRoundNum()));
        round.setEngine(bundle.getInt("engine", 6));
    }

    public void makeToast(String s){
        Toast.makeText(RoundActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    public void endActivity(){
        Intent endRound = new Intent(RoundActivity.this, RoundEndActivity.class);
        endRound.putExtra("comp_round_score", round.getPlayers()[1].getRoundScore());
        endRound.putExtra("human_round_score", round.getPlayers()[0].getRoundScore());
        endRound.putExtra("comp_tour_score", tournament.getComputerTourScore());
        endRound.putExtra("human_tour_score", tournament.getHumanTourScore());
        endRound.putExtra("tournament_max", tournament.getMaxTourScore());
        endRound.putExtra("round_num", tournament.getRoundNum());
        endRound.putExtra("engine" , round.getEngine());
        startActivity(endRound);
        finish();
    }

    public void updateAllViews(boolean isEnabled){
        boardView.updateBoardView(round.getBoard(),isEnabled);
        computerView.updateComputerHandView(round.getPlayers()[1].getHand(), isEnabled);
        humanView.updateView(round.getPlayers()[0].getHand(), isEnabled);
        deckView.updateView(round, tournament.getMaxTourScore());
    }

    View.OnClickListener playButtonListener = (new View.OnClickListener() {

        public void onClick(View view) {
            if(isEnginePlaced) {
                if (round.getTurn() == 1) {

                    round.getPlayers()[1].playTile(round, 0, humanView);

                    if (round.hasEnded()) {
                        endActivity();
                    }

                    updateAllViews(true);
                }
            }
            else{
                round.getBoard().addTileToLeft(round.getPlayers()[round.getTurn()].getHand().playTileAt(engineTileIndex));
                round.switchTurn();
                isEnginePlaced = true;
                updateAllViews(true);
            }
        }
    });

    View.OnClickListener saveButtonListener = (new View.OnClickListener() {

        public void onClick(View view) {
            try {
                tournament.getParser().saveFile(tournament);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish();
        }
    });


    View.OnClickListener drawButtonListener = (new View.OnClickListener() {

        public void onClick(View view) {
            round.drawForEngine();
            updateAllViews(false);
            int result = round.checkForEngine(humanView, computerView);
            if(result == -1){
                makeToast("The engine is not found. Press DRAW to draw tiles.");
            }
            else{
                makeToast("THE ENGINE IS FOUND. PRESS PLAY TO PLAY IT.");
                engineTileIndex = result;
                unlockButtons();
            }
        }
    });

    DialogInterface.OnClickListener chooseLeftOrRightListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    round.getPlayers()[0].playDoubleTile(round, playedTileIndex, false);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    round.getPlayers()[0].playDoubleTile(round, playedTileIndex, true);
                    //No button clicked
                    break;
            }
            if(round.hasEnded()){
                endActivity();
            }
            updateAllViews(true);
        }
    };


    View.OnClickListener hintButtonHandler = (new View.OnClickListener() {
        public void onClick(View view) {
            if(round.getTurn() == 0){
                round.getPlayers()[0].hint(round);
            }
            else{
                makeToast("Not your turn.");
            }
        }
    });

    View.OnClickListener handButtonsHandler = (new View.OnClickListener() {
        public void onClick(View view) {
            for(int i = 0; i < round.getPlayers()[0].getHand().size(); i++){
                if(humanView.getView().elementAt(i).getView().getId() == view.getId()) {
                    if (round.getTurn() == 0) {

                        round.getPlayers()[0].playTile(round, i, humanView);
                        playedTileIndex = i;
                        if (round.hasEnded()) {
                            endActivity();
                        }

                        updateAllViews(true);
                    }
                    else {
                        makeToast("It's computer's turn. Press Play for the computer. ");
                    }
                }
            }
        }
    });
}
