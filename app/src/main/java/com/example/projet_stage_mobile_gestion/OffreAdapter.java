package com.example.projet_stage_mobile_gestion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

import java.util.List;

public class OffreAdapter extends RecyclerView.Adapter<OffreAdapter.OffreViewHolder> {

    private List<OfferModel> offerList;
    private Context context;
    private long currentStudentId;

    public OffreAdapter(List<OfferModel> offerList, Context context, long id) {
        this.offerList = offerList;
        this.context=context;
        this.currentStudentId=id;
    }

    @NonNull
    @Override
    public OffreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each offer item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acceuilstagiaire2, parent, false);
        return new OffreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffreViewHolder holder, int position) {
        // Assigner les données du modèle aux vues
        OfferModel offer = offerList.get(position);
        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(this.context);
        String compName=helper.getCompaniesById(offer.getCompanyId()).getName();

        holder.nomEntreprise.setText(compName);  // Afficher le nom de l'entreprise
        holder.domaine.setText(offer.getDomaine());
        holder.type.setText("Type: " + offer.getType().toString());
        holder.duree.setText(offer.getDuration());
        holder.details.setOnClickListener(v -> {
            Intent intent = new Intent(context, OfferDetailsActivity.class);
            intent.putExtra("OFF_ID", offer.getId());
            intent.putExtra("STUD_ID",currentStudentId);
            context.startActivity(intent);
        });
    }

    /*public void onBindViewHolder(@NonNull OffreViewHolder holder, int position) {
        // Assigner les données du modèle aux vues
        OfferModel offer = offerList.get(position);

        holder.nomEntreprise.setText(offer.getTitle());
        holder.domaine.setText(offer.getDomaine());
        holder.type.setText("Type :" +offer.getType().toString()); // Assurer que Type est correctement converti en String
        holder.duree.setText(offer.getDuration());
    }*/

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    // ViewHolder pour chaque élément de la liste
    public static class OffreViewHolder extends RecyclerView.ViewHolder {

        TextView nomEntreprise, domaine, type, duree, details;

        public OffreViewHolder(View itemView) {
            super(itemView);
            nomEntreprise = itemView.findViewById(R.id.nom_entreprise);
            domaine = itemView.findViewById(R.id.textView_domaine);
            type = itemView.findViewById(R.id.textView_type);
            duree = itemView.findViewById(R.id.textView_duration);
            details=itemView.findViewById(R.id.textView_moreDetails);

        }
    }

}

