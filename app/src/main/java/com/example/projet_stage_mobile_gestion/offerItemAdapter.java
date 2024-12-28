package com.example.projet_stage_mobile_gestion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;

import java.util.List;

public class offerItemAdapter extends BaseAdapter {

    private Context context;
    private List<OfferModel> offers;

    public offerItemAdapter(Context context, List<OfferModel> offers) {
        this.context = context;
        this.offers = offers;
    }

    @Override
    public int getCount() {
        return offers.size();
    }

    @Override
    public Object getItem(int position) {
        return offers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.offer_item, parent, false);
        }

        TextView offerTitle = convertView.findViewById(R.id.offer_title);
        Button seeCandidatesButton = convertView.findViewById(R.id.see_candidates);

        // Set the offer title
        OfferModel offer = offers.get(position);
        offerTitle.setText(offer.getTitle());

        // Handle button click
        seeCandidatesButton.setOnClickListener(v -> {
            // Example: Show a toast or start a new activity
            Intent intent = new Intent(context, ListCandidatActivity.class);
            intent.putExtra("OFFER_ID", offer.getId());
            context.startActivity(intent);
        });

        return convertView;
    }
}
