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
        v = inflater.inflate(R.layout.tuto_kamas_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));

        if(situation == 0) {
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/p3ENO5voEsc\" frameborder=\"0\" allowfullscreen></iframe>"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Vzy0rzlOIow\" frameborder=\"0\" allowfullscreen></iframe>"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cwLboTqzIIw\" frameborder=\"0\" allowfullscreen></iframe>"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/xD4smESQ8Ck\" frameborder=\"0\" allowfullscreen></iframe>"));
            youtubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/49CohezL8X4\" frameborder=\"0\" allowfullscreen></iframe>"));

            situation=1;
        }
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
        return v;
    }
}
