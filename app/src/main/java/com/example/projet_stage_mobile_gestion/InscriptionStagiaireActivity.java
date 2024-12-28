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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InscriptionStagiaireActivity extends AppCompatActivity {

    EditText editTextNom; // Pour la saisie du
    EditText editTextPrenom;
    EditText editTextEcole;
    EditText editTextSpecialite;
    EditText editTextEmail;
    EditText editTextTelephone;
    EditText editTextPassword;
    EditText editTextConfirmerPassword;
    Button editTextProfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscriptionstagiaire);

        editTextNom = findViewById(R.id.editTextFirstN);
        editTextPrenom = findViewById(R.id.editTextLastN);
        editTextEcole = findViewById(R.id.editTextSchooll);
        editTextSpecialite = findViewById(R.id.editTextSpecialtyy);
        editTextEmail = findViewById(R.id.editTextEmailEt);
        editTextTelephone = findViewById(R.id.editTextPhoneEt);
        editTextPassword = findViewById(R.id.editTextPasswordEt);
        editTextConfirmerPassword = findViewById(R.id.editTextConfirmPasswordEt);
        editTextProfil = findViewById(R.id.buttonUploadProfilee);


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

        // Lier le bouton "Continuer" avec son ID
        Button buttonContinuer = findViewById(R.id.buttonSubmit);

        // Ajouter un OnClickListener pour naviguer vers PopupStagiaire
        buttonContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer le texte saisi
                String userTextNom = editTextNom.getText().toString();
                String userTextPrenom = editTextPrenom.getText().toString();
                String userTextEcole = editTextEcole.getText().toString();
                String userTextSpecialite = editTextSpecialite.getText().toString();
                String userTextEmail = editTextEmail.getText().toString();
                String userTextTelephone = editTextTelephone.getText().toString();
                String userTextPassword = editTextPassword.getText().toString();
                String userTextConfirmerPassword = editTextConfirmerPassword.getText().toString();


                // Créer un Intent pour lancer l'Activity PopupEntreprise
                Intent intent = new Intent(InscriptionStagiaireActivity.this, ConfirmerStagiaireActivity.class );

                // Envoyer le texte
                intent.putExtra("TEXT_KEY_Nom", userTextNom);
                intent.putExtra("TEXT_KEY_Prenom", userTextPrenom);
                intent.putExtra("TEXT_KEY_Ecole", userTextEcole);
                intent.putExtra("TEXT_KEY_Specialite", userTextSpecialite);
                intent.putExtra("TEXT_KEY_Email", userTextEmail);
                intent.putExtra("TEXT_KEY_Telephone", userTextTelephone);
                intent.putExtra("TEXT_KEY_Password", userTextPassword);
                intent.putExtra("TEXT_KEY_ConfirmerPassword", userTextConfirmerPassword);
                startActivity(intent);
            }
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