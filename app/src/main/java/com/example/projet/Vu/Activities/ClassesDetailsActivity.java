package com.example.projet.Vu.Activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet.R;
import com.squareup.picasso.Picasso;

public class ClassesDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_classes);
        getIncommingIntent();
    }

    private void getIncommingIntent() {
        if(getIntent().hasExtra("nom")&& getIntent().hasExtra("description")) {
            String nomS = getIntent().getStringExtra("nom");
            String descriptionS = getIntent().getStringExtra("description");
            String urlS = getIntent().getStringExtra("url");
            String roleS = getIntent().getStringExtra("roles");
            String imageMaleS=getIntent().getStringExtra("imageMale");
            String imageFemaleS=getIntent().getStringExtra("imageFemale");
            setTxt(nomS,descriptionS,urlS, imageMaleS, imageFemaleS, roleS);
        }
    }

    private void setTxt(String nom, String description, String urlS, String imageMaleS, String imageFemaleS, String roleS)
    {
        TextView nomTv = findViewById(R.id.nom);
        nomTv.setText(nom);

        TextView descriptionTv = findViewById(R.id.description);
        descriptionTv.setText(description);

        TextView lvlTv = findViewById(R.id.urlS);
        lvlTv.setText(urlS);

        ImageView imageM =  findViewById(R.id.imageM);
        Picasso.with(getApplicationContext())
                .load(imageMaleS)
                .into(imageM);

        ImageView imageF =  findViewById(R.id.imageF);
        Picasso.with(getApplicationContext())
                .load(imageFemaleS)
                .into(imageF);




    }
}
