package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcceuilStagiaireActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOffres;
    private OffreAdapter offreAdapter;
    private List<OfferModel> offerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilstagiaire1);  // Utilise le bon fichier XML

        // Initialiser le RecyclerView
        recyclerViewOffres = findViewById(R.id.recyclerView_offres);
        recyclerViewOffres.setLayoutManager(new LinearLayoutManager(this));

        // Créer des données statiques pour tester
        offerList = new ArrayList<>();

        // Créer des dates statiques pour tester (ou remplacer par des dates dynamiques)
        Date startDate = new Date();
        Date endDate = new Date();
        Date postDate = new Date();

        // Ajouter des offres statiques à la liste
        offerList.add(new OfferModel(1, "Stage Android", "Développement d'une application", Type.APPLICATION, "Informatique", "3 mois", startDate, endDate, postDate, 1));
        offerList.add(new OfferModel(2, "Alternance Java", "Développement Backend", Type.PFE, "Informatique", "6 mois", startDate, endDate, postDate, 2));
        offerList.add(new OfferModel(3, "Stage Marketing", "Analyse de marché", Type.PFA, "Marketing", "2 mois", startDate, endDate, postDate, 3));

        // Initialiser l'adaptateur et lier au RecyclerView
        offreAdapter = new OffreAdapter(offerList,this);
        recyclerViewOffres.setAdapter(offreAdapter);

        // Ajouter l'événement de clic sur l'ImageView pour accéder au profil
        ImageView imageViewProfil = findViewById(R.id.imageView_profil);
        imageViewProfil.setOnClickListener(v -> {
            // Rediriger vers l'activité de profil
            Intent intent = new Intent(AcceuilStagiaireActivity.this, ProfilStagiaireActivity.class);
            startActivity(intent);
        });
    }
}