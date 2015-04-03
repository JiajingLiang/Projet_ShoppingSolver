package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-01.
 */
public class Category {

    private String name;
    private float ratioTPS; // Taxe sur les produits et services
    private float ratioTVQ; // Taxe de vente du Quebec

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return this.name;
    }

    public void setRatioTPS(float ratio) {
        this.ratioTPS = ratio;
    }

    public float getRatioTPS() {
        return this.ratioTPS;
    }

    public void setRatioTVQ(float ratio) {
        this.ratioTVQ = ratio;
    }

    public float getRatioTVQ() {
        return this.ratioTVQ;
    }
}
