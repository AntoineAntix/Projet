package com.example.projet.Modele.Weapons;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Classe java de l'objet Weapons.
 */

public class Weapons {
    private String _id;
    private String name;
    private String description;
    private String level;
    private String type;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLvl() {
        return level;
    }

    public void setLvl(String lvl) {
        this.level = lvl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imageUrl) {
        this.imgUrl = imageUrl;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
