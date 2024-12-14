package com.example.projet_stage_mobile_gestion;

import android.content.Intent;  // Importez la classe Intent
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ACceuilPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilpage); // Lien avec le fichier XML acceuilpage.xml

        // Référence au bouton
        Button menuButton = findViewById(R.id.menuButton);

        // Ajouter un écouteur pour le bouton
        menuButton.setOnClickListener(v -> {
            // Créer un Intent pour passer à MainActivity
            Intent intent = new Intent(ACceuilPageActivity.this, MainActivity.class);

            // Démarrer l'activité
            startActivity(intent);
        });
    }
}