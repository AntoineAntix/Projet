package com.example.projet.Vu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.projet.Modele.Classe;
import com.example.projet.Modele.ClassesOnItemClick;
import com.example.projet.R;

import java.util.ArrayList;
import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> implements Filterable{

    private final ClassesOnItemClick click;
    private List<Classe> classes;
    private Context contextClasses;
    private List<Classe> classesFull;

    public ClassesAdapter(List<Classe> dataBase, Context context, ClassesOnItemClick click) {
        classes=dataBase;
        this.click=click;
        this.contextClasses=context;
        classesFull=new ArrayList<>(classes);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nomClasse;
        public View layoutClasse;

        public ViewHolder(View vu) {
            super(vu);
            layoutClasse = vu;
            nomClasse = (TextView) vu.findViewById(R.id.cell_txt_classes_name);
        }
    }

    public void ajouter(int position, Classe classe) {
        classes.add(position,classe);
        notifyItemInserted(position);
    }

    public void supprimer(int position) {
        classes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ClassesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.row_layout_classes,parent,false);
        ViewHolder vuH = new ViewHolder(vu);
        return vuH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Classe classeActuel = classes.get(position);
        final String name=classeActuel.getName();

        holder.nomClasse.setText(name);

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
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            classes.clear();
            classes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
