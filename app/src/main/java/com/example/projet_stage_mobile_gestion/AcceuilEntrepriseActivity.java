// AcceuilEntrepriseActivity.java
package com.example.projet_stage_mobile_gestion;

import android.app.DatePickerDialog;
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

import com.example.projet_stage_mobile_gestion.DataBase.Models.CompanyModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Type;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AcceuilEntrepriseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOffres;
    private OffreAdapter2 offreAdapter;
    private List<OfferModel> offerList;
    private AppCompatImageButton fabAddOffer;
    private LinearLayout formLayout;
    private long currentCompId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilentreprise1);

        currentCompId = getIntent().getLongExtra("COMP_ID", 1);

        InternshipDataBaseHelper helper = new InternshipDataBaseHelper(this);
        CompanyModel currentComp = helper.getCompaniesById(currentCompId);

        // Initialisation des vues
        recyclerViewOffres = findViewById(R.id.recyclerView_offres);
        fabAddOffer = findViewById(R.id.fab_add_offer);
        formLayout = findViewById(R.id.formLayout);
        formLayout.setVisibility(View.GONE); // Cache le formulaire par défaut

        // Configuration de RecyclerView
        recyclerViewOffres.setLayoutManager(new LinearLayoutManager(this));

        // Liste des offres
        offerList = helper.getCompnysOffers(currentCompId);

        // Initialiser l'adaptateur
        offreAdapter = new OffreAdapter2(offerList, this);
        recyclerViewOffres.setAdapter(offreAdapter);

        // Ajouter un gestionnaire pour l'icône du menu
        findViewById(R.id.imageView_menu).setOnClickListener(this::showPopupMenu);

        // Affichage du formulaire lors du clic sur le bouton +
        fabAddOffer.setOnClickListener(v -> {
            formLayout.setVisibility(View.VISIBLE);
            formLayout.bringToFront();
        });

        // Validation et ajout des données à la RecyclerView
        findViewById(R.id.submit_button).setOnClickListener(view -> {
            EditText etTitle = findViewById(R.id.et_title);
            EditText etDomaine = findViewById(R.id.et_domaine);
            EditText etType = findViewById(R.id.et_type);
            EditText etDuration = findViewById(R.id.et_duration);
            EditText etDuration1 = findViewById(R.id.et_duration1);
            EditText etDuration2 = findViewById(R.id.et_duration2);
            EditText etProfil = findViewById(R.id.et_profil);

            String title = etTitle.getText().toString().trim();
            String domaine = etDomaine.getText().toString().trim();
            String type = etType.getText().toString().trim();
            String duration = etDuration.getText().toString().trim();
            String datedebut = etDuration1.getText().toString().trim();
            String datefin = etDuration2.getText().toString().trim();
            String profil = etProfil.getText().toString().trim();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            // Valider les entrées
            if (title.isEmpty() || domaine.isEmpty() || type.isEmpty() || duration.isEmpty() || datedebut.isEmpty() || datefin.isEmpty() || profil.isEmpty()) {
                Toast.makeText(AcceuilEntrepriseActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    Date dateDebutParsed = dateFormat.parse(datedebut);
                    Date dateFinParsed = dateFormat.parse(datefin);

                    if (dateDebutParsed == null || dateFinParsed == null || dateDebutParsed.after(dateFinParsed)) {
                        Toast.makeText(AcceuilEntrepriseActivity.this, "Dates invalides ou incohérentes", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Ajouter l'offre à la liste
                    OfferModel offer = new OfferModel(
                            title,
                            domaine,
                            Type.APPLICATION,
                            type,
                            duration,
                            dateDebutParsed,
                            dateFinParsed,
                            new Date(), // Date de création (aujourd'hui)
                            currentComp.getId()
                    );
                    helper.addOffer(offer);
                    offerList.add(offer);
                    offreAdapter.notifyItemInserted(offerList.size() - 1); // Mettre à jour RecyclerView
                    formLayout.setVisibility(View.GONE); // Cacher le formulaire après ajout
                    Toast.makeText(AcceuilEntrepriseActivity.this, "Offre ajoutée avec succès", Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    Toast.makeText(AcceuilEntrepriseActivity.this, "Format de date invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Afficher le PopupMenu
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.menu_entreprise, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil) {
                Intent intent = new Intent(AcceuilEntrepriseActivity.this, ProfilEntrepriseActivity.class);
                intent.putExtra("COMP_ID", currentCompId);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.listeCondidat) {
                Intent intent = new Intent(AcceuilEntrepriseActivity.this, CondidatureActivity.class);
                intent.putExtra("COMP_ID", currentCompId);
                startActivity(intent);
                return true;
            }else if (item.getItemId() == R.id.DéconnexionEntreprise) {
                Intent intent = new Intent(AcceuilEntrepriseActivity.this, MainActivity.class);
                intent.putExtra("COMP_ID", currentCompId);
                startActivity(intent);
                return true;
            }  else {
                Intent intent = new Intent(AcceuilEntrepriseActivity.this, AcceuilEntrepriseActivity.class);
                intent.putExtra("COMP_ID", currentCompId);
                startActivity(intent);
                return true;
            }
        });
        popupMenu.show();
    }

    private void setDateInputListener(EditText editText) {
        editText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
                String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                editText.setText(selectedDate);
            }, year, month, day);
            datePickerDialog.show();
        });
    }
}
