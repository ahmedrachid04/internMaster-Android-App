package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilStagiaireActivity extends AppCompatActivity {

    TextView textViewNom;
    TextView textViewEcole;
    ImageView menuButton;

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

        menuButton = findViewById(R.id.imageView_menu_stagiaire);
        menuButton.setOnClickListener(v -> showPopupMenu(v));
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        // Infler le menu à partir d'un fichier de menu XML
        getMenuInflater().inflate(R.menu.menu_stagiaire, popupMenu.getMenu());

        // Gestion des éléments du menu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil) {
                startActivity(new Intent(ProfilStagiaireActivity.this, ProfilStagiaireActivity.class));
                return true;
            }  else {
                startActivity(new Intent(ProfilStagiaireActivity.this, AcceuilStagiaireActivity.class));
                return true;
            }
        });
        popupMenu.show();
    }
}