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
    private boolean fast;
    private Controller controller = new Controller();

    // Initialisation des composants graphiques
    private void init() {
        tvage = findViewById(R.id.tvage);
        tvres = findViewById(R.id.tvres);
        mlevel = findViewById(R.id.mLevel);
        sbage = findViewById(R.id.skAge);
        con = findViewById(R.id.btn);
        grb = findViewById(R.id.Grp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    tvres.setText(controller.getPatientRes());
                }
            }
        });
    }
}
