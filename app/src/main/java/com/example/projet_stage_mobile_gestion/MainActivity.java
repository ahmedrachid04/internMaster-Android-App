package com.example.projet_stage_mobile_gestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnConnection = findViewById(R.id.btnConnection);
        Button btnInscription = findViewById(R.id.btnInscription);

        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConnexionPageActivity.class);
                startActivity(intent);
            }
        });

        // Action pour le bouton "Inscription"
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InscriptionPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
