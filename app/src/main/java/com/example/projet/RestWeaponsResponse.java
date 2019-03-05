package com.example.projet;

import java.util.List;

public class RestWeaponsResponse
{
    private Integer compteur;
    private String suivant;
    private String precedent;
    private List<Weapons> resultat;


    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public String getSuivant() {
        return suivant;
    }

    public void setSuivant(String suivant) {
        this.suivant = suivant;
    }

    public String getPrecedent() {
        return precedent;
    }

    public void setPrecedent(String precedent) {
        this.precedent = precedent;
    }

    public List<Weapons> getResultat() {
        return resultat;
    }

    public void setResultat(List<Weapons> resultat) {
        this.resultat = resultat;
    }
}
