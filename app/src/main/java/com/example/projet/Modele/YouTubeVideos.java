package com.example.projet.Modele;

public class YouTubeVideos {
    private String videoUrl;
    private String name;

    public YouTubeVideos() {
    }

    public YouTubeVideos(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
