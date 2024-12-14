package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InscriptionPageActivity extends AppCompatActivity {

    // Déclarations des éléments d'interface utilisateur
    private ImageView homeIcon;
    private TextView youAreText;
    private Button entrepriseButton;
    private TextView ouText;
    private Button stagiaireButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscriptionpage); // Utilisation du fichier XML nommé inscriptionpage

        // Initialisation des éléments de l'interface
        homeIcon = findViewById(R.id.homeIcon);
        youAreText = findViewById(R.id.youAreText);
        entrepriseButton = findViewById(R.id.entrepriseButton);
        ouText = findViewById(R.id.ouText);
        stagiaireButton = findViewById(R.id.stagiaireButton);

        // Action pour le bouton Entreprise
        entrepriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entrepriseIntent = new Intent(InscriptionPageActivity.this, InscriptionEntrepriseActivity.class);
                startActivity(entrepriseIntent);
            }
        });

        // Action pour le bouton Stagiaire
        stagiaireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stagiaireIntent = new Intent(InscriptionPageActivity.this, InscriptionStagiaireActivity.class);
                startActivity(stagiaireIntent);
            }
        });

        // Action pour l'icône Home
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(InscriptionPageActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}
