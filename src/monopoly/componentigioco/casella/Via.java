package monopoly.componentigioco.casella;

import monopoly.Coordinate;
import monopoly.utilita.Costanti;

public class Via extends Casella {

    public Via() {
        super();
        setCoordinate(new Coordinate(Costanti.RIGHE-1,Costanti.CASELLE_PER_RIGA-1));
    }

    @Override
    public void setPedaggioDefault() {
        setPedaggio(Costanti.IMPORTO_DEL_VIA);
    }
    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo=NomiCaselle.SINGOLE;
        super.setNomeColore(tipo.getNome(1),tipo.getColore());
    }
    @Override
    public String infoCasella() {
        return "Ritira " + this.getPedaggio();
    }
}
