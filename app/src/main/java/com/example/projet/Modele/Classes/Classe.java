package com.example.projet.Modele.Classes;

import java.util.List;

public class Classe {
    private String name;
    private String url;
    private String description;
    private List<String> roles;
    private String maleImg;
    private String femaleImg;
    private List<Spells> spells;


    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getMaleImg() {
        return maleImg;
    }

    public String getFemaleImg() {
        return femaleImg;
    }

    public List<String> getRoles() {
        return roles;
    }

    public List<Spells> getSpells() { return spells; }
}
