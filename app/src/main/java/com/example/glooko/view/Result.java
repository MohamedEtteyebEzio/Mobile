package com.example.glooko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.glooko.R;


public class Result extends AppCompatActivity {

    private TextView tvres;
    private Button con;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        con = findViewById(R.id.resbtn);

        tvres = findViewById(R.id.tvres);
        Intent intent =getIntent();
        String  res =intent.getStringExtra("result");

        if(intent != null){
            tvres.setText(res);

        }
    con.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            if(res ==null){
                setResult(RESULT_OK,intent);

            }
        else {

                setResult(RESULT_CANCELED,intent);

            }

        }
    });
    }
}