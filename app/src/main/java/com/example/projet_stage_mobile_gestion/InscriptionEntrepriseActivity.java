package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class InscriptionEntrepriseActivity extends AppCompatActivity {
    EditText editTextNom;
    EditText editTextAdresse;
    EditText editTextEmail;
    EditText editTextTelephone;
    EditText editTextPassword;
    EditText editTextConfirmerPassword;
    Button editTextLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscriptionentreprise);

        editTextNom = findViewById(R.id.editTextCompanyName);
        editTextAdresse = findViewById(R.id.editTextAddress);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelephone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmerPassword = findViewById(R.id.editTextConfirmPassword);
        editTextLogo = findViewById(R.id.buttonUploadLogo);



        // TextView avec des étoiles
        TextView companyTextView = findViewById(R.id.textViewCompany);
        companyTextView.setText(getColoredText("Nom d'entreprise: ", "", Color.RED));

        TextView emailTextView = findViewById(R.id.textViewEmail);
        emailTextView.setText(getColoredText("Email: ", "", Color.RED));

        TextView phoneTextView = findViewById(R.id.textViewPhone);
        phoneTextView.setText(getColoredText("Numéro Téléphone (fax): ", "", Color.RED));

        TextView addressTextView = findViewById(R.id.textViewAddress);
        addressTextView.setText(getColoredText("Adresse: ", "", Color.RED));

        TextView logoTextView = findViewById(R.id.textViewLogo);
        logoTextView.setText(getColoredText("Logo: ", "", Color.RED));

        TextView passwordTextView = findViewById(R.id.textViewPassword);
        passwordTextView.setText(getColoredText("Mot de passe: ", "", Color.RED));

        TextView confirmPasswordTextView = findViewById(R.id.textViewConfirmPassword);
        confirmPasswordTextView.setText(getColoredText("Confirmer Mot de passe: ", "", Color.RED));

        // Lier le bouton "Continuer" avec son ID
        Button buttonContinuer = findViewById(R.id.buttonSubmit);

        // Ajouter un OnClickListener pour naviguer vers PopupEntreprise
        buttonContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer le texte saisi
                String userTextNom = editTextNom.getText().toString();
                String userTextEmail = editTextEmail.getText().toString();
                String userTextTelephone = editTextTelephone.getText().toString();
                String userTextAdresse = editTextAdresse.getText().toString();
                String userTextPassword = editTextPassword.getText().toString();
                String userTextConfirmerPassword = editTextConfirmerPassword.getText().toString();

                // Créer un Intent pour lancer l'Activity PopupEntreprise
                Intent intent = new Intent(InscriptionEntrepriseActivity.this, ConfirmerEntrepriseActivity.class);

                // Envoyer le texte
                intent.putExtra("TEXT_KEY_Nom", userTextNom);
                intent.putExtra("TEXT_KEY_Email", userTextEmail);
                intent.putExtra("TEXT_KEY_Telephone", userTextTelephone);
                intent.putExtra("TEXT_KEY_Adresse", userTextAdresse);
                intent.putExtra("TEXT_KEY_Password", userTextPassword);
                intent.putExtra("TEXT_KEY_ConfirmerPassword", userTextConfirmerPassword);

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