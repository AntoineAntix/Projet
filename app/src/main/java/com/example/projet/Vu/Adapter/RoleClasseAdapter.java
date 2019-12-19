package com.example.projet.Vu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projet.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Cet Adapter gère l'affichage du recyclerview des roles de chaque classe.
 */

public class RoleClasseAdapter extends RecyclerView.Adapter<RoleClasseAdapter.ViewHolder>{

    private List<String> listroles;
    private Context context;

    //Fonction qui permet l'appelle de la classe java
    public RoleClasseAdapter(List<String> dataBase, Context context) {
        listroles=dataBase;
        this.context=context;
    }

    //On définit ce qu'il y a à afficher dans le recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView role;

        //On définit les balises correspondant à ce qu'on veut afficher
        public ViewHolder(View vu) {
            super(vu);
            role = (TextView) vu.findViewById(R.id.cell_txt_classes_role);
        }
    }

    //On définit le layout correspondant à l'affichage des items
    @Override
    public RoleClasseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.row_role_classe,parent,false);
        RoleClasseAdapter.ViewHolder vuH = new RoleClasseAdapter.ViewHolder(vu);
        return vuH;
    }

    //On récupère les données de l'objet
    @Override
    public void onBindViewHolder(@NonNull RoleClasseAdapter.ViewHolder holder, int position) {
        final String roleActuel = listroles.get(position);
        holder.role.setText(roleActuel);
    }

    @Override
    public int getItemCount() {
        return listroles.size();
    }
}
