package com.example.glooko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.glooko.R;

public class HomePage extends AppCompatActivity {
    private Button con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_page);
        con = (Button) findViewById(R.id.homebtn);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomePage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}