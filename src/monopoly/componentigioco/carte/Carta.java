package monopoly.componentigioco.carte;

import monopoly.componentigioco.giocatore.Giocatore;

import java.io.File;
import java.util.LinkedList;

public abstract class Carta implements InformazioniCarta {
    private final String descrizione;
    private static final String percorsoFile="src/monopoly/componentigioco/carte/".replace("/",File.separator);


    public Carta(String descrizione) {
        this.descrizione=descrizione;
    }


    public static LinkedList<Carta> creaImprevisti() {
        return InformazioniCarta.readFromFile(new File(percorsoFile+"Imprevisti.txt"));
    }

    public static LinkedList<Carta> creaProbabilita() {
        return InformazioniCarta.readFromFile(new File(percorsoFile+"Probabilita.txt"));
    }
    public abstract void azioneCarta(Giocatore giocatoreCorrente);

    @Override
    public String toString() {
        return "Hai pescato: '" + descrizione + '\'';
    }
}
