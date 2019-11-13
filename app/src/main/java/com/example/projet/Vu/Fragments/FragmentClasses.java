package com.example.projet.Vu.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.Controller.ClassesController;
import com.example.projet.Modele.Classe;
import com.example.projet.Modele.ClassesOnItemClick;
import com.example.projet.R;
import com.example.projet.Vu.ClassesAdapter;
import com.example.projet.Vu.ClassesDetailsActivity;

import java.util.List;

public class FragmentClasses extends Fragment {
    public View v;
    private RecyclerView recyclerView;
    private ClassesAdapter classesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ClassesController crt;
    private ProgressBar progressBar;

    public FragmentClasses() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.classes_fragment, container, false);
        recyclerView = v.findViewById(R.id.myRecyclerView);
        progressBar = v.findViewById(R.id.chargement_main_activity);
        crt = new ClassesController(this, getActivity().getSharedPreferences("dataClasse", Context.MODE_PRIVATE));
        crt.onCreate();
        return v;
    }

    public void showLoader(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        progressBar.setVisibility(View.GONE);
    }
    public void showList(List<Classe> listClasses)
    {
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        classesAdapter = new ClassesAdapter(listClasses, getActivity().getApplicationContext(), new ClassesOnItemClick() {
            @Override
            public void onItemClick(Classe classes) {
                Toast.makeText(getActivity().getApplicationContext(), classes.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity().getApplicationContext(), ClassesDetailsActivity.class);
                intent.putExtra("nom", classes.getName());
                intent.putExtra("description", classes.getDescription());
                intent.putExtra("url", classes.getUrl());
                intent.putExtra("imageMale", classes.getMaleImg());
                intent.putExtra("imageFemale", classes.getFemaleImg());
                intent.putExtra("roles", classes.getRoles());
                FragmentClasses.this.startActivity(intent);
            }
        }
        );
        recyclerView.setAdapter(classesAdapter);
    }


    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                classesAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
