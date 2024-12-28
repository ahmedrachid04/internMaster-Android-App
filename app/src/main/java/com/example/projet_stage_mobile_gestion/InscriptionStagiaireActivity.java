package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    private static final int PICK_FILE_REQUEST = 1;  // Code pour identifier la sélection de fichier

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

        // Ajout de l'écouteur de clic pour le bouton "Importer un fichier"
        editTextProfil.setOnClickListener(v -> {
            // Ouvrir un sélecteur de fichiers
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");  // Vous pouvez restreindre le type de fichier, par exemple "application/pdf" ou "image/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, PICK_FILE_REQUEST);
        });

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

        // Ajouter un OnClickListener pour naviguer vers ConfirmerStagiaireActivity
        buttonContinuer.setOnClickListener(v -> {
            // Récupérer le texte saisi
            String userTextNom = editTextNom.getText().toString();
            String userTextPrenom = editTextPrenom.getText().toString();
            String userTextEcole = editTextEcole.getText().toString();
            String userTextSpecialite = editTextSpecialite.getText().toString();
            String userTextEmail = editTextEmail.getText().toString();
            String userTextTelephone = editTextTelephone.getText().toString();
            String userTextPassword = editTextPassword.getText().toString();
            String userTextConfirmerPassword = editTextConfirmerPassword.getText().toString();
            if (userTextEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(userTextEmail).matches()) {
                editTextEmail.setError("Invalid email format");
            }
            if (userTextTelephone.isEmpty() || !userTextTelephone.matches("^[0-9]{10}$")) {
                editTextTelephone.setError("Invalid phone number format");
            }
            if (!userTextConfirmerPassword.matches(userTextPassword)){editTextConfirmerPassword.setError("Passwords do not match");}
            if(userTextEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(userTextEmail).matches() || userTextTelephone.isEmpty() || !userTextTelephone.matches("^[0-9]{10}$") || !userTextConfirmerPassword.matches(userTextPassword) || userTextConfirmerPassword.isEmpty() || userTextPassword.isEmpty() ){
                Toast.makeText(InscriptionStagiaireActivity.this, "Veuillez remplir too les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Créer un Intent pour lancer l'Activity ConfirmerStagiaireActivity
            Intent intent = new Intent(InscriptionStagiaireActivity.this, ConfirmerStagiaireActivity.class);

            // Envoyer les données
            intent.putExtra("TEXT_KEY_Nom", userTextNom);
            intent.putExtra("TEXT_KEY_Prenom", userTextPrenom);
            intent.putExtra("TEXT_KEY_Ecole", userTextEcole);
            intent.putExtra("TEXT_KEY_Specialite", userTextSpecialite);
            intent.putExtra("TEXT_KEY_Email", userTextEmail);
            intent.putExtra("TEXT_KEY_Telephone", userTextTelephone);
            intent.putExtra("TEXT_KEY_Password", userTextPassword);
            intent.putExtra("TEXT_KEY_ConfirmerPassword", userTextConfirmerPassword);
            startActivity(intent);
        });
    }

    // Méthode pour gérer l'activation de l'intent de fichier
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_FILE_REQUEST) {
            if (data != null) {
                Uri fileUri = data.getData();
                // Afficher un Toast pour indiquer le chemin du fichier sélectionné
                Toast.makeText(this, "Fichier sélectionné : " + fileUri.getPath(), Toast.LENGTH_LONG).show();
            }
        }
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