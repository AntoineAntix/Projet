package com.example.projet;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet.OnItemClick;
import com.squareup.picasso.Picasso;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private final OnItemClick click;
    private List<Weapons> weapon;


    public Adapter(List<Weapons> dataBase, OnItemClick click)
    {
        weapon=dataBase;
        this.click=click;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nomTxt;
        public TextView idTxt;
        public View layout;

        public ViewHolder(View vu)
        {
            super(vu);
            layout = vu;
            nomTxt = (TextView) vu.findViewById(R.id.cell_txt_weapons_name);
            idTxt = (TextView) vu.findViewById(R.id.cell_txt_weapons_id);
        }
    }

    public void ajouter(int position, Weapons arme)
    {
        weapon.add(position,arme);
        notifyItemInserted(position);
    }

    public void supprimer(int position)
    {
        weapon.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View vu=inflater.inflate(R.layout.row_layout,parent,false);
       ViewHolder vuH = new ViewHolder(vu);
       return vuH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Weapons weaponActuel = weapon.get(position);
        final String name=weaponActuel.getName();
        final String id=weaponActuel.get_id();

        holder.nomTxt.setText(name);
        holder.idTxt.setText(id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(weaponActuel);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return weapon.size();
    }
}
