package monopoly.componentigioco.casella;

import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;

import java.util.LinkedList;

public class PrintCasellaPrigione {

    private String printRighetta(){
        StringBuilder riga=new StringBuilder();
        riga.append("|");
        return riga.toString();
    }

    public String printRigaDueEQuattro(String stringa){
        StringBuilder secondaRiga = new StringBuilder();
        secondaRiga.append(CostantiCaselle.SPAZIO).append(printRighetta()).append(stringa);
        secondaRiga.append(CostantiCaselle.SPAZIO.repeat(((Costanti.LARGHEZZA_CASELLA ) - secondaRiga.length())));
        secondaRiga.append(printRighetta()).append(CostantiCaselle.SPAZIO);
        return secondaRiga.toString();
    }

    public String printRigaTre(LinkedList<String> giocatoriPresenti, String colore, LinkedList<Giocatore> giocatori) {
        return printRiga(giocatoriPresenti, colore, giocatori, true);
    }

    public String printRigaCinque(LinkedList<String> giocatoriPresenti, String colore, LinkedList<Giocatore> giocatori) {
        return printRiga(giocatoriPresenti, colore, giocatori, false);
    }

    public String printRiga(LinkedList<String> giocatoriPresenti, String colore, LinkedList<Giocatore> giocatori, boolean isTransito) {
        StringBuilder riga = new StringBuilder();
        riga.append(CostantiCaselle.SPAZIO).append(printRighetta());
        int giocatoriCount = 0;

        for (String giocatore : giocatoriPresenti) {
            if (!(giocatore.isBlank())) {
                Giocatore giocatoreOggetto = cercaGiocatore(giocatore, giocatori);
                if (giocatoreOggetto != null && giocatoreOggetto.isImprigionato() != isTransito) {
                    giocatoriCount++;
                    riga.append(giocatore).append(Costanti.COLORE_SFONDO).append(" ");
                }
            }
        }

        int spaziDaFare = 2 * giocatoriCount;
        riga.append(CostantiCaselle.SPAZIO.repeat(Math.max(0, Costanti.LARGHEZZA_CASELLA - 2 - spaziDaFare))).append(colore);
        riga.append(printRighetta()).append(CostantiCaselle.SPAZIO);
        return riga.toString();
    }


    private Giocatore cercaGiocatore(String simboloGiocatore, LinkedList<Giocatore> giocatori) {
        for (Giocatore giocatore : giocatori) {
            if (simboloGiocatore.equals(giocatore.getSimbolo()))
                return giocatore;
        }
        return null;
    }

}
