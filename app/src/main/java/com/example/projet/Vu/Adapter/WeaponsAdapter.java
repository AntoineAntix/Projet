package com.example.projet.Vu.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet.Modele.Weapons.WeaponsOnItemClick;
import com.example.projet.Modele.Weapons.Weapons;
import com.example.projet.R;
import com.squareup.picasso.Picasso;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Cet Adapter gère l'affichage du recyclerview des Weapons.
 */

public class WeaponsAdapter extends RecyclerView.Adapter<WeaponsAdapter.ViewHolder> implements Filterable
{
    private final WeaponsOnItemClick click;
    private List<Weapons> weapon;
    private Context context;
    private List<Weapons> weaponFull;

    //Fonction qui permet l'appelle de la classe java
    public WeaponsAdapter(List<Weapons> dataBase, Context context, WeaponsOnItemClick click)
    {
        weapon=dataBase;
        this.click=click;
        this.context=context;
        weaponFull=new ArrayList<>(weapon);
    }

    //On définit ce qu'il y a à afficher dans le recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nomTxt;
        public TextView idTxt;
        public View layout;
        public ImageView imageVw;

        //On définit les balises correspondant à ce qu'on veut afficher
        public ViewHolder(View vu)
        {
            super(vu);
            layout = vu;
            nomTxt = (TextView) vu.findViewById(R.id.cell_txt_weapons_name);
            idTxt = (TextView) vu.findViewById(R.id.cell_txt_weapons_id);
            imageVw = (ImageView) vu.findViewById(R.id.cell_txt_weapons_image);
        }
    }


    //On définit le layout correspondant à l'affichage des items
    @Override
    public WeaponsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.row_layout_weapons,parent,false);
        ViewHolder vuH = new ViewHolder(vu);
        return vuH;
    }

    //On récupère les données de l'objet
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

    //Fonction qui permet de gérer le filtrage de la recyclerview
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Weapons> ListFilter = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                ListFilter.addAll(weaponFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Weapons arme : weaponFull) {
                    if (arme.getName().toLowerCase().contains(filterPattern)) {
                        ListFilter.add(arme);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = ListFilter;
            return results;
        }

        //On affiche le résultat du filtre
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            weapon.clear();
            weapon.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}