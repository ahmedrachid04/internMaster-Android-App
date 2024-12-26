package com.example.projet_stage_mobile_gestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.CompanyModel;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private Context context;
    private List<CompanyModel> companyList;
    private OnItemClickListener listener;

    public CompanyAdapter(Context context, List<CompanyModel> companyList, OnItemClickListener listener) {
        this.context = context;
        this.companyList = companyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the company item
        View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        // Get the company at the current position
        CompanyModel company = companyList.get(position);

        // Set the company data to the views
        holder.tvCompanyName.setText(company.getName());
        holder.tvCompanyAddress.setText(company.getAddresse()); // Assurez-vous que getAddress() existe dans le modèle
        holder.tvContactEmail.setText(company.getContactEmail()); // Assurez-vous que getEmail() existe dans le modèle

        // Clic sur le bouton Modifier
        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(company));

        // Clic sur le bouton Supprimer
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(company));
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCompanyName, tvCompanyAddress, tvContactEmail;
        ImageButton btnEdit, btnDelete;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            // Find views by their IDs
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvCompanyAddress = itemView.findViewById(R.id.tvCompanyAddress); // Assurez-vous que ce champ est dans item_company.xml
            tvContactEmail = itemView.findViewById(R.id.tvContactEmail); // Assurez-vous que ce champ est dans item_company.xml
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    // Interface pour gérer les clics sur les boutons
    public interface OnItemClickListener {
        void onEditClick(CompanyModel company);
        void onDeleteClick(CompanyModel company);
    }
}
