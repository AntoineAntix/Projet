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

public class WeaponsAdapter extends RecyclerView.Adapter<WeaponsAdapter.ViewHolder> implements Filterable
{
    private final WeaponsOnItemClick click;
    private List<Weapons> weapon;
    private Context context;
    private List<Weapons> weaponFull;


    public WeaponsAdapter(List<Weapons> dataBase, Context context, WeaponsOnItemClick click)
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
    public WeaponsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.row_layout_weapons,parent,false);
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

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            weapon.clear();
            weapon.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}