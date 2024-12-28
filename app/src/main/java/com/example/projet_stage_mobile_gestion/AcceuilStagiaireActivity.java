package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Type;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcceuilStagiaireActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOffres;
    private OffreAdapter offreAdapter;
    private List<OfferModel> offerList;

    private long currentStudentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilstagiaire1);  // Utilise le bon fichier XML

        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(this);

        currentStudentId=getIntent().getLongExtra("STUD_ID",1);

        // Initialiser le RecyclerView
        recyclerViewOffres = findViewById(R.id.recyclerView_offres);
        recyclerViewOffres.setLayoutManager(new LinearLayoutManager(this));

        // Créer des données statiques pour tester
        offerList = helper.getAllOffers();

        // Initialiser l'adaptateur et lier au RecyclerView
        offreAdapter = new OffreAdapter(offerList,this, currentStudentId);
        recyclerViewOffres.setAdapter(offreAdapter);

        // Ajouter l'événement de clic sur l'ImageView pour accéder au profil
        ImageView imageViewProfil = findViewById(R.id.imageView_profil);
        imageViewProfil.setOnClickListener(v -> {
            // Rediriger vers l'activité de profil
            Intent intent = new Intent(AcceuilStagiaireActivity.this, ProfilStagiaireActivity.class);
            intent.putExtra("STUD_ID",currentStudentId);
            startActivity(intent);
        });

    }
}