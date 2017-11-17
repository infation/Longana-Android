package edu.ramapo.sminev.longana.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.Vector;

import edu.ramapo.sminev.longana.Model.Tournament;
import edu.ramapo.sminev.longana.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartingActivity extends AppCompatActivity {
    private Button startButton, loadButton;
    private EditText tournamentScore;
    private int tournamentMaxScore;
    private Spinner filesSpinner;
    private String selectedFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(startButtonListener);
        loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(loadButtonListener);
        tournamentScore = (EditText) findViewById(R.id.tournament_score);
        filesSpinner = (Spinner) findViewById(R.id.files_spinner);
        tournamentScore.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0 && Integer.parseInt(s.toString())>0) {
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

        ArrayAdapter filePickerAdapter=new ArrayAdapter<>(StartingActivity.this,android.R.layout.simple_spinner_item, getAllTextFiles());
        filePickerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filesSpinner.setAdapter(filePickerAdapter);
        filesSpinner.setBackgroundResource(R.drawable.button_border);

        filesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    selectedFile = filesSpinner.getItemAtPosition(position).toString();
                    loadButton.setVisibility(View.VISIBLE);
                    tournamentScore.setVisibility(View.INVISIBLE);
                }
                else{
                    loadButton.setVisibility(View.INVISIBLE);
                    tournamentScore.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    View.OnClickListener startButtonListener = (new View.OnClickListener() {
        public void onClick(View view) {
            Intent endRound = new Intent(StartingActivity.this, RoundActivity.class);
            endRound.putExtra("load", false);
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


    private Vector<String> getAllTextFiles()
    {
        Vector<String> textFiles=new Vector<>();
        textFiles.addElement("Select file");
        //Finding the sdcard path on the tablet
        File sdcard = Environment.getExternalStorageDirectory().getAbsoluteFile();
        //It have to be matched with the directory in SDCard
        //File file = new File("/storage/sdcard0");

        File[] files=sdcard.listFiles();

        for(int i=0; i<files.length; i++)
        {
            File file = files[i];
            //It's assumed that all file in the path are in supported type
            String filePath = file.getPath().substring(17);
            if(filePath.endsWith(".txt")) // Condition to check .txt file extension
                textFiles.add(filePath.substring(filePath.indexOf('0')+2));
        }
        return textFiles;
    }

    View.OnClickListener loadButtonListener = (new View.OnClickListener() {
        public void onClick(View view) {
            Intent endRound = new Intent(StartingActivity.this, RoundActivity.class);
            //tournament.getParser().loadFile(tournament, "/Download/test.txt");
            endRound.putExtra("load", true);
            System.out.println(selectedFile);
            endRound.putExtra("whichFile", selectedFile);
            startActivity(endRound);
            finish();
        }
    });
}
