package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editEmail, editNewPassword;
    private Button buttonResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        // Initialisation des vues
        editEmail = findViewById(R.id.editEmail);
        editNewPassword = findViewById(R.id.editNewPassword);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);

        // Action du bouton
        buttonResetPassword.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String newPassword = editNewPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(newPassword)) {
                Toast.makeText(ForgotPasswordActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            } else {
                // Placeholder pour une future logique de réinitialisation
                Toast.makeText(ForgotPasswordActivity.this, "Réinitialisation simulée pour " + email, Toast.LENGTH_SHORT).show();

                // Redirection vers la page d'inscription après réinitialisation
                Intent intent = new Intent(ForgotPasswordActivity.this, ConnexionPageActivity.class);
                startActivity(intent);
                finish(); // Fin de l'activité actuelle pour ne pas revenir à cette page avec le bouton retour
            }
        });
    }
}
