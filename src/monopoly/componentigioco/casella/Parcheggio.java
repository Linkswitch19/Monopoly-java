package monopoly.componentigioco.casella;


import monopoly.Coordinate;


public class Parcheggio extends Casella {

    public Parcheggio() {
        super();
        setCoordinate(new Coordinate(0,0));
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
}
