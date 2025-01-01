package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.projet_stage_mobile_gestion.DataBase.Models.CompanyModel;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

public class ProfilEntrepriseActivity extends AppCompatActivity {

    TextView textViewNom;
    TextView textViewLocal;
    TextView textViewNumero;

    long currentCompId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profilentreprise);

        currentCompId = getIntent().getLongExtra("COMP_ID",1);
        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(this);
        CompanyModel currentCompany=helper.getCompaniesById(currentCompId);

        // Initialisation du TextView
        textViewNom = findViewById(R.id.title);
        textViewLocal=findViewById(R.id.local);
        textViewNumero=findViewById(R.id.tel);

        // Récupérer le texte depuis l'Intent
        String receivedTextNom = getIntent().getStringExtra("TEXT_KEY_Nom");

        // Afficher le texte dans le TextView
        textViewNom.setText(currentCompany.getName());
        textViewLocal.setText(currentCompany.getAddresse());
        textViewNumero.setText(currentCompany.getContactNumber());

        // Initialisation du bouton Menu
        ImageView menuButton = findViewById(R.id.imageView_menu);
        menuButton.setOnClickListener(v -> showPopupMenu(v));  // Afficher le menu lors du clic sur le bouton
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        // Infler le menu à partir d'un fichier de menu XML
        getMenuInflater().inflate(R.menu.menu_entreprise, popupMenu.getMenu());

        // Gestion des éléments du menu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil) {
                Intent intent=new Intent(ProfilEntrepriseActivity.this, ProfilEntrepriseActivity.class);
                intent.putExtra("COMP_ID",currentCompId);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.listeCondidat) {
                Intent intent=new Intent(ProfilEntrepriseActivity.this, CondidatureActivity.class);
                intent.putExtra("COMP_ID",currentCompId);
                startActivity(intent);
                return true;
            }
            else if (item.getItemId() == R.id.DéconnexionEntreprise) {
                Intent intent=new Intent(ProfilEntrepriseActivity.this, MainActivity.class);
                intent.putExtra("COMP_ID",currentCompId);
                startActivity(intent);
                return true;
            }else {
                Intent intent=new Intent(ProfilEntrepriseActivity.this, AcceuilEntrepriseActivity.class);
                intent.putExtra("COMP_ID",currentCompId);
                startActivity(intent);
                return true;
            }
        });
        popupMenu.show();
    }
}