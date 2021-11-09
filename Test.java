package edu.upc.dsa;

import edu.upc.dsa.models.PuntInteres;
import edu.upc.dsa.models.Usuari;
import org.apache.log4j.Logger;

import java.util.List;

public class Test {
    private static Logger logger = Logger.getLogger(Test.class);
    Manager manager = ManagerImpl.getInstance();

    public void SetUp(){

        manager.addUsuari("Paula");
        manager.addUsuari("Joan");

        PuntInteres pi1 = new PuntInteres("Porta");
        PuntInteres pi2 = new PuntInteres("CasellaX");
        PuntInteres pi3 = new PuntInteres("Pont");

        manager.addPI(pi1);
        manager.addPI(pi2);
        manager.addPI(pi3);

        manager.addPIAUsuari(pi1, "Joan");
        manager.addPIAUsuari(pi2, "Joan");
        manager.addPIAUsuari(pi3, "Paula");

    }

    public void TearDown(){
        manager.clear();
    }

    public void Test1(){
        logger.info("Afegim un usuari amb nom: Toni");

        manager.addUsuari("Toni");

        Usuari u = manager.consultarUsuari("Toni");

        logger.info("Informació de l'usuari " + u.getIdUsuari() + ": \nPunts d'interès: " + u.getPuntsInteres());

        PuntInteres pi = new PuntInteres("CasellaY");

        manager.addPIAUsuari(pi, "Toni");

        logger.info("Punt d'interès afegit:");
        logger.info("Informació de l'usuari " + u.getIdUsuari() + ": \nPunts d'interès: " + u.getPuntsInteres());

    }

    public void test2(){
        List<Usuari> l = manager.llistaUsuaris();
        logger.info("Llista d'usuaris per ordre alfabètic: " + l.toString());

        List<Usuari> l2 = manager.usuarisPerPI("Porta");
        logger.info("Llista d'usuaris per que han passat per Porta: "+ l2.toString());

    }


}
