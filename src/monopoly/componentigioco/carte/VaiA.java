package monopoly.componentigioco.carte;


import monopoly.Coordinate;
import monopoly.componentigioco.Tabellone;
import monopoly.componentigioco.giocatore.Giocatore;

public class VaiA extends Carta{
    private final Coordinate coordinateDiArrivo;
    public VaiA(String descrizione, String terzaInformazione) {
        super(descrizione);
        this.coordinateDiArrivo = Tabellone.controlloCasella(terzaInformazione);
    }

    public Coordinate getCoordinateDiArrivo() {
        return coordinateDiArrivo;
    }

    @Override
    public void azioneCarta(Giocatore giocatoreCorrente) {
        giocatoreCorrente.spostaGiocatoreConCarta(this);
    }
}
