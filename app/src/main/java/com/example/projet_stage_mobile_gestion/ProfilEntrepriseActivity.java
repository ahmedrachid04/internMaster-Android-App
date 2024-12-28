package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

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

        // Initialisation du bouton Menu
        ImageView menuButton = findViewById(R.id.imageView_menu);
        menuButton.setOnClickListener(v -> showPopupMenu(v));  // Afficher le menu lors du clic sur le bouton
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        // Infler le menu à partir d'un fichier de menu XML
        getMenuInflater().inflate(R.menu.menu_entreprise, popupMenu.getMenu());

        // Gestion des éléments du menu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil) {
                startActivity(new Intent(ProfilEntrepriseActivity.this, ProfilEntrepriseActivity.class));
                return true;
            } else if (item.getItemId() == R.id.listeCondidat) {
                startActivity(new Intent(ProfilEntrepriseActivity.this, CondidatureActivity.class));
                return true;
            } else {
                startActivity(new Intent(ProfilEntrepriseActivity.this, AcceuilEntrepriseActivity.class));
                return true;
            }
        });
        popupMenu.show();
    }
}