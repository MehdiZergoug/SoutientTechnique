package com.example.semaine9_exercice1_final;

public class joueur {
    private String nomUSager;
    private String courriel;
    private int MeilleurPointage;

    public joueur() {
    }

    public joueur(String nomUSager, String courriel, int meilleurPointage) {
        this.nomUSager = nomUSager;
        this.courriel = courriel;
        MeilleurPointage = meilleurPointage;
    }

    public String getNomUSager() {
        return nomUSager;
    }

    public void setNomUSager(String nomUSager) {
        this.nomUSager = nomUSager;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public int getMeilleurPointage() {
        return MeilleurPointage;
    }

    public void setMeilleurPointage(int meilleurPointage) {
        MeilleurPointage = meilleurPointage;
    }
}
