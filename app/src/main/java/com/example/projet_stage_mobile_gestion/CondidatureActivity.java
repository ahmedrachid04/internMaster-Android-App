package com.example.projet_stage_mobile_gestion;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

        // Gestion des clics sur le bouton (à implémenter dans l'adapter personnalisé si nécessaire)
    }
}
