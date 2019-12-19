package com.example.projet.Vu.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projet.Modele.Classes.Classe;
import com.example.projet.Modele.Classes.ClassesOnItemClick;
import com.example.projet.R;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Cet Adapter gère l'affichage du recyclerview des classes.
 */

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> implements Filterable{

    private final ClassesOnItemClick click;
    private List<Classe> classes;
    private Context context;
    private List<Classe> classesFull;

    //Fonction qui permet l'appelle de la classe java
    public ClassesAdapter(List<Classe> dataBase, Context context, ClassesOnItemClick click) {
        classes=dataBase;
        this.click=click;
        this.context=context;
        classesFull=new ArrayList<>(classes);
    }

    //On définit ce qu'il y a à afficher dans le recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nomClasse;
        public ImageView imgClasse;
        public View layoutClasse;

        //On définit les balises correspondant à ce qu'on veut afficher
        public ViewHolder(View vu) {
            super(vu);
            layoutClasse = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            nomClasse = (TextView) vu.findViewById(R.id.cell_txt_classes_name);
            imgClasse = (ImageView) vu.findViewById(R.id.imageView_Image);
        }
    }

    //On définit le layout correspondant à l'affichage des items
    @Override
    public ClassesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.row_layout_classes,parent,false);
        ViewHolder vuH = new ViewHolder(vu);
        return vuH;
    }

    //On récupère les données de l'objet
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Classe classeActuel = classes.get(position);
        final String name=classeActuel.getName();
        final String photo=classeActuel.getMaleImg();

        holder.nomClasse.setText(name);

        Picasso.with(context)
                .load(photo)
                .into(holder.imgClasse);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(classeActuel);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return classes.size();
    }

    //Fonction qui permet de gérer le filtrage de la recyclerview
    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Classe> ListFilter = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                ListFilter.addAll(classesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Classe classe : classesFull) {
                    if (classe.getName().toLowerCase().contains(filterPattern)) {
                        ListFilter.add(classe);
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
            classes.clear();
            classes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
