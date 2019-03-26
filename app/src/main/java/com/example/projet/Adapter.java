package com.example.projet;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import android.text.TextUtils;
import android.app.Activity;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable
{
    private final OnItemClick click;
    private List<Weapons> weapon;
    private Context context;
    private List<Weapons> weaponFull;


    public Adapter(List<Weapons> dataBase, Context context, OnItemClick click)
    {
        weapon=dataBase;
        this.click=click;
        this.context=context;
        weaponFull=new ArrayList<>(weapon);
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nomTxt;
        public TextView idTxt;
        public View layout;
        public ImageView imageVw;

        public ViewHolder(View vu)
        {
            super(vu);
            layout = vu;
            nomTxt = (TextView) vu.findViewById(R.id.cell_txt_weapons_name);
            idTxt = (TextView) vu.findViewById(R.id.cell_txt_weapons_id);
            imageVw = (ImageView) vu.findViewById(R.id.cell_txt_weapons_image);
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

        Picasso.with(context)
                .load(weaponActuel.getImgUrl())
                .into(holder.imageVw);

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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Weapons> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(weaponFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Weapons item : weaponFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            weapon.clear();
            weapon.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
