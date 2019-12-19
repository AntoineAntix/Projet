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

import com.example.projet.Modele.Equipments.EquipmentsOnItemClick;
import com.example.projet.Modele.Equipments.Equipments;
import com.example.projet.R;
import com.squareup.picasso.Picasso;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Cet Adapter gère l'affichage du recyclerview des Equipments.
 */

public class EquipmentsAdapter extends RecyclerView.Adapter<EquipmentsAdapter.ViewHolder> implements Filterable
{
    private final EquipmentsOnItemClick click;
    private List<Equipments> equipmentsList;
    private Context context;
    private List<Equipments> equipmentsListFull;

    //Fonction qui permet l'appelle de la classe java
    public EquipmentsAdapter(List<Equipments> dataBase, Context context, EquipmentsOnItemClick click)
    {
        equipmentsList =dataBase;
        this.click=click;
        this.context=context;
        equipmentsListFull =new ArrayList<>(equipmentsList);
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
    public EquipmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View vu=inflater.inflate(R.layout.row_layout_equipments,parent,false);
       ViewHolder vuH = new ViewHolder(vu);
       return vuH;
    }

    //On récupère les données de l'objet
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Equipments equipmentActuel = equipmentsList.get(position);
        final String name=equipmentActuel.getName();
        final String id=equipmentActuel.get_id();

        holder.nomTxt.setText(name);
        holder.idTxt.setText(id);

        Picasso.with(context)
                .load(equipmentActuel.getImgUrl())
                .into(holder.imageVw);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(equipmentActuel);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return equipmentsList.size();
    }

    //Fonction qui permet de gérer le filtrage de la recyclerview
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Equipments> ListFilter = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                ListFilter.addAll(equipmentsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Equipments equipments : equipmentsListFull) {
                    if (equipments.getName().toLowerCase().contains(filterPattern)) {
                        ListFilter.add(equipments);
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
            equipmentsList.clear();
            equipmentsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
