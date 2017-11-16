package edu.ramapo.sminev.longana.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.ramapo.sminev.longana.R;

public class EndTournamentActivity extends AppCompatActivity {

    private Button play;
    private int computerTScore, humanTScore;
    private String winner;
    private TextView compTScoreView, humanTScoreView, winnerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_tournament);
        Bundle bundle = getIntent().getExtras();;
        play = (Button) findViewById(R.id.play_button);
        play.setOnClickListener(playButtonListener);
        compTScoreView = (TextView) findViewById(R.id.comp_tour_score);
        humanTScoreView = (TextView) findViewById(R.id.human_tour_score);
        winnerView = (TextView) findViewById(R.id.winner);
        computerTScore = bundle.getInt("comp_tour_score", 0);
        humanTScore = bundle.getInt("human_tour_score", 0);
        winner = bundle.getString("winner", "draw");
        updateScores();
    }

    public void updateScores(){
        winnerView.setText("Tournament Winner: " + winner);
        compTScoreView.setText("Computer Tournament Score: " + computerTScore);
        humanTScoreView.setText("Human Tournament Score: " + humanTScore);
    }


    View.OnClickListener playButtonListener = (new View.OnClickListener() {
        public void onClick(View view) {
            Intent endRound = new Intent(EndTournamentActivity.this, StartingActivity.class);
            startActivity(endRound);
            finish();
        }
    });
}