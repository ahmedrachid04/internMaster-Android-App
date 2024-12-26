package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmerStagiaireActivity extends AppCompatActivity {

    // Pour afficher le texte reçu
    TextView textViewNom;
    TextView textViewPrenom;
    TextView textViewEcole;
    TextView textViewSpecialite;
    TextView textViewEmail;
    TextView textViewTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.confirmerstagiaire);

        // Initialisation du TextView
        textViewNom = findViewById(R.id.popup_emailEnt);
        textViewPrenom = findViewById(R.id.popup_teleEnt);
        textViewEcole = findViewById(R.id.popup_adresseEnt);
        textViewSpecialite = findViewById(R.id.popup_specialiteS);
        textViewEmail = findViewById(R.id.popup_emailS);
        textViewTelephone = findViewById(R.id.popup_teleS);

        // Récupérer le texte depuis l'Intent
        String receivedTextNom = getIntent().getStringExtra("TEXT_KEY_Nom");
        String receivedTextPrenom = getIntent().getStringExtra("TEXT_KEY_Prenom");
        String receivedTextEcole = getIntent().getStringExtra("TEXT_KEY_Ecole");
        String receivedTextSpecialite = getIntent().getStringExtra("TEXT_KEY_Specialite");
        String receivedTextEmail = getIntent().getStringExtra("TEXT_KEY_Email");
        String receivedTextTelephone = getIntent().getStringExtra("TEXT_KEY_Telephone");

        // Afficher le texte dans le TextView
        textViewNom.setText(receivedTextNom);
        textViewPrenom.setText(receivedTextPrenom);
        textViewEcole.setText(receivedTextEcole);
        textViewSpecialite.setText(receivedTextSpecialite);
        textViewEmail.setText(receivedTextEmail);
        textViewTelephone.setText(receivedTextTelephone);


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
                Intent intent = new Intent(ConfirmerStagiaireActivity.this, AcceuilStagiaireActivity.class);
                startActivity(intent);
            }
        });
    }
}