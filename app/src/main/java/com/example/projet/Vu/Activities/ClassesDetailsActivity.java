package com.example.projet.Vu.Activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projet.Modele.Classes.Classe;
import com.example.projet.R;
import com.example.projet.Vu.Adapter.AttaqueClasseAdapter;
import com.example.projet.Vu.Adapter.RoleClasseAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ClassesDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRole;
    public static RoleClasseAdapter roleClasseAdapter;
    private RecyclerView recyclerViewAttaque;
    public static AttaqueClasseAdapter attaqueClasseAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_classes);
        recyclerViewRole = findViewById(R.id.myRecyclerViewRole);
        recyclerViewAttaque=findViewById(R.id.myRecyclerViewAttaque);
        getIncommingIntent();
    }

    private void getIncommingIntent() {
        if (getIntent().hasExtra("keyclasses")) {
            String jsonClasse = getIntent().getStringExtra("keyclasses");
            Gson gson = new Gson();
            Classe classe = gson.fromJson(jsonClasse, Classe.class);

            setTxt(classe);
        }
    }

    private void setTxt(Classe classe){
        recyclerViewRole.setHasFixedSize(true);
        recyclerViewAttaque.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        layoutManager=new LinearLayoutManager(this);

        recyclerViewRole.setLayoutManager(gridLayoutManager);
        recyclerViewAttaque.setLayoutManager(layoutManager);

        roleClasseAdapter = new RoleClasseAdapter(classe.getRoles(),getApplicationContext());
        attaqueClasseAdapter= new AttaqueClasseAdapter(classe.getSpells(),getApplicationContext());

        recyclerViewRole.setAdapter(roleClasseAdapter);
        recyclerViewAttaque.setAdapter(attaqueClasseAdapter);

        TextView nomTv = findViewById(R.id.nom);
        nomTv.setText(classe.getName());

        TextView descriptionTv = findViewById(R.id.description);
        descriptionTv.setText(classe.getDescription());

        TextView lvlTv = findViewById(R.id.urlS);
        lvlTv.setText(classe.getUrl());

        ImageView imageM =  findViewById(R.id.imageM);
        Picasso.with(getApplicationContext())
                .load(classe.getMaleImg())
                .into(imageM);

        ImageView imageF =  findViewById(R.id.imageF);
        Picasso.with(getApplicationContext())
                .load(classe.getFemaleImg())
                .into(imageF);
    }
}
