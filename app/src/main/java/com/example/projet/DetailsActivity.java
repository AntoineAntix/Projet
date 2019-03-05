package com.example.projet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getIncommingIntent();
    }

    private void getIncommingIntent()
    {
        if(getIntent().hasExtra("nom")&& getIntent().hasExtra("description"))
        {
            String nomS = getIntent().getStringExtra("nom");
            String descriptionS = getIntent().getStringExtra("description");
            setTxt(nomS,descriptionS);
        }
    }

    private void setTxt(String nom, String description)
    {
        TextView nomTv = findViewById(R.id.nom);
        nomTv.setText(nom);

        TextView descriptionTv = findViewById(R.id.description);
        descriptionTv.setText(description);
    }
}
