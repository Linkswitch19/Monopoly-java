package monopoly.componentigioco.casella;

import monopoly.Coordinate;
import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;

public class VaiInPrigione extends Casella {
    private final Prigione prigione;
    public VaiInPrigione(Prigione prigione) {
        super();
        setCoordinate(new Coordinate(0,Costanti.CASELLE_PER_RIGA-1));
        this.prigione=prigione;
    }

    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo = NomiCaselle.SINGOLE;
        super.setNomeColore(tipo.getNome(0),tipo.getColore());
    }

    @Override
    public void setPedaggioDefault() {
        super.setPedaggio(0);
    }

    @Override
    public String infoCasella() {
        return "";
    }

    @Override
    public void azioneCasella(Giocatore giocatoreCorrente) {
        if (isGiocatore(giocatoreCorrente)){
            giocatoreCorrente.spostaGiocatoreInPrigione(this);
            this.prigione.aggiungiCarattere(giocatoreCorrente.getSimbolo());
        }

    }
}
