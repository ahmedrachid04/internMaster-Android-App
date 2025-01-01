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
import com.example.projet_stage_mobile_gestion.DataBase.Models.StudentModel;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity implements StudentAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private List<StudentModel> studentList;

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
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(this);

        // Initialisation de la toolbar
        setSupportActionBar(findViewById(R.id.toolbar));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Liste d'entreprises (données factices)
        studentList = helper.getAllStudents();

        adapter = new StudentAdapter(this, studentList, this);

        recyclerView.setAdapter(adapter);
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            // Lancer l'activité pour créer une nouvelle entreprise
            Intent intent = new Intent(StudentListActivity.this, InscriptionStagiaireActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onEditClick(StudentModel company) {
        // Action de modification (ouvrir l'activité de modification par exemple)
        Intent intent = new Intent(StudentListActivity.this, InscriptionStagiaireActivity.class);
        intent.putExtra("company_id", company.getId());  // Passer l'ID de l'entreprise à modifier
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(StudentModel student) {
        // Action de suppression
        // Exemple : Afficher un message
        Toast.makeText(this, "Suppression de " + student.getFirstName() + " " + student.getLastName(), Toast.LENGTH_SHORT).show();

        // Vous pouvez également supprimer l'entreprise de la liste et mettre à jour l'UI
        studentList.remove(student);
        adapter.notifyDataSetChanged();
    }
}
