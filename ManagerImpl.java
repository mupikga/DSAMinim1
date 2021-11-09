package edu.upc.dsa;

import edu.upc.dsa.models.PuntInteres;
import edu.upc.dsa.models.Usuari;
import org.apache.log4j.Logger;

import java.util.*;

public class ManagerImpl implements Manager{

    HashMap<String,Usuari> llistaUsuaris = new HashMap<String,Usuari>();
    HashMap<String,PuntInteres> llistaPunts = new HashMap<String,PuntInteres>();
    static final Logger logger = Logger.getLogger(ManagerImpl.class.getName());
    private static ManagerImpl manager;

    public static ManagerImpl getInstance(){
        if(manager==null){
            manager= new ManagerImpl();
        }
        return manager;
    }
    public ManagerImpl(){}


    @Override
    public void addUsuari(String idUsuari) {
        if (llistaUsuaris.get(idUsuari) != null)
                logger.error("Ja existeix un usuari amb aquest ID: " + idUsuari);
        else {
            Usuari u = new Usuari(idUsuari);
            llistaUsuaris.put(idUsuari, u);
            logger.info("Usuari afegit amb identificador: " + idUsuari);
        }
    }

    @Override
    public Usuari consultarUsuari(String idUsuari) {
        Usuari u = this.llistaUsuaris.get(idUsuari);
        if (u != null){
            logger.info("Usuari trobat: " + idUsuari);
        }
        else
            logger.error("Usuari no trobat: " + idUsuari);
        return u;
    }

    @Override
    public List<PuntInteres> consultarPIPerOnPassaUsuari(String idUsuari) {
        Usuari u = this.llistaUsuaris.get(idUsuari);
        List<PuntInteres> l = u.getPuntsInteres();
        logger.info("Punts per on ha passat " + idUsuari + " :\n");

        for (PuntInteres p : l) {
            logger.info("Punt d'interès: " + p.getTipusPI());
        }
        return l;
    }

    @Override
    public List<Usuari> llistaUsuaris() {
        List<Usuari> l = new ArrayList<>(llistaUsuaris.values());
        Collections.sort(l, new Comparator<Usuari>() {
            @Override
            public int compare(Usuari u1, Usuari u2) {
                return u1.getIdUsuari().compareToIgnoreCase(u2.getIdUsuari());
            }
        });
        logger.info("Llista d'usuaris (ordre alfabètic): " + l.toString());
        return l;
    }

    @Override
    public PuntInteres infoPI(String idUsuari) {
        Usuari u = this.llistaUsuaris.get(idUsuari);
        List<PuntInteres> l = u.getPuntsInteres();
        PuntInteres p = l.get(l.size());
        logger.info("Informació de que " + idUsuari + " passa pel punt d'interès " + p.getTipusPI());

        return p;
    }

    @Override
    public List<Usuari> usuarisPerPI(String nomPI) {
        PuntInteres p = this.llistaPunts.get(nomPI);
        List<Usuari> l = p.getUsuarisPelPI();
        logger.info("Llista d'usuaris per el punt d'interès " + nomPI +":\n" + l.toString());
        return l;
    }

    @Override
    public List<Usuari> usuarisSegonsPI() {
        List<Usuari> l = new ArrayList<>(llistaUsuaris.values());
        Usuari aux = null;
        for (Usuari u1 : l){
            for (Usuari u2: l){
                if (u1.getPuntsInteres().size() > u2.getPuntsInteres().size())
                    aux = u1;
                    u1= u2;
                    u2 = aux;
            }
        }
        logger.info("Llista d'usuaris segons el nombre de PI que han creuat (ordre decreixent): " + l.toString());
        return l;
    }

    // Funcions auxiliars
    @Override
    public void addPI(PuntInteres pi) {
        llistaPunts.put(pi.getTipusPI(), pi);
    }

    @Override
    public void addPIAUsuari(PuntInteres pi, String idUsuari) {
        Usuari u = this.llistaUsuaris.get(idUsuari);
        List<PuntInteres> l = new ArrayList<>();
        l.add(pi);
        u.setPuntsInteres(l);
    }

    @Override
    public void clear() {
        this.llistaUsuaris.clear();
        this.llistaPunts.clear();
    }

    @Override
    public Usuari getUsuariPerId(String idUsuari) {
        Usuari u = this.llistaUsuaris.get(idUsuari);
        return u;
    }
}
