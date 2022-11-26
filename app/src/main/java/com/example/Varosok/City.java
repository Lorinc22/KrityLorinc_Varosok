package com.example.Varosok;

import android.annotation.SuppressLint;

public class City {
    private int id;
    private String varos;
    private String orszag;
    private int lakossag;

    public City(int id, String name, String country, int population) {
        this.id = id;
        this.varos = name;
        this.orszag = country;
        this.lakossag = population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return varos;
    }

    public void setName(String name) {
        this.varos = name;
    }

    public String getCountry() {
        return orszag;
    }

    public void setCountry(String country) {
        this.orszag = country;
    }

    public int getPopulation() {
        return lakossag;
    }

    public void setPopulation(int population) {
        this.lakossag = population;
    }


    public void setAgeText(String ageText) {
        if (ageText.equals("")) {
            this.lakossag = 0;
        } else {
            this.lakossag = Integer.parseInt(ageText);
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("nev: %s \norszag: %s \nlakossag: %d\n\n", this.varos, this.orszag, this.lakossag);
    }
}
