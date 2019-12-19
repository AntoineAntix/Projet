package com.example.projet.Vu.Fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Vector;

import com.example.projet.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.projet.Vu.Adapter.VideoAdapter;
import com.example.projet.Modele.YouTubeVideos;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Ce fragment gère l'affichage des video youtube tutoriel XP.
 */

public class FragmentTutoXP extends Fragment {
    public View v;
    private RecyclerView recyclerView;
    private static Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();
    private static int situation = 0;

    public FragmentTutoXP() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tuto_xp_fragment, container, false); //Définition du layout

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView); //Définition de la recyclerview

        //Initialisation du recycleview
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));

        //Si on ouvre pour la première fois le fragment alors on ajoute les items des videos
        if(situation == 0) {
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/5yAdo_907Rg\" frameborder=\"0\" allowfullscreen></iframe>","XP LVL 1 à 100"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qoQZsk7uzPo\" frameborder=\"0\" allowfullscreen></iframe>","XP LVL 100 à 200"));
            situation=1;
        }

        //Appel de l'adapter pour l'affichage
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);

        return v;
    }
}
