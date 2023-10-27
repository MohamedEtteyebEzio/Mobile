package com.example.glooko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView tvage;
private TextView tvres;
private EditText mlevel;
private RadioButton yes;
private RadioButton no;
private SeekBar sbage;
private Button con;

private  void init(){
    tvage=(TextView) findViewById(R.id.tvage);
    tvres=(TextView) findViewById(R.id.tvres);
    mlevel=(EditText) findViewById(R.id.mLevel);
    yes=(RadioButton) findViewById(R.id.Yes);
    no=(RadioButton) findViewById(R.id.No);
    sbage=(SeekBar) findViewById(R.id.skAge);
    con=(Button) findViewById(R.id.btn);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //action de changment sur le seekbar
        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information","OnProgressChange "+progress);
                tvage.setText("Your Age : "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}