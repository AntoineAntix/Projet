package com.example.projet.Vu.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

    public void showFragment(View view) {
        Intent intent = new Intent(this, MainActivityFragment.class);
        startActivity(intent);
    }


}
