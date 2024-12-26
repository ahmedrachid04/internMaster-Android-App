package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

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

        // Créer une instance de DatabaseHelper pour récupérer les données
        InternshipDataBaseHelper dbHelper = new InternshipDataBaseHelper(this);
        offerList = dbHelper.getOffersWithCompanyName();  // Appel de la méthode qui retourne les offres avec le nom de l'entreprise

        // Initialiser l'adaptateur et lier au RecyclerView
        offreAdapter = new OffreAdapter(offerList);
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

