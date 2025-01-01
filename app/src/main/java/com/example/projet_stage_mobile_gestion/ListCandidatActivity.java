package com.example.projet_stage_mobile_gestion;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.ApplicationModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Status;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListCandidatActivity extends AppCompatActivity {

    private long offerId, compId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcandidat);

        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(this);

        offerId= getIntent().getLongExtra("OFFER_ID",1);
        compId= getIntent().getLongExtra("COMP_ID",1);
        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView title=findViewById(R.id.subtitle);
        title.setText(helper.getOffersById(offerId).getTitle());

        // Example Data from Backend
        Date currentDate=new Date();
        List<ApplicationModel> candidates = helper.getOffersApplications(offerId);


        // Set Adapter
        CandidatAdapter adapter = new CandidatAdapter(this, candidates);
        recyclerView.setAdapter(adapter);

        ImageButton back=findViewById(R.id.back_button);
        back.setOnClickListener(v -> {
            Intent intent=new Intent(ListCandidatActivity.this,CondidatureActivity.class);
            intent.putExtra("COMP_ID",compId);
        });
    }
}

