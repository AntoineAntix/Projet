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
 * Ce fragment gère l'affichage des video youtube tutoriel kama.
 */

public class FragmentTutoKama extends Fragment {
    public View v;
    private RecyclerView recyclerView;
    private static Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();
    private static int situation = 0;

    public FragmentTutoKama() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tuto_kamas_fragment, container, false); //Définition du layout
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView); //Définition de la recyclerview

        //Initialisation du recycleview
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));

        //Si on ouvre pour la première fois le fragment alors on ajoute les items des videos
        if(situation == 0) {
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/p3ENO5voEsc\" frameborder=\"0\" allowfullscreen></iframe>","Tuto Kama EP.1"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Vzy0rzlOIow\" frameborder=\"0\" allowfullscreen></iframe>","Tuto Kama EP.2"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cwLboTqzIIw\" frameborder=\"0\" allowfullscreen></iframe>","Tuto Kama EP.3"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/xD4smESQ8Ck\" frameborder=\"0\" allowfullscreen></iframe>","Tuto Kama EP.4"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/49CohezL8X4\" frameborder=\"0\" allowfullscreen></iframe>","Tuto Kama EP.5"));

            situation=1;
        }

        //Appel de l'adapter pour l'affichage
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
        return v;
    }
}
