package monopoly.componentigioco.casella;

import monopoly.Coordinate;
import monopoly.componentigioco.Banca;
import monopoly.componentigioco.Tabellone;
import monopoly.componentigioco.carte.Carta;
import monopoly.componentigioco.carte.ModificaBudget;
import monopoly.componentigioco.carte.VaiA;
import monopoly.componentigioco.giocatore.Giocatore;

import java.util.Collections;

public class Imprevisti extends CaselleCarte{


    public Imprevisti(Casella[][] caselle) {
        super(caselle);
    }

    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo=NomiCaselle.IMPREVISTI;
        super.setNomeColore(tipo.getNome(),tipo.getColore());
    }

    @Override
    public void azioneCasella(Giocatore giocatoreCorrente) {
        Carta carta = getImprevisti().poll();
        if (carta != null)
            super.azioneCasellaCarte(carta, giocatoreCorrente);
        getImprevisti().add(carta);
    }

    @Override
    public String infoCasella() {
        return super.infoCasella() + " imprevisti";
    }

}
