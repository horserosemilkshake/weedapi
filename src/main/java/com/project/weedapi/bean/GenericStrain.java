package com.project.weedapi.bean;


import java.io.Serializable;

public class GenericStrain implements Serializable {
    private String strain;
    private String type;
    private Double rating;
    private String[] effects;
    private String[] flavours;
    private String description;

    public GenericStrain(String strain) {
        this.strain = strain;
        type = "";
        rating = -1.0;
        effects = new String[]{};
        flavours = new String[]{};
        description = "";
    }

    public GenericStrain() {
    }

    public GenericStrain(String strain, String type, Double rating, String[] effects, String[] flavours, String description) {
        this.strain = strain;
        this.type = type;
        this.rating = rating;
        this.effects = effects;
        this.flavours = flavours;
        this.description = description;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String[] getEffects() {
        return effects;
    }

    public void setEffects(String[] effects) {
        this.effects = effects;
    }

    public String[] getFlavours() {
        return flavours;
    }

    public void setFlavours(String[] flavours) {
        this.flavours = flavours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
