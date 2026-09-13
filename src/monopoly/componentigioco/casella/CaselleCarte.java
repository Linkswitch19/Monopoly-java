package monopoly.componentigioco.casella;

import monopoly.Coordinate;
import monopoly.componentigioco.carte.Carta;
import monopoly.componentigioco.carte.InformazioniCarta;
import monopoly.componentigioco.giocatore.Giocatore;

import java.util.LinkedList;
import java.util.Queue;

public abstract class CaselleCarte extends Casella {

    private static Queue<Carta> imprevisti = new LinkedList<>();
    private static Queue<Carta> probabilita = new LinkedList<>();


    public static void creaCaselleCarte(Casella[][] caselle){
        for(int i=0; i<3; i++){
            new Imprevisti(caselle);
            new Probabilita(caselle);
        }
    }

    public CaselleCarte(Casella [][] caselle){
        super();
        super.setCasella(caselle);
    }

    @Override
    public String infoCasella() {
        return "Pesca una carta";
    }

    public void azioneCasellaCarte(Carta carta, Giocatore giocatoreCorrente) {
        System.out.println(carta.toString());
        carta.azioneCarta(giocatoreCorrente);
        System.out.println("Il tuo importo è ora " + giocatoreCorrente.getSoldi() + "CHF\n" );
    }

    public static Queue<Carta> getImprevisti() {
        return imprevisti;
    }

    public static Queue<Carta> getProbabilita() {
        return probabilita;
    }

    public static void creaCarte() {
        CaselleCarte.probabilita = Carta.creaProbabilita();
        CaselleCarte.imprevisti = Carta.creaImprevisti();
    }
}
