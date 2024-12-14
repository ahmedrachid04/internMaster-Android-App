package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class InscriptionEntrepriseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscriptionentreprise);

        // TextView avec des étoiles
        TextView companyTextView = findViewById(R.id.textViewCompany);
        companyTextView.setText(getColoredText("Nom d'entreprise: *", "*", Color.RED));

        TextView emailTextView = findViewById(R.id.textViewEmail);
        emailTextView.setText(getColoredText("Email: *", "*", Color.RED));

        TextView phoneTextView = findViewById(R.id.textViewPhone);
        phoneTextView.setText(getColoredText("Numéro Téléphone (fax): *", "*", Color.RED));

        TextView addressTextView = findViewById(R.id.textViewAddress);
        addressTextView.setText(getColoredText("Adresse: *", "*", Color.RED));

        TextView logoTextView = findViewById(R.id.textViewLogo);
        logoTextView.setText(getColoredText("Logo: *", "*", Color.RED));

        TextView passwordTextView = findViewById(R.id.textViewPassword);
        passwordTextView.setText(getColoredText("Mot de passe: *", "*", Color.RED));

        TextView confirmPasswordTextView = findViewById(R.id.textViewConfirmPassword);
        confirmPasswordTextView.setText(getColoredText("Confirmer Mot de passe: *", "*", Color.RED));

        // Lier le bouton "Continuer" avec son ID
        Button buttonContinuer = findViewById(R.id.buttonSubmit);

        // Ajouter un OnClickListener pour naviguer vers PopupEntreprise
        buttonContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un Intent pour lancer l'Activity PopupEntreprise
                Intent intent = new Intent(InscriptionEntrepriseActivity.this, ConfirmerEntrepriseActivity.class);
                startActivity(intent);
            }
        });
    }

    // Méthode pour appliquer une couleur à une partie spécifique du texte
    private SpannableString getColoredText(String fullText, String target, int color) {
        SpannableString spannableString = new SpannableString(fullText);
        int start = fullText.indexOf(target);
        int end = start + target.length();
        if (start >= 0) {
            spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }
}
