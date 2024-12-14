package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmerStagiaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.confirmerstagiaire);

        // Lier le bouton "Modifier" avec son ID
        Button buttonModifier = findViewById(R.id.btn_update);

        // Ajouter un OnClickListener pour naviguer vers InscriptionEntreprise
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer un Intent pour lancer l'Activity InscriptionEntreprise
                Intent intent = new Intent(ConfirmerStagiaireActivity.this, InscriptionStagiaireActivity.class);
                startActivity(intent);
            }
        });
        // Lier le bouton "Confirmer" avec son ID
        Button buttonConfirmer = findViewById(R.id.btn_confirm);

        // Ajouter un OnClickListener pour naviguer vers InscriptionEntreprise
        buttonConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer un Intent pour lancer l'Activity InscriptionEntreprise
                Intent intent = new Intent(ConfirmerStagiaireActivity.this, ProfilStagiaireActivity.class);
                startActivity(intent);
            }
        });
    }
}