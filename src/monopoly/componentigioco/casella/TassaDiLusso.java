package monopoly.componentigioco.casella;

import monopoly.utilita.Costanti;

public class TassaDiLusso extends Tassa {

    public TassaDiLusso(Casella[][] caselle) {
        super(); // serve a qualcosa questo super??
        setCasella(caselle);
    }

    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo=NomiCaselle.TASSE;
        super.setNomeColore(tipo.getNome(0),tipo.getColore());
    }

    @Override
    public void setPedaggioDefault() {
        super.setPedaggio(Costanti.IMPORTO_TASSA_LUSSO);
    }
}
