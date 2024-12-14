package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmerEntrepriseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.confirmerentreprise);

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
