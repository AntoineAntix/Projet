package com.example.projet.Vu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projet.Modele.Classes.Spells;
import com.example.projet.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AttaqueClasseAdapter extends RecyclerView.Adapter<AttaqueClasseAdapter.ViewHolder>{
    private List<Spells> listSpells;
    private Context context;

    public AttaqueClasseAdapter(List<Spells> dataBase, Context context) {
        this.listSpells =dataBase;
        this.context=context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView variant;

        public ViewHolder(View vu) {
            super(vu);
            name = (TextView) vu.findViewById(R.id.cell_txt_attaque_name);
            variant = (TextView) vu.findViewById(R.id.cell_txt_attaque_variant);
        }
    }

    @Override
    public AttaqueClasseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.row_attaque_classe,parent,false);
        AttaqueClasseAdapter.ViewHolder vuH = new AttaqueClasseAdapter.ViewHolder(vu);
        return vuH;
    }

    @Override
    public void onBindViewHolder(@NonNull AttaqueClasseAdapter.ViewHolder holder, int position) {
        final Spells spellsAcutel = listSpells.get(position);
        final String name= spellsAcutel.getName();
        final String variant= spellsAcutel.getVariant();

        holder.name.setText(name);
        holder.variant.setText(variant);
    }

    @Override
    public int getItemCount() {
        return listSpells.size();
    }
}


