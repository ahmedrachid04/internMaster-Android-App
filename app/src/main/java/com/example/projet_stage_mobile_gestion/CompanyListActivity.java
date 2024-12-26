package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.CompanyModel;

import java.util.ArrayList;
import java.util.List;

public class CompanyListActivity extends AppCompatActivity implements CompanyAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private CompanyAdapter adapter;
    private List<CompanyModel> companyList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu); // Référence au fichier XML du menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_statistiques) {
            // Ouvrir l'activité Statistiques
            startActivity(new Intent(this, StatistiqueActivity.class));
            return true;
        } else if (item.getItemId() == R.id.nav_entreprises) {
            startActivity(new Intent(this, CompanyListActivity.class));
            return true;
        } else if (item.getItemId() == R.id.nav_stagiaires) {
            startActivity(new Intent(this, StudentListActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item); // Ajoutez cette ligne pour gérer d'autres éléments ou retourner false
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialisation de la toolbar
        setSupportActionBar(findViewById(R.id.toolbar));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Liste d'entreprises (données factices)
        companyList = new ArrayList<>();
        companyList.add(new CompanyModel(1, "Entreprise A", "Adresse A", "emailA@example.com", "0123456789", "", "Description A", null, ""));
        companyList.add(new CompanyModel(2, "Entreprise B", "Adresse B", "emailB@example.com", "9876543210", "", "Description B", null, ""));
        companyList.add(new CompanyModel(3, "Entreprise C", "Adresse C", "emailC@example.com", "5555555555", "", "Description C", null, ""));

        adapter = new CompanyAdapter(this, companyList, this);
        recyclerView.setAdapter(adapter);
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            // Lancer l'activité pour créer une nouvelle entreprise
            Intent intent = new Intent(CompanyListActivity.this, InscriptionEntrepriseActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onEditClick(CompanyModel company) {
        // Action de modification (ouvrir l'activité de modification par exemple)
        Intent intent = new Intent(CompanyListActivity.this, InscriptionEntrepriseActivity.class);
        intent.putExtra("company_id", company.getId());  // Passer l'ID de l'entreprise à modifier
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(CompanyModel company) {
        // Action de suppression
        // Exemple : Afficher un message
        Toast.makeText(this, "Suppression de " + company.getName(), Toast.LENGTH_SHORT).show();

        // Vous pouvez également supprimer l'entreprise de la liste et mettre à jour l'UI
        companyList.remove(company);
        adapter.notifyDataSetChanged();
    }
}
