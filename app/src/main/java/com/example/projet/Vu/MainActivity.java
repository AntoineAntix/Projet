package com.example.projet.Vu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.projet.R;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private static int m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void musiquePlay (){
        if(m==0)
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dofusmusic);
            mediaPlayer.start();
            m=1;
        }
    }

    public void showWeapons(View view) {
        Intent intent = new Intent(this, WeaponsActivity.class);
        startActivity(intent);
    }

    public void showClasses(View view) {
        Intent intent = new Intent(this, ClassesActivity.class);
        startActivity(intent);
    }


}
