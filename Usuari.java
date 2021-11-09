package edu.upc.dsa.models;

import edu.upc.dsa.ManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class Usuari {
    private String idUsuari;
    private List<PuntInteres> puntsInteres = new ArrayList<>();

    public Usuari() {}

    public Usuari(String idUsuari) {
        this.idUsuari = idUsuari;
        this.puntsInteres = puntsInteres;
    }

    public String getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(String nomUsuari) {
        this.idUsuari = nomUsuari;
    }

    public List<PuntInteres> getPuntsInteres() {
        return puntsInteres;
    }

    public void setPuntsInteres(List<PuntInteres> puntsInteres) {
        this.puntsInteres = puntsInteres;
    }
}
