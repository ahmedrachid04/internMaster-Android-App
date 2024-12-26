package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StatistiqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistique);

        // Initialisation du toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Charge le menu
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_statistiques) {
            Intent intent = new Intent(StatistiqueActivity.this, StatistiqueActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_entreprises) {
            Intent intent = new Intent(StatistiqueActivity.this, CompanyListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_stagiaires) {
            // Action pour "Liste des Stagiaires"
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

