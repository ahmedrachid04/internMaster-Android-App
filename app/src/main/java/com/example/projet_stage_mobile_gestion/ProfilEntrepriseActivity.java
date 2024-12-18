package com.example.projet_stage_mobile_gestion;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilEntrepriseActivity extends AppCompatActivity {

    TextView textViewNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profilentreprise);

        // Initialisation du TextView
        textViewNom = findViewById(R.id.title);

        // Récupérer le texte depuis l'Intent
        String receivedTextNom = getIntent().getStringExtra("TEXT_KEY_Nom");

        // Afficher le texte dans le TextView
        textViewNom.setText(receivedTextNom);

    }
}