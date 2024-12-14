package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionStagiaireActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscriptionstagiaire);

        TextView firstNameTextView = findViewById(R.id.textViewFirstN);
        TextView lastNameTextView = findViewById(R.id.textViewLastN);
        TextView schoolTextView = findViewById(R.id.textViewSchooll);
        TextView specialtyTextView = findViewById(R.id.textViewSpecialtyy);
        TextView phoneTextView = findViewById(R.id.textViewPhoneEt);
        TextView emailTextView = findViewById(R.id.textViewEmailEt);
        TextView profileTextView = findViewById(R.id.textViewProfilee);
        TextView passwordTextView = findViewById(R.id.textViewPasswordEt);
        TextView confirmPasswordTextView = findViewById(R.id.textViewCPasswordEt);

        firstNameTextView.setText(getColoredText("Nom : ", "", Color.RED));
        lastNameTextView.setText(getColoredText("Prénom : ", "", Color.RED));
        schoolTextView.setText(getColoredText("École : ", "", Color.RED));
        specialtyTextView.setText(getColoredText("Spécialité : ", "", Color.RED));
        phoneTextView.setText(getColoredText("Numéro Téléphone  : ", "", Color.RED));
        emailTextView.setText(getColoredText("Email : ", "", Color.RED));
        profileTextView.setText(getColoredText("Profil : ", "", Color.RED));
        passwordTextView.setText(getColoredText("Mot de passe : ", "", Color.RED));
        confirmPasswordTextView.setText(getColoredText("Confirmer Mot de passe : ", "", Color.RED));

        // Bouton de confirmation
        Button confirmButton = findViewById(R.id.buttonConfirm);
        confirmButton.setOnClickListener(v -> {
            // Intent pour naviguer vers ConfirmerStagiaireActivity
            Intent intent = new Intent(InscriptionStagiaireActivity.this, ConfirmerStagiaireActivity.class);
            startActivity(intent);
        });
    }

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