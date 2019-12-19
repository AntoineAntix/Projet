package com.example.projet.Vu.Adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.projet.R;
import com.example.projet.Modele.YouTubeVideos;

import java.util.List;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Cet Adapter gère l'affichage du recyclerview des video Youtube.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<YouTubeVideos> youtubeVideoList;

    //Fonction qui permet l'appelle de la classe java
    public VideoAdapter(List<YouTubeVideos> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
    }

    //On définit le layout correspondant à l'affichage des items
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.video_tuto_xp, parent, false);
        return new VideoViewHolder(view);
    }

    //On récupère les données de l'objet
    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        holder.videoWeb.loadData( youtubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8" );
        holder.nomTxt.setText( youtubeVideoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    //On définit ce qu'il y a à afficher dans le recyclerview
    public class VideoViewHolder extends RecyclerView.ViewHolder{

        public WebView videoWeb;
        public TextView nomTxt;

        //On définit les balises correspondant à ce qu'on veut afficher
        public VideoViewHolder(View itemView) {
            super(itemView);
            videoWeb = (WebView) itemView.findViewById(R.id.videoWebView);
            nomTxt = (TextView) itemView.findViewById(R.id.textView);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {
            } );
        }
    }
}