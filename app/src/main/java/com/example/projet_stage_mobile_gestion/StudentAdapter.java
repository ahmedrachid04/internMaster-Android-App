package com.example.projet_stage_mobile_gestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projet_stage_mobile_gestion.DataBase.Models.StudentModel;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<StudentModel> studentList;
    private OnItemClickListener listener; // Correct: Utilise une interface propre à cet adaptateur

    public StudentAdapter(Context context, List<StudentModel> studentList, OnItemClickListener listener) {
        this.context = context;
        this.studentList = studentList;
        this.listener = listener; // Corrige l'initialisation du listener
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentModel student = studentList.get(position);
        holder.nameTextView.setText(student.getFirstName() + " " + student.getLastName());
        holder.specialtyTextView.setText(student.getSpecialty());
        holder.schoolTextView.setText(student.getSchool());

        // Clic sur le bouton Modifier
        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(student));

        // Clic sur le bouton Supprimer
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(student));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    // Classe interne pour le ViewHolder
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, specialtyTextView, schoolTextView;
        ImageButton btnEdit, btnDelete;

        public StudentViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.studentName);
            specialtyTextView = itemView.findViewById(R.id.studentSpecialty);
            schoolTextView = itemView.findViewById(R.id.studentSchool);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    // Interface pour gérer les clics sur les boutons
    public interface OnItemClickListener {
        void onEditClick(StudentModel student); // Corrigé pour utiliser StudentModel
        void onDeleteClick(StudentModel student); // Corrigé pour utiliser StudentModel
    }
}
