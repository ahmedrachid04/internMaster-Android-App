package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PostulerActivity extends AppCompatActivity {

    private static final int PICK_CV_REQUEST_CODE = 1;
    private static final int PICK_LETTER_REQUEST_CODE = 2;

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextSchool;
    private EditText editTextFiliere;
    private EditText editTextOffer;
    private Button buttonUploadCV;
    private Button buttonUploadLetter;
    private Button buttonSubmit;

    private Uri cvUri;
    private Uri letterUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postuler);

        // Initialiser les composants de l'interface
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextSchool = findViewById(R.id.editTextSchool);
        editTextFiliere = findViewById(R.id.editTextFiliere);
        editTextOffer = findViewById(R.id.editTextOffer);
        buttonUploadCV = findViewById(R.id.buttonUploadCV);
        buttonUploadLetter = findViewById(R.id.buttonUploadLetter);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Gérer l'importation du CV
        buttonUploadCV.setOnClickListener(view -> openFileChooser(PICK_CV_REQUEST_CODE));

        // Gérer l'importation de la lettre d'application
        buttonUploadLetter.setOnClickListener(view -> openFileChooser(PICK_LETTER_REQUEST_CODE));

        // Gérer la soumission du formulaire
        buttonSubmit.setOnClickListener(view -> submitApplication());
    }

    // Ouvrir un sélecteur de fichiers
    private void openFileChooser(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Permet de sélectionner n'importe quel type de fichier
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == PICK_CV_REQUEST_CODE) {
                cvUri = data.getData();
                Toast.makeText(this, "CV sélectionné : " + cvUri.getLastPathSegment(), Toast.LENGTH_SHORT).show();
            } else if (requestCode == PICK_LETTER_REQUEST_CODE) {
                letterUri = data.getData();
                Toast.makeText(this, "Lettre sélectionnée : " + letterUri.getLastPathSegment(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Soumettre la candidature
    private void submitApplication() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String school = editTextSchool.getText().toString().trim();
        String filiere = editTextFiliere.getText().toString().trim();
        String offer = editTextOffer.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || school.isEmpty() || filiere.isEmpty() || offer.isEmpty() || cvUri == null || letterUri == null) {
            Toast.makeText(this, "Veuillez remplir tous les champs obligatoires.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Afficher un message de confirmation (remplacez par l'envoi au backend si nécessaire)
        Toast.makeText(this, "Candidature soumise avec succès !", Toast.LENGTH_SHORT).show();

        // Réinitialiser le formulaire
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextSchool.setText("");
        editTextFiliere.setText("");
        editTextOffer.setText("");
        cvUri = null;
        letterUri = null;
    }
}
