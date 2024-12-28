// AcceuilEntrepriseActivity.java
package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcceuilEntrepriseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOffres;
    private OffreAdapter2 offreAdapter;
    private List<OfferModel> offerList;
    private AppCompatImageButton fabAddOffer;
    private LinearLayout formLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilentreprise1);

        // Initialisation des vues
        recyclerViewOffres = findViewById(R.id.recyclerView_offres);
        fabAddOffer = findViewById(R.id.fab_add_offer);
        formLayout = findViewById(R.id.formLayout);
        formLayout.setVisibility(View.GONE);  // Cache le formulaire par défaut

        // Configuration de RecyclerView
        recyclerViewOffres.setLayoutManager(new LinearLayoutManager(this));

        // Liste des offres
        offerList = new ArrayList<>();
        Date startDate = new Date();
        Date endDate = new Date();
        Date postDate = new Date();
        offerList.add(new OfferModel(1, "Stage Android", "Développement d'une application", Type.APPLICATION, "Informatique", "3 mois", startDate, endDate, postDate, 1));
        offerList.add(new OfferModel(2, "Alternance Java", "Développement Backend", Type.PFE, "Informatique", "6 mois", startDate, endDate, postDate, 2));
        offerList.add(new OfferModel(3, "Stage Marketing", "Analyse de marché", Type.PFA, "Marketing", "2 mois", startDate, endDate, postDate, 3));

        // Initialiser l'adaptateur
        offreAdapter = new OffreAdapter2(offerList);
        recyclerViewOffres.setAdapter(offreAdapter);

        // Ajouter un gestionnaire pour l'icône du menu
        findViewById(R.id.imageView_menu).setOnClickListener(v -> showPopupMenu(v));

        // Affichage du formulaire lors du clic sur le bouton +
        fabAddOffer.setOnClickListener(v -> formLayout.setVisibility(View.VISIBLE));  // Affiche le formulaire

        // Validation et ajout des données à la RecyclerView
        findViewById(R.id.submit_button).setOnClickListener(view -> {
            EditText etCompanyName = findViewById(R.id.et_company_name);
            EditText etDomaine = findViewById(R.id.et_domaine);
            EditText etType = findViewById(R.id.et_type);
            EditText etDuration = findViewById(R.id.et_duration);
            EditText etDuration1 = findViewById(R.id.et_duration1);
            EditText etProfil = findViewById(R.id.et_profil_rechercher);

            String companyName = etCompanyName.getText().toString();
            String domaine = etDomaine.getText().toString();
            String type = etType.getText().toString();
            String duration = etDuration.getText().toString();
            String datedebut = etDuration1.getText().toString();
            String profil = etProfil.getText().toString();

            // Valider les entrées
            if (companyName.isEmpty() || domaine.isEmpty() || type.isEmpty() || duration.isEmpty() || datedebut.isEmpty() || profil.isEmpty()) {
                Toast.makeText(AcceuilEntrepriseActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Ajouter l'offre à la liste
                offerList.add(new OfferModel(offerList.size() + 1, companyName, domaine, Type.APPLICATION, type, duration, new Date(), new Date(), new Date(), 1));
                offreAdapter.notifyDataSetChanged(); // Mettre à jour RecyclerView
                formLayout.setVisibility(View.GONE); // Cacher le formulaire après ajout
                Toast.makeText(AcceuilEntrepriseActivity.this, "Offre ajoutée avec succès", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Afficher le PopupMenu
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        // Infler le menu à partir d'un fichier de menu XML
        getMenuInflater().inflate(R.menu.menu_entreprise, popupMenu.getMenu());

        // Gestion des éléments du menu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil) {
                startActivity(new Intent(AcceuilEntrepriseActivity.this, ProfilEntrepriseActivity.class));
                return true;
            } else if (item.getItemId() == R.id.listeCondidat) {
                startActivity(new Intent(AcceuilEntrepriseActivity.this, ListCandidatActivity.class));
                return true;
            } else {
                Toast.makeText(AcceuilEntrepriseActivity.this, "Autre option sélectionnée", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        popupMenu.show();
    }
}
