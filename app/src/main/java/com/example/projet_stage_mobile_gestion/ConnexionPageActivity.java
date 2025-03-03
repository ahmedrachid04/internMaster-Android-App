package com.example.projet_stage_mobile_gestion;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

public class ConnexionPageActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnConnexion;
    private TextView forgotPasswordText;
    private ImageView homeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexionpage);

        // Initialisation des éléments de l'interface
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnConnexion = findViewById(R.id.btnConnexion);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        homeIcon = findViewById(R.id.homeIcon);
        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(this);


        // Action pour l'icône "Home"
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers la page MainActivity
                Intent intent = new Intent(ConnexionPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Ferme cette activité pour éviter de revenir à celle-ci
            }
        });

        // Action pour le bouton "Connexion"
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                // Validation des champs
                if(email.matches("admin@mail.com")&&password.matches("admin"))startActivity(new Intent(ConnexionPageActivity.this, StatistiqueActivity.class));
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ConnexionPageActivity.this, "Veuillez entrer votre adresse email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ConnexionPageActivity.this, "Veuillez entrer votre mot de passe", Toast.LENGTH_SHORT).show();
                } else {
                    // Simule une connexion réussie
                    if (helper.getStudentsByEmailndPassword(email,password)!=null) {
                        Toast.makeText(ConnexionPageActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ConnexionPageActivity.this, AcceuilStagiaireActivity.class);
                        intent.putExtra("STUD_ID", helper.getStudentsByEmailndPassword(email,password).getId());
                        startActivity(intent);
                        // Exemple : rediriger vers le tableau de bord ou une autre activité
                        // Intent intent = new Intent(ConnexionPageActivity.this, DashboardActivity.class);
                        // startActivity(intent);
                        finish();
                    } else {
                        if(helper.getCompanyByEmailndPassword(email,password)!=null){
                            Toast.makeText(ConnexionPageActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ConnexionPageActivity.this, AcceuilEntrepriseActivity.class);
                            intent.putExtra("COMP_ID", helper.getCompanyByEmailndPassword(email,password).getId());
                            startActivity(intent);
                            finish();
                        }
                        Toast.makeText(ConnexionPageActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Action pour le texte "Mot de passe oublié"
        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers la page ForgotPassPageActivity
                Intent intent = new Intent(ConnexionPageActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
