package com.example.glooko.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView tvage;
    final int REQUEST_CODE=1;
    private EditText mlevel;
    private SeekBar sbage;
    private Button con;
    private Button logout;

    private RadioGroup grb;
    private boolean fast;
    FirebaseAuth auth;
    FirebaseUser  user;
    private Controller controller = Controller.getIns();

    // Initialisation des composants graphiques
    private void init() {
        tvage = findViewById(R.id.tvage);
        mlevel = findViewById(R.id.mLevel);
        sbage = findViewById(R.id.skAge);
        con = findViewById(R.id.btn);
        logout = findViewById(R.id.btnlog);
        grb = findViewById(R.id.Grp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        if (user==null){

            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }



        init(); // Initialisation des composants graphiques

        // Action lors du changement sur la SeekBar
        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Log d'information
                Log.i("Information", "OnProgressChange " + progress);
                // Mise à jour du TextView affichant l'âge
                tvage.setText("Your Age : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Possibilité d'afficher un message lors du début du déplacement de la SeekBar
                // Toast.makeText(MainActivity.this, "Start touch seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Action à effectuer à l'arrêt du déplacement de la SeekBar
            }
        });

        // Gestionnaire d'événements pour le RadioGroup
        grb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Récupération du RadioButton sélectionné
                RadioButton selectedRadioButton = findViewById(checkedId);
                // Récupération de la valeur du RadioButton
                String selectedValue = selectedRadioButton.getText().toString();
                // Mise à jour du booléen 'fast' en fonction du choix de l'utilisateur
                fast = selectedValue.equals("Yes");
            }
        });

        // Gestionnaire d'événements pour le bouton
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérification si des champs obligatoires sont vides
                if (sbage.getProgress() == 0 || mlevel.getText().toString().isEmpty()) {
                    // Affichage d'un message d'erreur sous forme de toast
                    Toast.makeText(MainActivity.this, "Age or Blood Measurement are Empty", Toast.LENGTH_SHORT).show();
                } else {
                    // Création du patient et mise à jour de l'affichage avec le résultat
                    controller.createPatient(sbage.getProgress(), fast, Double.valueOf(mlevel.getText().toString()));

                    Intent intent =new Intent(getApplicationContext(), Result.class);
                    intent.putExtra("result",controller.getPatientRes());

                    startActivityForResult(intent, REQUEST_CODE);

                }
            }
        });

logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();

    }
});



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_CANCELED)
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show();
    }
}
