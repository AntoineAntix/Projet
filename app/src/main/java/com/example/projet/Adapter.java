package com.example.projet;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private List<Weapons> weapon;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nomTxt;
        public View layout;

        public ViewHolder(View vu)
        {
            super(vu);
            layout = vu;
            nomTxt = (TextView) vu.findViewById(R.id.cell_txt_weapons_name);
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

    public Adapter(List<Weapons> dataBase)
    {
        weapon=dataBase;
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
        holder.nomTxt.setText(name);
    }

    @Override
    public int getItemCount()
    {
        return weapon.size();
    }
}
