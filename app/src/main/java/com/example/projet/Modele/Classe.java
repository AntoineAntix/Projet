package com.example.projet.Modele;

public class Classe {
    private String name;
    private String url;
    private String description;
    private String[] roles;
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

    public String[] getRoles() {
        return roles;
    }
    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
