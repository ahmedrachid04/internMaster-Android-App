package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.projet_stage_mobile_gestion.DataBase.Models.CompanyModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CondidatureActivity extends AppCompatActivity {

    private ListView offersListView;
    long currentCompId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.condidature);
        currentCompId = getIntent().getLongExtra("COMP_ID",1);
        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(this);


        offersListView = findViewById(R.id.offers_list);

        // Exemple de données d'offre
        List<OfferModel> offers = helper.getCompnysOffers(currentCompId);
        List<String> offerTitles=new ArrayList<>();
        for(int i=0;i<offers.size();i++){
            String title=offers.get(i).getTitle();
            offerTitles.add(title);
        }

        // Adapter personnalisé
        offerItemAdapter adapter = new offerItemAdapter(this, offers);
        offersListView.setAdapter(adapter);

// Initialisation du bouton Menu
        ImageView menuButton = findViewById(R.id.menu_icon);
        menuButton.setOnClickListener(v -> showPopupMenu(v));
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        // Infler le menu à partir d'un fichier de menu XML
        getMenuInflater().inflate(R.menu.menu_entreprise, popupMenu.getMenu());

        // Gestion des éléments du menu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil) {
                Intent intent=new Intent(CondidatureActivity.this, ProfilEntrepriseActivity.class);
                intent.putExtra("COMP_ID",currentCompId);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.listeCondidat) {
                Intent intent=new Intent(CondidatureActivity.this, CondidatureActivity.class);
                intent.putExtra("COMP_ID",currentCompId);
                startActivity(intent);
                return true;
            } else {
                Intent intent=new Intent(CondidatureActivity.this, AcceuilEntrepriseActivity.class);
                intent.putExtra("COMP_ID",currentCompId);
                startActivity(intent);
                return true;
            }
        });
        popupMenu.show();
    }
}
