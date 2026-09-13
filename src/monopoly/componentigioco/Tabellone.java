package monopoly.componentigioco;


import monopoly.Coordinate;
import monopoly.componentigioco.casella.*;
import monopoly.utilita.Costanti;

import java.util.Objects;

/**
 * <i>Classe che gestisce il tabellone e ciò che ne deriva (Caselle)</i>
 */

public class Tabellone {
    private static Casella[][] caselle ;

    /**
     * Genera la stringa del tabellone
     * @return {@code String}
     */
    @Override
    public String toString(){
        Casella currentCell;
        String spazio = " ";
        StringBuilder tabellone=new StringBuilder();

        for (int i = 0; i < Costanti.RIGHE; i++) {
            for (int d = 0; d < Costanti.RIGHE_CASELLA; d++) {
                for (int col = 0; col < Costanti.CASELLE_PER_RIGA; col++) {
                    currentCell = caselle[i][col];
                    if (Casella.checkForNull(currentCell)) {
                        tabellone.append(Costanti.COLORE_SFONDO_NULL).append(spazio.repeat(Costanti.LARGHEZZA_CASELLA+2)); // +2 per lo spazio aggiunto per separare le caselle
                    }
                    else {
                        tabellone.append(currentCell.casellaString(d));
                    }
                }
                tabellone.append("\n");
            }
        }

        return tabellone.toString();

    }

    /**
     *<p> Metodo che si occupa di gestire il coretto riempimento la matrice di caselle. {@link #caselle}</p>
     * <p>Il metodo inizia riempendo la matrice di {@link #caselle} con le caselle che hanno una posizione predefinita. </p>
     * <p>Successivamente dopo un controllo crea le caselle generali.</p>
     * @see #caselleSpeciali() 
     * @see  #controlloPosizione(int, int)
     * @see  #scegliNome(int, int)
     */
    public void crea() {

        this.caselle = new Casella[Costanti.RIGHE][Costanti.CASELLE_PER_RIGA];
        caselleSpeciali();
        for (int i = 0; i < Costanti.RIGHE; i++) {
            for (int j = 0; j < Costanti.CASELLE_PER_RIGA; j++) {
                if (!controlloPosizione(i,j)) scegliNome(i, j); // controlla se non è ai lati

            }
        }
    }

    /**
     *
     * @param i posizione in y della casella
     * @param j posizione in x della casella
     * @return {@code True} se le coordinate no sono i lati del rettangolo.
     */
    public static boolean controlloPosizione(int i ,int j){
        return i > 0 && i < Costanti.RIGHE-1 && j > 0 && j < Costanti.CASELLE_PER_RIGA-1;
    }

    /**
     * <p>Riempe {@link #caselle} con le caselle "speciali" </p>
     */
    private void caselleSpeciali(){
        int righeMatrice=Costanti.RIGHE-1;
        int elementiMatrice=Costanti.CASELLE_PER_RIGA-1;
        int numeroStazioni=4;

        caselle[righeMatrice][elementiMatrice] = new Via();
        for (int i = 0; i < numeroStazioni ; i++) {
            new Stazione(caselle);
        }
        caselle[0][0] = new Parcheggio();
        Prigione prigione = new Prigione();
        caselle[righeMatrice][0]=prigione;
        caselle[0][elementiMatrice] = new VaiInPrigione(prigione);
        CaselleCarte.creaCaselleCarte(caselle);
        Tassa.creaTasse(caselle);
        Societa.creaSocieta(caselle);


    }

    /**
     * Riempe la matrice {@link #caselle} ,dove non sono già state create le caselle, con le proprietà.
     * @param i posizione in y della casella
     * @param j posizione in x della casella
     */
    private void scegliNome(int i, int j) {
        if (caselle[i][j] == null){
            caselle[i][j] = new Proprieta(i, j);
        }
    }

    /**
     * Il metodo si occupa di modificare i simboli dei giocatori visualizzabili in una casella
     * @param simbolo simbolo giocatore
     * @param i posizione y giocatore
     * @param j posizione x giocatore

     */
    public void modificaCasella(String simbolo, int i, int j) {
        caselle[i][j].aggiungiCarattere(simbolo);
    }


    public Casella[][] getCaselle() {
        return caselle;
    }



    public static Casella getCasella(int i, int j) {

        return caselle[i][j];
    }

    public static Coordinate controlloCasella(String nomeCasella) {
        nomeCasella = nomeCasella.trim().toLowerCase();
        String nomeDaComparare = "";
        for (Casella[] righeCasella : caselle) {
            for (Casella casella : righeCasella)
                if (casella != null) {
                    nomeDaComparare = casella.getNome().trim().toLowerCase();
                    if (nomeCasella.equals(nomeDaComparare))
                        return casella.getCoordinate();
                }
        }
        return null;
    }
}
