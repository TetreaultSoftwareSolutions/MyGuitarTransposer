package com.example.btet.myguitartransposer;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class transposes a guitar chord and handles the UI elements.
 * @author Ben Tetreault
 * @version 6/1/2016
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button okButton,clearButton;
    EditText firstKey, secondKey, desiredChord;
    TextView result;

    /**
     * Instantiate the Activity and all of it's components.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initiates the buttons
        okButton = (Button)findViewById(R.id.button);
        clearButton = (Button)findViewById(R.id.button2);
        //Initiates the EditText fields
        firstKey = (EditText)findViewById(R.id.editText1);
        secondKey = (EditText)findViewById(R.id.editText2);
        desiredChord = (EditText)findViewById(R.id.editText3);
        //Initiates a TextView so that I can display the result
        result = (TextView)findViewById(R.id.textView4);
        //Gotta have those Event Listeners
        okButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);



    }

    /**
     * Finds the distance between the starting key and the end key, and finds the chord that is
     * that distance away from the desired chord.
     * @param startKey The starting Key
     * @param endKey The target Key
     * @param chord The chord that is to be transposed
     * @return The transposed chord
     */
    private String transposeChords(String startKey, String endKey, String chord){

        String result = "";
        //This array holds all of the keys that are possible. The list is iterated twice to avoid
        //array out of bounds. Also possibly with a circular array
        String[] letters = {"A","B","C","D","E","F","G","A","B","C","D","E","F","G"};

        int interval = 0;
        int startPoint = 0;
        int endPoint = 0;
        int noteLocation = 0;
        //gets the starting Key position
        for(int i=0; i < letters.length; i++){
            if(letters[i].equals(startKey)){
                startPoint = i;
            }
        }
        //gets the target key position
        for(int i=0; i < letters.length; i++){
            if(letters[i].equals(endKey)){
                endPoint = i;
            }
        }
        //Calculates the distance between the start key and target key
        interval = endPoint - startPoint;
        //gets the position of the Chord in the array
        for(int i=0; i < letters.length; i++){
            if(letters[i].equals(chord)){
                noteLocation = i;
            }
        }
        //This starts at the chord location, and increases the space by the distance between
        //the start key and target key, thus effectively transposing the guitar chord
        result = letters[noteLocation+interval];

        return result;
    }

    /**
     * On the click of the OK button, get the values of the keys from the EditTexts, and
     * call transposeChords to find the right chord
     * @param v The view that holds the editTexts
     */
    @Override
    public void onClick(View v) {

        String startKey = firstKey.getText().toString();
        String endKey = secondKey.getText().toString();
        String chord = desiredChord.getText().toString();

        String resultChord = transposeChords(startKey, endKey, chord);

        result.setText(resultChord);


    }

    /**
     * Clears the editTexts
     * @param v
     */
    public void onClick2(View v){


        firstKey.getText().clear();
        secondKey.getText().clear();
        desiredChord.getText().clear();


    }
}
