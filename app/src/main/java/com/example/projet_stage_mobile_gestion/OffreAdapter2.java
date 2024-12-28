package com.example.projet_stage_mobile_gestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.SQLiteFiles.InternshipDataBaseHelper;

import java.util.List;

public class OffreAdapter2 extends RecyclerView.Adapter<OffreAdapter2.OffreViewHolder> {

    private List<OfferModel> offerList;
    private Context context;


    public OffreAdapter2(List<OfferModel> offerList, Context context) {
        this.offerList = offerList;
        this.context=context;
    }

    @NonNull
    @Override
    public OffreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each offer item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acceuilentreprise2, parent, false);
        return new OffreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffreViewHolder holder, int position) {
        // Assigner les données du modèle aux vues
        InternshipDataBaseHelper helper=new InternshipDataBaseHelper(context);
        OfferModel offer = offerList.get(position);
        String companyName=helper.getCompaniesById(offer.getCompanyId()).getName();

        holder.nomEntreprise.setText(companyName);  // Afficher le nom de l'entreprise
        holder.domaine.setText(offer.getDomaine());
        holder.type.setText("Type: " + offer.getType().toString());
        holder.duree.setText(offer.getDuration());
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

        TextView nomEntreprise, domaine, type, duree;

        public OffreViewHolder(View itemView) {
            super(itemView);
            nomEntreprise = itemView.findViewById(R.id.nom_entreprise);
            domaine = itemView.findViewById(R.id.textView_domaine);
            type = itemView.findViewById(R.id.textView_type);
            duree = itemView.findViewById(R.id.textView_duration);

        }
    }

}


