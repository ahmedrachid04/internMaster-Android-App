package com.example.projet_stage_mobile_gestion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OffreAdapter offreAdapter;
    private List<OfferModel> offerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company);

        offerList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_offres);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        offreAdapter = new OffreAdapter(offerList);
        recyclerView.setAdapter(offreAdapter);

        // FloatingActionButton pour ajouter une nouvelle offre
        FloatingActionButton fabAddOffer = findViewById(R.id.fab_add_offer);
        fabAddOffer.setOnClickListener(v -> showAddOfferDialog());
    }

    private void showAddOfferDialog() {
        // Créez un Dialog pour ajouter une nouvelle offre
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_offer, null);
        builder.setView(dialogView);

        EditText etCompanyName = dialogView.findViewById(R.id.et_company_name);
        EditText etDomaine = dialogView.findViewById(R.id.et_domaine);
        EditText etType = dialogView.findViewById(R.id.et_type);
        EditText etDuration = dialogView.findViewById(R.id.et_duration);
        EditText etProfil = dialogView.findViewById(R.id.et_profil_rechercher);


        builder.setPositiveButton("Ajouter", (dialog, which) -> {
            String companyName = etCompanyName.getText().toString();
            String domaine = etDomaine.getText().toString();
            String typeString = etType.getText().toString();  // Type sous forme de chaîne
            String duration = etDuration.getText().toString();
            String profil = etProfil.getText().toString();

            // Valider les champs
            if (companyName.isEmpty() || domaine.isEmpty() || typeString.isEmpty() || duration.isEmpty()) {
                Toast.makeText(CompanyActivity.this, "Tous les champs sont requis!", Toast.LENGTH_SHORT).show();
            } else {
                // Convertir le type en énumération Type (Assurez-vous que Type est défini)
                Type type = Type.valueOf(typeString.toUpperCase());  // Utilise la valeur de type en majuscule

                // Ajouter l'offre à la liste
                // Nous utilisons un constructeur avec 9 paramètres (le constructeur sans 'id' et 'companyId')
                OfferModel newOffer = new OfferModel(
                        "Default Title",  // Titre par défaut
                        "Default Description",  // Description par défaut
                        type,  // Type de l'offre
                        domaine,  // Domaine de l'offre
                        duration,  // Durée de l'offre
                        new Date(),  // Date de début, ajustez si nécessaire
                        new Date(),  // Date de fin, ajustez si nécessaire
                        new Date(),  // Date de publication
                        1,  // Company ID par défaut
                        companyName  // Nom de l'entreprise
                );

                offerList.add(newOffer);
                offreAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
}
