package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class PuntInteres {
    private String tipusPI;
    private List<Usuari> usuarisPelPI = new ArrayList<>();

    public PuntInteres() {}

    public PuntInteres(String tipusPI) {
        this.tipusPI = tipusPI;
        this.usuarisPelPI = new ArrayList<>();
    }

    public String getTipusPI() {
        return tipusPI;
    }

    public void setTipusPI(String tipusPI) {
        this.tipusPI = tipusPI;
    }

    public List<Usuari> getUsuarisPelPI() {
        return usuarisPelPI;
    }

    public void setUsuarisPelPI(List<Usuari> usuarisPelPI) {
        this.usuarisPelPI = usuarisPelPI;
    }
}
