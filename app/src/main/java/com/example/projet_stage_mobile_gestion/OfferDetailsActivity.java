package com.example.projet_stage_mobile_gestion;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;

import java.text.SimpleDateFormat;

public class OfferDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView typeTextView;
    private TextView domaineTextView;
    private TextView durationTextView;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private TextView postDateTextView;
    private TextView companyNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offerdetails);

        // Initialiser les TextViews
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        typeTextView = findViewById(R.id.typeTextView);
        domaineTextView = findViewById(R.id.domaineTextView);
        durationTextView = findViewById(R.id.durationTextView);
        startDateTextView = findViewById(R.id.startDateTextView);
        endDateTextView = findViewById(R.id.endDateTextView);
        postDateTextView = findViewById(R.id.postDateTextView);
        companyNameTextView = findViewById(R.id.companyNameTextView);

        // Récupérer les données de l'offre (supposons que l'offre soit passée en extra)
        OfferModel offer = (OfferModel) getIntent().getSerializableExtra("offer");

        if (offer != null) {
            // Afficher les informations dans les TextViews
            titleTextView.setText(offer.getTitle());
            descriptionTextView.setText(offer.getDescription());
            typeTextView.setText(offer.getType().toString());
            domaineTextView.setText(offer.getDomaine());
            durationTextView.setText(offer.getDuration());

            // Format des dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            startDateTextView.setText(dateFormat.format(offer.getStartDate()));
            endDateTextView.setText(dateFormat.format(offer.getEndDate()));
            postDateTextView.setText(dateFormat.format(offer.getPostDate()));

            companyNameTextView.setText(offer.getCompanyName());
        }
    }
}

