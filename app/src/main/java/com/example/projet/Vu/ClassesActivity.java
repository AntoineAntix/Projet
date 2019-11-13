package com.example.projet.Vu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projet.Controller.ClassesController;
import com.example.projet.Modele.Classe;
import com.example.projet.Modele.ClassesOnItemClick;
import com.example.projet.R;

import java.util.List;

public class ClassesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClassesAdapter classesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ClassesController crt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        recyclerView = findViewById(R.id.myRecyclerView);
        progressBar = findViewById(R.id.chargement_main_activity);
        crt = new ClassesController(this, getSharedPreferences("dataClasse", Context.MODE_PRIVATE));
        crt.onCreate();
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
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        classesAdapter = new ClassesAdapter(listClasses, getApplicationContext(), new ClassesOnItemClick() {
            @Override
            public void onItemClick(Classe classes) {
                Toast.makeText(getApplicationContext(), classes.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ClassesDetailsActivity.class);
                intent.putExtra("nom", classes.getName());
                intent.putExtra("description", classes.getDescription());
                intent.putExtra("url", classes.getUrl());
                intent.putExtra("imageMale", classes.getMaleImg());
                intent.putExtra("imageFemale", classes.getFemaleImg());
                intent.putExtra("roles", classes.getRoles());
                ClassesActivity.this.startActivity(intent);
            }
        }
        );
        recyclerView.setAdapter(classesAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
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
