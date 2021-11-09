package edu.upc.dsa;

import edu.upc.dsa.models.PuntInteres;
import edu.upc.dsa.models.Usuari;

import java.util.List;

public interface Manager {
    //Afegir usuari
    public void addUsuari(String idUsuari);
    //Consultar informació de l'usuari
    public Usuari consultarUsuari(String idUsuari);
    //Consultar Punts d'Interès per on ha passat un usuari
    public List<PuntInteres> consultarPIPerOnPassaUsuari(String idUsuari);
    //Llista d'usuaris (ordre alfabètic)
    public List<Usuari> llistaUsuaris();
    //Informació sobre el Punt d'Interès actual d'un usuari
    public PuntInteres infoPI(String idUsuari);
    //Llista d'usuaris que han passat per un Punt d'Interès
    public List<Usuari> usuarisPerPI(String nomPI);
    //Llista d'usuaris segons el nombre de Punts d'Interès que han creuat
    public List<Usuari> usuarisSegonsPI();


    //Funcions auxiliars
    public void addPI(PuntInteres pi);
    public void addPIAUsuari(PuntInteres pi, String idUsuari);
    public void clear();
    public Usuari getUsuariPerId(String idUsuari);
}
