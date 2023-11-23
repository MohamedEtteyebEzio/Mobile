package com.example.glooko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.glooko.R;
import com.example.glooko.controller.Controller;

public class MainActivity extends AppCompatActivity {
private TextView tvage;
private TextView tvres;
private EditText mlevel;
private SeekBar sbage;
private Button con;
private RadioGroup grb;
private  boolean fast;
private Controller controller =new Controller();
private  void init(){
    tvage=(TextView) findViewById(R.id.tvage);
    tvres=(TextView) findViewById(R.id.tvres);
    mlevel=(EditText) findViewById(R.id.mLevel);
    sbage=(SeekBar) findViewById(R.id.skAge);
    con=(Button) findViewById(R.id.btn);
    grb=(RadioGroup) findViewById(R.id.Grp);



    }

    private  void calcu(){

        int age = sbage.getProgress();
        double level=Double.valueOf(mlevel.getText().toString());
        String normal= "The level of blood sugar is normal.";
        String high= "The blood sugar level is too high." ;
        String low= "The blood sugar level is too low." ;

        if (fast) {
            if (age < 120 && age >= 13) {
                if (level >= 5.0 && level <= 7.2) {
                    tvres.setText(normal);
                } else if (level > 7.2) {
                    tvres.setText(high);

                } else if (level < 5.0) {

                    tvres.setText(low);

                }
            } else if (age < 13 && age > 6) {
                if (level >= 5.0 && level <= 10.0) {
                    tvres.setText(normal);
                } else if (level > 10.0) {
                    tvres.setText(high);

                } else if (level < 5.0) {

                    tvres.setText(low);

                }

            } else if (age < 6 && age > 1) {
                if (level >= 5.5 && level <= 10.0) {
                    tvres.setText(normal);
                } else if (level > 10.0) {
                    tvres.setText(high);

                } else if (level < 5.5) {

                    tvres.setText(low);

                }

            }


        } else if (!fast) {
            if (age > 6 && level < 10.5) {
                tvres.setText(normal);
            } else {
                tvres.setText(high);
            }

        }

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
                //Toast.makeText(MainActivity.this,"Start touch seekbar",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        grb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton selectedRadioButton = findViewById(checkedId);
                String selectedValue = selectedRadioButton.getText().toString();
                if (selectedValue.equals("Yes")) {
                    fast=true;
                }
                else {
                    fast=false;
                }
            }
        });



        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sbage.getProgress()==0||mlevel.getText().toString().isEmpty() )
                {
                    Toast.makeText(MainActivity.this, "Age or Blood Measurement are Empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    controller.createPatient(sbage.getProgress(),fast,Double.valueOf(mlevel.getText().toString()));
                    tvres.setText(controller.getPatientRes());

                }

            }
        });

    }

}