package com.example.projet.Modele.Classes;

import java.util.List;

public class Classe {
    private String name;
    private String url;
    private String description;
    private List<String> roles;
    private String maleImg;
    private String femaleImg;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaleImg() {
        return maleImg;
    }
    public void setMaleImg(String maleImg) {
        this.maleImg = maleImg;
    }

    public String getFemaleImg() {
        return femaleImg;
    }
    public void setFemaleImg(String femaleImg) {
        this.femaleImg = femaleImg;
    }

    public List<String> getRoles() {
        return roles;
    }
}
