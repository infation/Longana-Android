package edu.ramapo.sminev.longana.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.ramapo.sminev.longana.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartingActivity extends AppCompatActivity {
    private Button startButton, loadButton;
    private EditText tournamentScore;
    private int tournamentMaxScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_starting);
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(startButtonListener);
        loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(loadButtonListener);
        tournamentScore = (EditText) findViewById(R.id.tournament_score);

        tournamentScore.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    tournamentMaxScore = Integer.parseInt(tournamentScore.getText().toString());
                    startButton.setClickable(true);
                    startButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

    }

    View.OnClickListener startButtonListener = (new View.OnClickListener() {
        public void onClick(View view) {
            Intent endRound = new Intent(StartingActivity.this, RoundActivity.class);
            endRound.putExtra("comp_round_score", 0);
            endRound.putExtra("human_round_score", 0);
            endRound.putExtra("comp_tour_score", 0);
            endRound.putExtra("human_tour_score", 0);
            endRound.putExtra("tournament_max", tournamentMaxScore);
            endRound.putExtra("engine", 6);
            startActivity(endRound);
            finish();
        }
    });


    View.OnClickListener loadButtonListener = (new View.OnClickListener() {
        public void onClick(View view) {

        }
    });
}
