package com.example.projet.Vu;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet.R;
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
        if(getIntent().hasExtra("nom")&& getIntent().hasExtra("description"))
        {
            String nomS = getIntent().getStringExtra("nom");
            String descriptionS = getIntent().getStringExtra("description");
            String lvlS = getIntent().getStringExtra("lvl");
            String typeS = getIntent().getStringExtra("type");
            String imageUrlS=getIntent().getStringExtra("image");
            setTxt(nomS,descriptionS,lvlS,typeS, imageUrlS);
        }
    }

    private void setTxt(String nom, String description, String lvl, String type, String imageUrl)
    {
        TextView nomTv = findViewById(R.id.nom);
        nomTv.setText(nom);

        TextView descriptionTv = findViewById(R.id.description);
        descriptionTv.setText(description);

        TextView lvlTv = findViewById(R.id.lvl);
        lvlTv.setText(lvl);

        TextView typeTv = findViewById(R.id.type);
        typeTv.setText(type);

        ImageView imageVw =  findViewById(R.id.image);
        Picasso.with(getApplicationContext())
                .load(imageUrl)
                .into(imageVw);


    }
}
