package com.example.projet.Vu.Activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.projet.Modele.Classes.Classe;
import com.example.projet.Modele.Weapons.Weapons;
import com.example.projet.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class WeaponsDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_weapons);


        getIncommingIntent();
    }

    private void getIncommingIntent()
    {
        if (getIntent().hasExtra("keyweapons")) {
            String jsonWeapons = getIntent().getStringExtra("keyweapons");
            Gson gson = new Gson();
            Weapons weapons = gson.fromJson(jsonWeapons, Weapons.class);

            setTxt(weapons);
        }
    }

    private void setTxt(Weapons weapons)
    {
        TextView nomTv = findViewById(R.id.nom);
        nomTv.setText(weapons.getName());

        TextView descriptionTv = findViewById(R.id.description);
        descriptionTv.setText(weapons.getDescription());

        TextView lvlTv = findViewById(R.id.lvl);
        lvlTv.setText(weapons.getLvl());

        TextView typeTv = findViewById(R.id.type);
        typeTv.setText(weapons.getType());

        ImageView imageVw =  findViewById(R.id.image);
        Picasso.with(getApplicationContext())
                .load(weapons.getImgUrl())
                .resize(400, 400)
                .into(imageVw);


    }
}
