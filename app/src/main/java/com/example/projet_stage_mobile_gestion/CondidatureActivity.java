package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class CondidatureActivity extends AppCompatActivity {

    private ListView offersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.condidature);

        offersListView = findViewById(R.id.offers_list);

        // Exemple de données d'offre
        List<String> offers = new ArrayList<>();
        offers.add("Offre #1");
        offers.add("Offre #2");
        offers.add("Offre #3");
        offers.add("Offre #4");
        offers.add("Offre #5");

        // Adapter personnalisé
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.offer_item, R.id.offer_title, offers);
        offersListView.setAdapter(adapter);

// Initialisation du bouton Menu
        ImageView menuButton = findViewById(R.id.menu_icon);
        menuButton.setOnClickListener(v -> showPopupMenu(v));
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        // Infler le menu à partir d'un fichier de menu XML
        getMenuInflater().inflate(R.menu.menu_entreprise, popupMenu.getMenu());

        // Gestion des éléments du menu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil) {
                startActivity(new Intent(CondidatureActivity.this, ProfilEntrepriseActivity.class));
                return true;
            } else if (item.getItemId() == R.id.listeCondidat) {
                startActivity(new Intent(CondidatureActivity.this, CondidatureActivity.class));
                return true;
            } else {
                startActivity(new Intent(CondidatureActivity.this, AcceuilEntrepriseActivity.class));
                return true;
            }
        });
        popupMenu.show();
    }
}
