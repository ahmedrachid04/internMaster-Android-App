package com.example.projet_stage_mobile_gestion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilStagiaireActivity extends AppCompatActivity {

    TextView textViewNom;
    TextView textViewEcole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profilstagiaire);

        // Initialisation du TextView
        textViewNom = findViewById(R.id.name);
        textViewEcole = findViewById(R.id.school);

        // Récupérer le texte depuis l'Intent
        String receivedTextNom = getIntent().getStringExtra("TEXT_KEY_Nom");
        String receivedTextEcole = getIntent().getStringExtra("TEXT_KEY_Ecole");

        // Afficher le texte dans le TextView
        textViewNom.setText(receivedTextNom);
        textViewEcole.setText(receivedTextEcole);


    }
}