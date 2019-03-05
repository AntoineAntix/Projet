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
            String lvlS = getIntent().getStringExtra("lvl");
            setTxt(nomS,descriptionS,lvlS);
        }
    }

    private void setTxt(String nom, String description, String lvl)
    {
        TextView nomTv = findViewById(R.id.nom);
        nomTv.setText(nom);

        TextView descriptionTv = findViewById(R.id.description);
        descriptionTv.setText(description);

        TextView lvlTv = findViewById(R.id.lvl);
        lvlTv.setText(lvl);
    }
}
