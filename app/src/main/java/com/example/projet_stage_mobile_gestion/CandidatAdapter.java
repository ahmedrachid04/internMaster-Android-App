package com.example.projet_stage_mobile_gestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.ApplicationModel;

import java.util.List;

public class CandidatAdapter extends RecyclerView.Adapter<CandidatAdapter.ViewHolder> {

    private List<ApplicationModel> applications;
    private Context context;

    public CandidatAdapter(Context context, List<ApplicationModel> candidates) {
        this.context = context;
        this.applications = candidates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApplicationModel application = applications.get(position);
        String candidatName="placeholder names";
        holder.name.setText(candidatName);
        holder.viewCV.setOnClickListener(v -> {
            // Handle CV click (e.g., open URL or show details)
        });
        holder.viewRequest.setOnClickListener(v -> {
            // Handle Request click (e.g., open details)
        });
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, viewCV, viewRequest;
        ImageView profileIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            viewCV = itemView.findViewById(R.id.view_cv);
            viewRequest = itemView.findViewById(R.id.view_request);
            profileIcon = itemView.findViewById(R.id.profile_icon);
        }
    }
}

