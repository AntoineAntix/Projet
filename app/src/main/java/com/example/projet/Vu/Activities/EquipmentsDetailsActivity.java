package com.example.projet.Vu.Activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet.Modele.Classes.Classe;
import com.example.projet.Modele.Equipments.Equipments;
import com.example.projet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class EquipmentsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_equipments);

        getIncommingIntent();
    }

    private void getIncommingIntent() {
        if (getIntent().hasExtra("keyequipment")) {
            String jsonEquipment = getIntent().getStringExtra("keyequipment");
            Gson gson = new Gson();
            Equipments equipments = gson.fromJson(jsonEquipment, Equipments.class);

            setTxt(equipments);
        }
    }

    private void setTxt(Equipments equipments) {
        TextView nomTv = findViewById(R.id.nom);
        nomTv.setText(equipments.getName());

        TextView descriptionTv = findViewById(R.id.description);
        descriptionTv.setText(equipments.getDescription());

        TextView lvlTv = findViewById(R.id.lvl);
        lvlTv.setText(equipments.getLvl());

        TextView typeTv = findViewById(R.id.type);
        typeTv.setText(equipments.getType());

        ImageView imageVw = findViewById(R.id.image);
        Picasso.with(getApplicationContext())
                .load(equipments.getImgUrl())
                .into(imageVw);


    }
}
