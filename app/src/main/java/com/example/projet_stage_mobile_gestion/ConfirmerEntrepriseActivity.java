package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmerEntrepriseActivity extends AppCompatActivity {

    // Pour afficher le texte reçu
    TextView textViewNom;
    TextView textViewEmail;
    TextView textViewTelephone;
    TextView textViewAdresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.confirmerentreprise);

        // Initialisation du TextView
        textViewNom = findViewById(R.id.textView);
        textViewEmail = findViewById(R.id.popup_emailEnt);
        textViewTelephone = findViewById(R.id.popup_teleEnt);
        textViewAdresse = findViewById(R.id.popup_adresseEnt);
        // Récupérer le texte depuis l'Intent
        String receivedTextNom = getIntent().getStringExtra("TEXT_KEY_Nom");
        String receivedTextEmail = getIntent().getStringExtra("TEXT_KEY_Email");
        String receivedTextTelephone = getIntent().getStringExtra("TEXT_KEY_Telephone");
        String receivedTextAdresse = getIntent().getStringExtra("TEXT_KEY_Adresse");
        String receivedTextPassword = getIntent().getStringExtra("TEXT_KEY_Password");
        String receivedTextConfirmerPassword = getIntent().getStringExtra("TEXT_KEY_ConfirmerPassword");
        // Afficher le texte dans le TextView
        textViewNom.setText(receivedTextNom);
        textViewEmail.setText(receivedTextEmail);
        textViewTelephone.setText(receivedTextTelephone);
        textViewAdresse.setText(receivedTextAdresse);


        // Lier le bouton "Modifier" avec son ID
        Button buttonModifier = findViewById(R.id.btn_update);

        // Ajouter un OnClickListener pour naviguer vers InscriptionEntreprise
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer un Intent pour lancer l'Activity InscriptionEntreprise
                Intent intent = new Intent(ConfirmerEntrepriseActivity.this, InscriptionEntrepriseActivity.class);
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
                Intent intent = new Intent(ConfirmerEntrepriseActivity.this, ProfilEntrepriseActivity.class);
                startActivity(intent);
            }
        });
    }
}