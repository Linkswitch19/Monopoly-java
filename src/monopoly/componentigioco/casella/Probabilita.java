package monopoly.componentigioco.casella;

import monopoly.Coordinate;
import monopoly.componentigioco.Banca;
import monopoly.componentigioco.Tabellone;
import monopoly.componentigioco.carte.Carta;
import monopoly.componentigioco.carte.ModificaBudget;
import monopoly.componentigioco.carte.VaiA;
import monopoly.componentigioco.giocatore.Giocatore;

import java.util.Collections;

public class Probabilita extends CaselleCarte{

    public Probabilita(Casella[][] caselle) {
        super(caselle);
    }

    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo=NomiCaselle.PROBABILITA;
        super.setNomeColore(tipo.getNome(0),tipo.getColore());
    }

    @Override
    public void azioneCasella(Giocatore giocatoreCorrente) {
        Carta carta = getProbabilita().poll();
        if (carta != null)
            super.azioneCasellaCarte(carta, giocatoreCorrente);
        getProbabilita().add(carta);
    }


    @Override
    public String infoCasella() {
        return super.infoCasella() + " probabilità";
    }
}
